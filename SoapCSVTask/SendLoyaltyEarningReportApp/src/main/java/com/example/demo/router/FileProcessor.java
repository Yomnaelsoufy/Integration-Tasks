package com.example.demo.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
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
            .process(this::prepareSoapRequest)
            .log("Request Body: ${body}")
            .log("Request Headers: ${headers}")
            .to("https://run.mocky.io/v3/318d57a6-9e3f-40ca-ba65-d0511d977e98")
            .log("Response Body: ${body}");
    }

    private void renameFile(Exchange exchange) throws Exception {
        String fileName = exchange.getIn().getHeader("CamelFileName").toString();
        fileName = fileName.replace(".", "_30.");
        exchange.getIn().setHeader("CamelFileName", fileName);
    }

    private void prepareSoapRequest(Exchange exchange) throws Exception {
        String soapRequest = createSoapRequest(exchange.getIn().getHeader("CamelFileName").toString());
        exchange.getIn().setHeader("CamelHttpMethod", "POST");
        exchange.getIn().setHeader("Content-Type", "text/xml");
        exchange.getIn().setHeader("SOAPAction", "YourSOAPAction");
        exchange.getIn().setBody(soapRequest);

        DataHandler fileDataHandler = new DataHandler(
            new FileDataSource(exchange.getIn().getHeader("CamelFileName", String.class))
        );
        exchange.getIn().setHeader("fileAttachment", fileDataHandler);
    }

    private static String createSoapRequest(String filename) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:fil=\"http://www.fintechwalletwebservices.com/FileUpload\">\n"
            + "   <soapenv:Header>\n" + "      <fil:RequestContext>\n"
            + "         <fil:RequestID>?</fil:RequestID>\n" + "         <fil:ServiceName>?</fil:ServiceName>\n"
            + "      </fil:RequestContext>\n" + "   </soapenv:Header>\n" + "   <soapenv:Body>\n"
            + "      <fil:FileUploadRequest>\n" + "         <fil:FileID>?</fil:FileID>\n"
            + "         <fil:FileType>?</fil:FileType>\n" + "         <fil:FileName>" + filename
            + "</fil:FileName>\n" + "         <fil:AttachmentID>?</fil:AttachmentID>\n"
            + "      </fil:FileUploadRequest>\n" + "   </soapenv:Body>\n" + "</soapenv:Envelope>";
    }
}
