package com.onemount.infrastructure.adapters;

import com.onemount.domain.ports.spi.BasePersistence;
import com.onemount.infrastructure.commons.Page;
import com.onemount.infrastructure.repo.mappers.BaseMapper;
import com.onemount.infrastructure.utils.CollectionUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
public class JpaPersistence<M, T, ID, R extends JpaRepository<T, ID>> implements BasePersistence<M, ID> {

    protected final R repo;
    protected final BaseMapper<M, T> mapper;

    public JpaPersistence(R repo, BaseMapper<M, T> mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public M save(M data) {
        var saved = repo.save(mapper.toEntity(data));
        return mapper.toDomainModel(saved);
    }

    @Override
    public void deleteById(ID id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<M> findById(ID id) {
        var op = repo.findById(id);
        return op.map(mapper::toDomainModel);
    }

    @Override
    public Page<M> findAll(int pageIndex, int pageSize) {
        var realPageIndex = Math.max(0, pageIndex - 1);
        var page = repo.findAll(PageRequest.of(realPageIndex, pageSize));
        var result = CollectionUtils.toList(page.getContent(), mapper::toDomainModel);
        return new Page<>(page.getTotalElements(), pageIndex, pageSize, result);
    }

    @Override
    public boolean existById(ID id) {
        return repo.existsById(id);
    }
}
