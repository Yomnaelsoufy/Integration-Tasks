package com.example.main.api.router;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.apache.camel.component.es.ElasticsearchComponent;

@Component
public class FirstServiceRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		ElasticsearchComponent elasticsearchComponent = new ElasticsearchComponent();
		elasticsearchComponent.setHostAddresses("localhost:9200");

		getContext().addComponent("elasticsearch", elasticsearchComponent);
		rest("/v1/consumers/id-verification/nafath-app/initiate").post().to("direct:firstService");
		from("direct:firstService")
				.log("body: ${body}")
				.process(new BuildBodyProcessor())
				.to("elasticsearch://elasticsearch?operation=Index&indexName=twitter")
				.setBody(constant("{\"verificationSource\": \"Bank\"}")).toD(
						"http://localhost:8085/v1/consumers/234/id-verification/validate?bridgeEndpoint=true")
				.process(new BuildBodyProcessor())
				.to("elasticsearch://elasticsearch?operation=Index&indexName=twitter");

	}
}

class BuildBodyProcessor implements Processor {

	@Override
	public void process(org.apache.camel.Exchange exchange) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("user", "hi");
		map.put("postDate", "2021-03-01");
		map.put("message", exchange.getIn().getBody(String.class));
		exchange.getIn().setBody(map);
	}
}