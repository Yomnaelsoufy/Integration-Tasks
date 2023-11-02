package com.example.timer.router;

import java.sql.SQLIntegrityConstraintViolationException;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.mysql.cj.jdbc.MysqlDataSource;
@Component
public class TimerRouter extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		MysqlDataSource datasource = new MysqlDataSource();
		datasource.setUrl("jdbc:mysql://localhost:3306/NafathDB");
		datasource.setUser("root");
		datasource.setPassword("root");

		getContext().getRegistry().bind("myDataSource", datasource);
		
		from("timer://test?period=5000")
		.doTry()
		.setBody(constant( "INSERT INTO nafathInfo (poinum, poitype, mobilenum) VALUES ('32453243', 'IQA', '234442345')"))
        .to("jdbc:myDataSource")
		.log("direct:handleSuccessResponse")
		.doCatch(SQLIntegrityConstraintViolationException.class)
		.log("Can't insert")
		.end()
		;
	}

}
