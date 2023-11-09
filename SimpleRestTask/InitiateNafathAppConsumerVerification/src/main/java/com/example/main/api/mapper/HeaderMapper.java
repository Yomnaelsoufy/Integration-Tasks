package com.example.main.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.main.api.model.Header;

@Mapper
public interface HeaderMapper {
    @Mapping(target = "statusDescription", source = "header.status.description", defaultValue = "Default Description")
    @Mapping(target = "statusDetails", source = "header.status.details", defaultValue = "Default Details")
    Header mapResponseToHeader(ResponseBody responseBody);
}