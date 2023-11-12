package com.example.demo.router;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.component.spring.ws.SpringWebserviceConstants;
import org.springframework.stereotype.Component;

@Component
public class CallSoapCxf_our extends RouteBuilder {

        @Override
        public void configure() throws Exception {

                from("direct:sendReportInSoapServiceCxf_our")
                                .setHeader("fileID", constant("yomna"))
                                .setHeader("fileName", constant("ssgdfs"))
                                .setHeader("attachmentID", constant("4335"))
                                .setHeader("fileType", constant("TXT"))
                                .bean(FileUploadRequestBuild.class,
                                                "fileUploadRequestBuild(${header.fileID}, ${header.fileName}, ${header.attachmentID}, ${header.fileType})")
                                .setHeader("SOAPAction", constant("\"#POST\""))
                                .process(new FileUploadResponseProcessor())
                                .log("req-headers: ${headers}")
                                .log("req-body: ${body}")
                                .setHeader(CxfConstants.OPERATION_NAME,
                                                constant("FileUpload"))
                                .setHeader(CxfConstants.OPERATION_NAMESPACE,
                                                constant("http://www.fintechwalletwebservices.com/FileUpload"))
                                .to("cxf:http://localhost:8088/mockFileUploadBinding"
                                                + "?serviceClass=com.fintechwalletwebservices.fileupload.FileUploadPort"
                                                + "&dataFormat=POJO")
                                .log("response: ${body[0]}");
                // .log("respone headers: ${headers} ");

        }

}

class FileUploadResponseProcessor implements Processor {

        @Override
        public void process(org.apache.camel.Exchange exchange) throws Exception {
                String soapHeader = "<h:Header xmlns:h=\"http://www.webserviceX.NET/\"><h:MessageID>1234567890</h:MessageID><h:Nested><h:NestedID>1111</h:NestedID></h:Nested></h:Header>";
                exchange.getIn().setHeader(SpringWebserviceConstants.SPRING_WS_SOAP_HEADER, soapHeader);


        }
}
