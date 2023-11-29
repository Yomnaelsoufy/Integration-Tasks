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
                .setHeader(Exchange.HTTP_QUERY, simple(
                        "IDType=${header.IDType}&IDNumber=${header.IDNumber}&mobileNumber=${header.mobileNumber}"))
                .wireTap("direct:Logger")
                .to("http://localhost:7882/v1/tahaqoq/mobile-verification?bridgeEndpoint=true")
                .wireTap("direct:Logger")
                .process(new SecondApiResponseProcessor())
                .choice()
                .when(simple(
                        "${header.CamelHttpResponseCode} == 200 && ${header.ResponseStatusCode} == 'I000000' && ${header.isOwner} == true"))
                .to("direct:fourthService")
                .otherwise()
                .to("direct:handleGeneralErrorResponse");
    }
}
