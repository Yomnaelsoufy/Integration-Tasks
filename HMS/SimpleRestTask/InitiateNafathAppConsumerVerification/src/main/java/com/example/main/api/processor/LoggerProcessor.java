package com.example.main.api.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(LoggerProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        String httpMethod = exchange.getIn().getHeader("CamelHttpMethod", String.class);
        String statuscode = exchange.getIn().getHeader("CamelHttpResponseCode", String.class);
        String reqId = exchange.getIn().getHeader("requestId", String.class);
        String responseBody = exchange.getIn().getBody(String.class);

        String logMessage = String.format("StatusCode:%s, RequestID:%s, HTTP Method: %s, Response Body: %s", statuscode,
                reqId, httpMethod,
                responseBody);
        logger.info(logMessage);

        // Set the log message as the new body
        exchange.getIn().setBody(logMessage);
    }
}
