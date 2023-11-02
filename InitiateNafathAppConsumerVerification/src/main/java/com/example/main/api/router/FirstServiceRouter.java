package com.example.main.api.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.example.main.api.processor.*;

@Component
public class FirstServiceRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:firstService")
            .log("My Service call Request: ${body}")
            .process(new FirstServiceProcessor())
            .setBody(constant("{\"verificationSource\": \"Bank\"}"))
//            .log("first call req body: ${body}")
            .toD("http://localhost:8085/v1/consumers/${header.consumerId}/id-verification/validate?bridgeEndpoint=true")
//            .log("First Api call Response: ${body}")
            .process(new FirstApiResponseProcessor())
            .choice()
                .when(simple("${header.CamelHttpResponseCode} == 200 && ${header.ResponseStatusCode} == 'I000000'"))
                    .choice()
                        .when(simple("${header.isTahakookVerified} == false"))
                            .to("direct:secondService")
                        .otherwise()
                            .to("direct:fourthService")
                    .endChoice()
                .otherwise()
                    .log("Failed to verify Customer ID")
                .endChoice();
    }
}