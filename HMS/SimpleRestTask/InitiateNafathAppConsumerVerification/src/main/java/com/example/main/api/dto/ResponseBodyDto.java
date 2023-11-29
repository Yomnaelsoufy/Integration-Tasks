package com.example.main.api.dto;

import com.example.main.api.model.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseBodyDto {
    private String data;
    private ResponseHeader header;

}