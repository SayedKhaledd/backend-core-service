package com.example.backendcoreservice.util;

import com.example.backendcoreservice.exception.CustomException;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import static com.example.backendcoreservice.util.CsvValidator.validateHeadersFromFile;
import static com.example.backendcoreservice.util.CsvValidator.validateIsCsvFile;

@Slf4j
public class CsvUtil {

    /**
     * Reads and parses a CSV file into a list of objects.
     *
     * @param file      The CSV file to be read.
     * @param classType The class type of the objects to be created from CSV data.
     * @return A list of objects representing the CSV data.
     */
    public static <T> List<T> readFile(MultipartFile file, Class<T> classType) throws CustomException {
        log.info("CsvUtil: readFile() - called");
        validateIsCsvFile(file);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            return new CsvToBeanBuilder<T>(reader)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .withType(classType)
                    .withSeparator(',')
                    .build().parse();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new CustomException("Exception occurred while reading csv file: " + exception.getMessage());
        }

    }

    public static <T> List<T> readFileWithHeadersValidation(MultipartFile file, Class<T> classType) throws CustomException {
        log.info("CsvUtil: readFileWithHeadersValidation() - called");
        validateHeadersFromFile(file, classType);
        return readFile(file, classType);
    }
}
