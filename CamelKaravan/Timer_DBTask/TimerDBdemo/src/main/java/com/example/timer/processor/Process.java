package com.example.timer.processor;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import com.example.timer.model.NafathInfo;
import com.example.timer.service.NafathInfoService;

@Component
public class Process implements Processor {
    @Autowired
    private NafathInfoService nafathInfoService;

    @Override
    public void process(Exchange exchange) throws Exception {
        NafathInfo entity = new NafathInfo("543456765", "564657", "5432456");
        nafathInfoService.save(entity);
    }
}