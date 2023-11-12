package com.example.demo.router;
import org.springframework.stereotype.Component;

import com.fintechwalletwebservices.fileupload.FileTypeType;
import com.fintechwalletwebservices.fileupload.FileUploadRequestType;

@Component
public class FileUploadRequestBuild {

    public FileUploadRequestType fileUploadRequestBuild(String fileID, String fileName, String attachmentID,
            FileTypeType fileType) {
        FileUploadRequestType fileUploadRequestType = new FileUploadRequestType();
        fileUploadRequestType.setFileName(fileName);
        fileUploadRequestType.setFileID(fileID);
        fileUploadRequestType.setAttachmentID(attachmentID);
        fileUploadRequestType.setFileType(fileType);
        return fileUploadRequestType;
    }

}
