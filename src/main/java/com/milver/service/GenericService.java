package com.milver.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericService <E, ID>{

    /**
     * @param pageable
     * @return list of Entities
     */
    Page<E> findAllEntities(Pageable pageable);

    /**
     * @param entity
     * @return a after create theEntities
     */
    E save(E entity);

    /**
     * @param entity
     * @return a after create theEntities
     */
    E update(E entity);

    /**
     * @param id
     * @return
     */
    E findEntity(ID id);

    /**
     * @param id
     */
    void deleteEntity(ID id);
}
