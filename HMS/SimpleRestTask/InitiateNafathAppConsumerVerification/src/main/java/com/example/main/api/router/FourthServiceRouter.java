package com.example.main.api.router;

import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.es.ElasticsearchComponent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mysql.cj.jdbc.MysqlDataSource;

@Component
public class FourthServiceRouter extends RouteBuilder {

    // private Header header ;

    @Override
    public void configure() throws Exception {
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setUrl("jdbc:mysql://localhost:3306/NafathDB");
        datasource.setUser("root");
        datasource.setPassword("hanaaElshamy1");

        getContext().getRegistry().bind("myDataSource", datasource);

        ElasticsearchComponent elasticsearchComponent = new ElasticsearchComponent();
        elasticsearchComponent.setHostAddresses("localhost:9200");

        from("direct:fourthService").doTry()
                .setBody(simple(
                        "INSERT INTO nafathInfo (poinum, poitype, mobilenum) VALUES ('${header.IDNumber}', '${header.IDType}', '${header.mobileNumber}')"))
                .to("jdbc:myDataSource")
                .setBody(constant("{\"id\": \"1081871111\", \"action\": \"SpRequest\", \"service\":\"Login\" }"))
                .wireTap("direct:Logger")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .toD("http://localhost:8086/v1/nafath-app/id-verification/initiate?bridgeEndpoint=true")
                .wireTap("direct:Logger");
    }
}
