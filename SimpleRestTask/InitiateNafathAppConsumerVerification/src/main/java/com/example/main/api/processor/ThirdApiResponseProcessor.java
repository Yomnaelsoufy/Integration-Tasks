package com.example.main.api.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ThirdApiResponseProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String responseBody = exchange.getIn().getBody(String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        String statusCode = jsonNode.at("/header/status/code").asText();
        exchange.getIn().setHeader("ResponseStatusCode", statusCode);
    }
}
