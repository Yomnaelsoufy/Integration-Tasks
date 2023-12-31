package com.example.main.api.router;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.example.main.api.processor.*;
@Component
public class SecondServiceRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:secondService")
            .setHeader(Exchange.HTTP_METHOD, constant("GET"))
            .process(new SecondServiceProcessor())
            .setHeader(Exchange.HTTP_QUERY, simple("IDType=${header.IDType}&IDNumber=${header.IDNumber}&mobileNumber=${header.mobileNumber}"))
            .log("Request Query String: ${header.CamelHttpQuery}")
            .to("http://localhost:7882/v1/tahaqoq/mobile-verification?bridgeEndpoint=true")
            .log("Second Service call Response ${body}")
            .process(new SecondApiResponseProcessor())
            .choice()
                .when(simple("${header.CamelHttpResponseCode} == 200 && ${header.ResponseStatusCode} == 'I000000' && ${header.isOwner} == true"))
                    .to("direct:thirdService")
                .otherwise()
                    .log("Failed to verify Tahqooq");
    }
}

