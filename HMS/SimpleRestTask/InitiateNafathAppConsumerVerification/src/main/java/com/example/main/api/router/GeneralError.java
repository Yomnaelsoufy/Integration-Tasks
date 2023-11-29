package com.example.main.api.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
@Component
public class GeneralError extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:handleGeneralErrorResponse")
	    .setBody(simple("{\"header\":{\"requestId\":\"6b967d8c-efd1-4e03-a6d2-66b73f71eee8\",\"status\":{\"code\":\"E99999\",\"description\":\"Failure\"}}}"));

		
	}

}
