package com.personal.FileSharing.controller;

import com.personal.FileSharing.service.FilesService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/ops")
public class OpsUserController {
    private final FilesService filesService;

    public OpsUserController(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?>uploadFile(@RequestParam("file")MultipartFile file) {

        String fileType=file.getContentType();
        if (fileType == null || (!fileType.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation")
                && !fileType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                && !fileType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))) {
            return ResponseEntity.badRequest().body("Invalid file type. Only pptx, docx, and xlsx are allowed.");
        }
            try {
                filesService.saveFile(file);
                return ResponseEntity.ok("File uploaded");
            }catch(IOException e){
            return ResponseEntity.status(500).body("File upload failed");
        }
    }
}
