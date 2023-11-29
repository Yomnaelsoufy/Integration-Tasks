package com.example.main.api.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SecondServiceProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String responseBody = exchange.getIn().getBody(String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        String mobileNumber = jsonNode.at("/data/mobileNumber").asText();
        String IDNumber = jsonNode.at("/data/poi/poiNumber").asText();
        String IDType = jsonNode.at("/data/poi/poiType").asText();

        exchange.getIn().setHeader("mobileNumber", mobileNumber);
        exchange.getIn().setHeader("IDNumber", IDNumber);
        exchange.getIn().setHeader("IDType", IDType);

    }
}
