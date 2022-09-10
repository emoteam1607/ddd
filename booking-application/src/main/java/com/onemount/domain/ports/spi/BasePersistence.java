package com.onemount.domain.ports.spi;

import com.onemount.infrastructure.commons.Page;

import java.util.Optional;

/**
 * M: model or dto
 * <p>
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
public interface BasePersistence<M, ID> {

    M save(M data);

    void deleteById(ID id);

    Optional<M> findById(ID id);

    Page<M> findAll(int pageIndex, int pageSize);

    boolean existById(ID id);
}
