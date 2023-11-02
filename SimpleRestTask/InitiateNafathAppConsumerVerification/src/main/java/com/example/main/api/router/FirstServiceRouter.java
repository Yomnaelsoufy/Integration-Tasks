package com.example.main.api.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.stereotype.Component;

import com.example.main.api.processor.*;
import org.apache.camel.model.CatchDefinition;

@Component
public class FirstServiceRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("direct:firstService").log("My Service call Request: ${body}").process(new FirstServiceProcessor())
				.setBody(constant("{\"verificationSource\": \"Bank\"}")).doTry().to("direct:handleSuccessResponse")
				.doCatch(HttpOperationFailedException.class).to("direct:handleGeneralErrorResponse").end();

		from("direct:handleSuccessResponse").toD(
				"http://localhost:8085/v1/consumers/${header.consumerId}/id-verification/validate?bridgeEndpoint=true")
				.process(new FirstApiResponseProcessor()).choice().when(simple("${header.isTahakookVerified} == false"))
				.to("direct:secondService").otherwise().to("direct:fourthService").endChoice();
	}
}