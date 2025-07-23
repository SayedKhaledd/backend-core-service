package com.example.backendcoreservice.util;

import com.example.backendcoreservice.dto.CsvHeader;
import com.example.backendcoreservice.exception.CustomException;
import com.opencsv.bean.CsvBindByName;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class CsvValidator {

    // Supported media types for CSV and Excel files. Currently only CSV and basic excel file are supported.
    // There are other types of excel files like xlsx, xls, etc. which are not supported yet.
    private static final List<MediaType> supportedCSVAndExcelMediaTypes = List.of(
            new MediaType("text", "csv"),
            new MediaType("application", "vnd.ms-excel"));

    /**
     * This method is used to get the media type of a file.
     * <li> It first checks if the file is null or empty, if so, it throws a CustomException.</li>
     * <li> If the file is not null and not empty, it parses the content type of the file to a MediaType object and returns it.</li>
     *
     * @param file The file for which the media type is to be determined.
     * @return The media type of the file.
     * @throws CustomException if the file is null or empty.
     */
    public static void validateIsCsvFile(MultipartFile file) {
        MediaType mediaType = getMediaType(file);
        if (!supportedCSVAndExcelMediaTypes.contains(mediaType))
            throw new CustomException("Media type not supported. File must be a CSV.");
    }

    /**
     * This method is used to validate the headers from a CSV file.
     * It takes a MultipartFile and a Class type as input.
     * <li> It first reads the first line from the file, which is assumed to contain the headers, and splits it into an array of strings.</li>
     * <li> It then validates these headers against the expected headers for the given class type.</li>
     * <li> If the headers are not valid, it throws a CustomException with a message indicating that the CSV file does not match the expected format for the given class type.</li>
     * <li> If any exception occurs during this process, it throws a CustomException with a message indicating the exception that occurred.</li>
     *
     * @param file      The CSV file to be validated.
     * @param classType The class type against which the headers are to be validated.
     * @throws CustomException if the headers are not valid or if any exception occurs during the process.
     */
    public static <T> void validateHeadersFromFile(MultipartFile file, Class<T> classType) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String[] headers = reader.readLine().split(",");
            if (!validateHeadersStrings(headers, classType)) {
                throw new CustomException("CSV file does not match expected format for " + classType.getSimpleName());
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

    }

    /**
     * <p> This method is used to validate the headers from a CSV file against the expected headers for a given class type.</p>
     * <p> It takes an array of header strings and a Class type as input, and returns a boolean indicating whether the headers are valid. </p>
     * <p>
     * The method works as follows:
     * <ol>1. It first gets the expected headers for the given class type and normalizes them.</ol>
     * <ol>2. It then normalizes the actual headers from the CSV file.</ol>
     * <ol>3. It checks if the actual headers contain all the expected headers.</ol>
     * <ol>4. It also checks if the actual headers contain all the required headers.</ol>
     * <ol>5. If both checks pass, it returns true. Otherwise, it returns false.</ol>
     *
     * @param headers   The array of header strings from the CSV file.
     * @param classType The class type against which the headers are to be validated.
     * @return A boolean indicating whether the headers are valid.
     */
    private static <T> boolean validateHeadersStrings(String[] headers, Class<T> classType) {
        Set<CsvHeader> expectedCsvHeaders = normalizeCsvHeaders(getExpectedHeadersForClass(classType));
        Set<String> actualHeaders = normalizeHeaders(new HashSet<>(Arrays.asList(headers)));
        return expectedCsvHeaders.stream().map(CsvHeader::getHeader).collect(Collectors.toSet()).containsAll(actualHeaders)
                && actualHeaders.containsAll(expectedCsvHeaders.stream().filter(CsvHeader::getRequired).map(CsvHeader::getHeader).collect(Collectors.toSet()));

    }

    /**
     * <p>This method is used to get the expected headers for a given class type.</p>
     * <p>It takes a Class type as input and returns a Set of CsvHeader objects.</p>
     *
     * <p>The method works as follows:</p>
     * <ol>
     * <li>It first gets all the declared fields of the class type.</li>
     * <li>It then filters out the fields that are annotated with CsvBindByName.</li>
     * <li>For each remaining field, it gets the CsvBindByName annotation and checks if the column attribute of the annotation is empty.</li>
     * <li>If the column attribute is not empty, it creates a CsvHeader object with the column attribute as the header and the required attribute as the required status.</li>
     * <li>If the column attribute is empty, it returns null.</li>
     * <li>It finally collects all the CsvHeader objects into a Set and returns it.</li>
     * </ol>
     *
     * @param classType The class type for which the expected headers are to be determined.
     * @return A Set of CsvHeader objects representing the expected headers.
     */
    private static <T> Set<CsvHeader> getExpectedHeadersForClass(Class<T> classType) {
        return Arrays.stream(classType.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(CsvBindByName.class))
                .map(field -> {
                    CsvBindByName bindByName = field.getAnnotation(CsvBindByName.class);
                    if (!bindByName.column().isEmpty()) {
                        return CsvHeader.builder()
                                .header(bindByName.column())
                                .required(bindByName.required())
                                .build();
                    }
                    return null;
                })
                .collect(Collectors.toSet());
    }

    /**
     * This method is used to normalize the headers from a CSV file.
     * <li> It takes a Set of header strings as input, and returns a new Set where each header string has been trimmed and converted to lowercase.</li>
     * <li> This is done to ensure that the headers can be correctly matched against the expected headers, regardless of leading/trailing whitespace or case.</li>
     *
     * @param headers The Set of header strings to be normalized.
     * @return A new Set of normalized header strings.
     */
    private static Set<String> normalizeHeaders(Set<String> headers) {
        return headers.stream().filter(Objects::nonNull)
                .map(String::toLowerCase).map(String::trim)
                .collect(Collectors.toSet());
    }

    /**
     * This method is used to normalize CsvHeader objects.
     * It takes a Set of CsvHeader objects as input, and returns a new Set where each CsvHeader object has been modified as follows:
     * <li> The header string of the CsvHeader object has been trimmed and converted to lowercase.</li>
     * <li> This is done to ensure that the headers can be correctly matched against the actual headers from the CSV file, regardless of leading/trailing whitespace or case.</li>
     *
     * @param headers The Set of CsvHeader objects to be normalized.
     * @return A new Set of normalized CsvHeader objects.
     */
    private static Set<CsvHeader> normalizeCsvHeaders(Set<CsvHeader> headers) {
        return headers.stream().filter(Objects::nonNull).map(csvHeader -> {
            csvHeader.setHeader(csvHeader.getHeader().toLowerCase().trim());
            return csvHeader;
        }).collect(Collectors.toSet());
    }

    /**
     * This method is used to get the media type of a file.
     * It first checks if the file is null or empty, if so, it throws a CustomException.
     * If the file is not null and not empty, it parses the content type of the file to a MediaType object and returns it.
     *
     * @param file The file for which the media type is to be determined.
     * @return The media type of the file.
     * @throws CustomException if the file is null or empty.
     */
    private static MediaType getMediaType(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new CustomException("File is null or empty.");
        }
        return MediaType.parseMediaType(file.getContentType());
    }
}
