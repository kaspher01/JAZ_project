package com.hoth.data.mappers;

public interface IMapEntities<TDto, TEntity> {
    TEntity map(TDto dto);
    TEntity map(TDto dto, TEntity entity);
}
