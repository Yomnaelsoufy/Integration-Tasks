package com.example.main.api.router;

import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.main.api.mapper.HeaderMapper;
import com.example.main.api.model.Header;
import com.mysql.cj.jdbc.MysqlDataSource;

@Component
public class FourthServiceRouter extends RouteBuilder {

//	private Header header ;

	@Override
	public void configure() throws Exception {
		MysqlDataSource datasource = new MysqlDataSource();
		datasource.setUrl("jdbc:mysql://localhost:3306/NafathDB");
		datasource.setUser("root");
		datasource.setPassword("hanaaElshamy1");

		getContext().getRegistry().bind("myDataSource", datasource);

		from("direct:fourthService").doTry()
        .setBody(simple(
            "INSERT INTO nafathInfo (id,poinum, poitype, mobilenum) VALUES (1,'${header.IDNumber}', '${header.IDType}', '${header.mobileNumber}')"))
        .log("{$body}").to("jdbc:myDataSource").log("Data inserted successfully into nafathtable")
        .setBody(constant("{\"id\": \"1081871111\", \"action\": \"SpRequest\", \"service\":\"Login\" }"))
        .log("fourth req: ${body}").setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .to("http://localhost:8086/v1/nafath-app/id-verification/initiate?bridgeEndpoint=true")
        .process(exchange -> {
            ResponseBody responseBody = exchange.getIn().getBody(ResponseBody.class);
//            HeaderMapper headerMapper = // Inject or create an instance of the generated mapper
//            Header header = headerMapper.mapResponseToHeader(responseBody);
//            exchange.getIn().setHeader("header", header);
        })
        .doCatch(SQLIntegrityConstraintViolationException.class)
            .log("Duplicate primary key detected. Handling the exception...")
            .to("direct:SQLError")
        .end();
//		from("direct:fourthService").setBody(simple(
//				"INSERT INTO nafathInfo (id, poinum, poitype, mobilenum) VALUES (1,'${header.IDNumber}', '${header.IDType}', '${header.mobileNumber}')"))
//				.log("{$body}").to("jdbc:myDataSource").log("Data inserted successfully into nafathtable")
//				.setBody(constant("{\"id\": \"1081871111\", \"action\": \"SpRequest\", \"service\":\"Login\" }"))
//				.log("fourth req: ${body}").setHeader(Exchange.HTTP_METHOD, constant("POST"))
//				.to("http://localhost:8086/v1/nafath-app/id-verification/initiate?bridgeEndpoint=true");

	}
}

