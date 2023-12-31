package com.example.main.api.router;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.example.main.api.dto.ResponseBodyDto;
import com.example.main.api.mapper.ResponseBodyMapper;
import com.example.main.api.model.ApiResponseBody;
import com.example.main.api.processor.*;

@Component
public class ThirdServiceRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:thirdService")
            .setHeader(Exchange.HTTP_METHOD, constant("PUT"))
            .setBody(simple("{\"isTahakookVerified\": ${header.isTahakookVerified}, \"newMobileNumber\":${header.mobileNumber} , \"poi\": {\"poiNumber\": ${header.IDNumber}, \"poiType\":${header.IDType}}}"))
            .log("second req : ${body}")
            .to("http://localhost:8082/v1/consumers/mobile?bridgeEndpoint=true")
            .log("Third Service call Response ${body}")
            .unmarshal().json(JsonLibrary.Jackson,ResponseBodyDto.class)
            // .process(new ThirdApiResponseProcessor())
            .log("this is after unmarshal ${body}")
            .bean(ResponseBodyMapper.class,"toResponseBody")
            .choice()
                .when(simple("${header.CamelHttpResponseCode} == 200 && ${header.ResponseStatusCode} == 'I000000'"))
                    .to("direct:fourthService")
                .otherwise()
                    .log("Failed to Update mobile number");
    }
}
