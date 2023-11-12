package com.example.demo.router;


import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.attachment.AttachmentMessage;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;

@Component
public class FileProcessor extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:D:/files")
            .process(this::renameFile)
            .to("file:D:/another")
            .to("direct:soapCall");

        from("direct:soapCall")
        .to("direct:sendReportInSoapServiceCxf_our");
    }

    private void renameFile(Exchange exchange) throws Exception {
        String fileName = exchange.getIn().getHeader("CamelFileName").toString();
        fileName = fileName.replace(".", "_30.");
        exchange.getIn().setHeader("CamelFileName", fileName);
        File file = exchange.getIn().getBody(File.class);
        AttachmentMessage attMsg = exchange.getIn(AttachmentMessage.class);
                                attMsg.addAttachment(fileName,
                                        new DataHandler(new FileDataSource(file)));
    }
}
