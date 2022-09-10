package com.onemount.infrastructure.repo.mappers;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
public interface BaseMapper<M, T> {

    M toDomainModel(T t);

    T toEntity(M m);
}
