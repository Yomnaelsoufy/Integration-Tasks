package com.example.main.api.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.main.api.dto.ResponseBodyDto;
import com.example.main.api.model.ApiResponseBody;


@Mapper(componentModel = "spring")
public interface ResponseBodyMapper {
    ResponseBodyMapper INSTANCE = Mappers.getMapper(ResponseBodyMapper.class);

    @Mapping(source = "body", target = "data")
    ResponseBodyDto toResponseBodyDto(ApiResponseBody apiResponseBody);

    @Mapping(source = "responseBodyDto.data", target = "body")
    ApiResponseBody toResponseBody(ResponseBodyDto responseBodyDto);
    
}