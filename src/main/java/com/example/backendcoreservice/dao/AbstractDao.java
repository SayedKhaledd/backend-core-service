package com.example.backendcoreservice.dao;

import com.example.backendcoreservice.api.pagination.PaginationRequest;
import com.example.backendcoreservice.model.AbstractEntity;
import org.slf4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AbstractDao<E extends AbstractEntity, T extends JpaRepository<E, Long>> {

    Logger log = org.slf4j.LoggerFactory.getLogger(AbstractDao.class);

    T getRepo();

    default Optional<E> findById(Long id) {
        return getRepo().findById(id);
    }

    default E create(E entity) {
        log.info("AbstractDao: create() was called -  entity{}", entity);
        return getRepo().save(entity);
    }

    default E update(E entity) {
        log.info("AbstractDao: update() was called -  entity{}", entity);
        return getRepo().save(entity);
    }

    default List<E> findAll() {
        return getRepo().findAll();
    }

    default Pageable getPageRequest(PaginationRequest<?> paginationRequest) {
        return PageRequest.of(paginationRequest.getPageNumber() - 1, paginationRequest.getPageSize(),
                paginationRequest.getSortDesc() ? Sort.by(paginationRequest.getSortBy()).descending() :
                        Sort.by(paginationRequest.getSortBy()).ascending());
    }

    default Boolean existsById(Long id) {
        log.info("AbstractDao: existsById() was called -  id{}", id);
        return getRepo().existsById(id);
    }

    default void deleteById(Long id) {
        log.info("AbstractDao: deleteById() was called -  id{}", id);
        getRepo().deleteById(id);
    }
}
