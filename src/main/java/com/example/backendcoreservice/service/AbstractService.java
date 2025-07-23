package com.example.backendcoreservice.service;

import com.example.backendcoreservice.api.pagination.PaginationResponse;
import com.example.backendcoreservice.dao.AbstractDao;
import com.example.backendcoreservice.dto.Dto;
import com.example.backendcoreservice.model.AbstractEntity;
import com.example.backendcoreservice.transformer.AbstractTransformer;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AbstractService<Entity extends AbstractEntity, DTO extends Dto, Transformer extends AbstractTransformer, Dao extends AbstractDao> {
    Logger log = org.slf4j.LoggerFactory.getLogger(AbstractService.class);

    Transformer getTransformer();

    Dao getDao();

    default Entity findEntityById(Long id) throws Throwable {
        return (Entity) getDao().findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    default DTO findById(Long id) {
        Optional<Entity> dto = getDao().findById(id);
        if (dto.isPresent()) {
            return (DTO) getTransformer().transformEntityToDto(dto.get());
        }
        throw new EntityNotFoundException(String.format("Entity with id %s does not exist", id));
    }

    default List<DTO> findAll() {
        return getTransformer().transformEntitiesToDtos(getDao().findAll());
    }

    default DTO create(DTO dto) {
        log.info("AbstractService: create() was called -  dto{}", dto);
        Entity entity = (Entity) getTransformer().transformDtoToEntity(dto);
        entity = doBeforeCreate(entity, dto);
        return (DTO) getTransformer().transformEntityToDto(getDao().create(entity));
    }
    default List<DTO> create(List<DTO> dtos) {
        log.info("AbstractService: create() was called -  dtos{}", dtos);
        List<Entity> entities = (List<Entity>) getTransformer().transformDtosToEntities(dtos);
        entities = doBeforeCreate(entities, dtos);
        return (List<DTO>) getTransformer().transformEntitiesToDtos(getDao().create(entities));
    }

    default Entity doBeforeCreate(Entity entity, DTO dto) {
        return entity;
    }

    default List<Entity> doBeforeCreate(List<Entity> entities, List<DTO> dtos) {
        return entities;
    }

    default Entity doBeforeUpdate(Entity entity, DTO dto) {
        return entity;
    }

    default DTO update(DTO dto, Long id) {
        log.info("AbstractService: update() was called -  dto{}", dto);
        if (id == null || findById(id) == null)
            throw new EntityNotFoundException(String.format("Entity with id %s does not exist", id));

        Entity entity = (Entity) getTransformer().transformDtoToEntity(dto);
        entity = doBeforeUpdate(entity, dto);
        return (DTO) getTransformer().transformEntityToDto(getDao().update(entity));
    }

    default PaginationResponse<DTO> buildPaginationResponse(Page<Entity> entities) {
        return (PaginationResponse<DTO>) PaginationResponse.paginationResponseBuilder().
                totalNumberOfPages((long) entities.getTotalPages()).
                totalNumberOfElements(entities.getTotalElements()).
                result(getTransformer().transformEntitiesToDtos(entities.getContent())).
                isFirst(entities.isFirst()).
                isLast(entities.isLast()).
                pageNumber(entities.getNumber() + 1).
                pageSize(entities.getSize()).
                build();
    }

    default Boolean existsById(Long id) {
        log.info("AbstractService: existsById() was called -  id{}", id);
        return getDao().existsById(id);
    }

    default void delete(Long id) {
        log.info("AbstractService: delete() was called -  id{}", id);
        if (!existsById(id))
            throw new EntityNotFoundException("Entity with id %s does not exist" + id);
        getDao().deleteById(id);
    }

    String getEntityName();

}
