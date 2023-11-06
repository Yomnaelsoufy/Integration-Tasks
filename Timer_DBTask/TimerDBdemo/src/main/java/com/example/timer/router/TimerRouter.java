package com.example.timer.router;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.timer.model.NafathInfo;
import com.example.timer.service.NafathInfoService;

@Component
public class TimerRouter extends RouteBuilder {
	@Autowired
	private NafathInfoService nafathInfoService;
//	private final SessionFactory sessionFactory;
//
//	public TimerRouter(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}

	@Override
	public void configure() throws Exception {
		  from("quartz://myTimer?cron=0+34+11+6+*+?").
//		from("timer:first-timer?period=10000").
		  log("saving to DB...")
		.process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                NafathInfo entity = new NafathInfo("56765", "564657", "5432456");
                nafathInfoService.save(entity);
            }
        });
	}
}
