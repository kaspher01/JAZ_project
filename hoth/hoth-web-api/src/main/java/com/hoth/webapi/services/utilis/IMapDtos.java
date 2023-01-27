package com.hoth.webapi.services.utilis;

import com.hoth.client.contract.SpeciesDto;
import com.hoth.data.model.Species;
import org.springframework.stereotype.Component;

public interface IMapDtos<TEntity, TDto> {
    TDto map(TEntity entity);
    TDto map(TEntity entity, TDto dto);

}
