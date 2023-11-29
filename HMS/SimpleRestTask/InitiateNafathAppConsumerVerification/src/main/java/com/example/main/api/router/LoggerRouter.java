package com.example.main.api.router;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.example.main.api.processor.LoggerProcessor;

@Component
public class LoggerRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:Logger")
				.process(new LoggerProcessor());
	}

}
