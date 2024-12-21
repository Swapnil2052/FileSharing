package com.personal.FileSharing.controller;

import com.personal.FileSharing.entity.Files;
import com.personal.FileSharing.service.FilesService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientUserController {
    private final FilesService filesService;
   @Autowired
    public ClientUserController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping("/files")
    public ResponseEntity<?> listFiles(){
       List<Files>filesList=filesService.listAllFiles();
       return ResponseEntity.ok(filesList);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<?> downloadFile(@PathVariable int fileId){
       Files file=filesService.getFilesById(fileId);
       if(file!=null){
           Resource resource= filesService.loadFileAsResource(file.getFilename());
           return ResponseEntity.ok()
                   .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+resource.getFilename()+"\"" )
                   .body(resource);
       }
       return ResponseEntity.status(404).body(null);
    }
}
