package com.personal.FileSharing.service;

import com.personal.FileSharing.entity.Files;
import com.personal.FileSharing.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FilesService {
    final Path uploadDir= Paths.get("src/main/resources/static/uploadedFiles");
    private final FilesRepository filesRepository;
    @Autowired
    public FilesService(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    public List<Files>listAllFiles(){
        return filesRepository.findAll();
    }

    public Files getFilesById(int id){
        return filesRepository.findById(id).orElse(null);
    }

    public void saveFile(MultipartFile file) throws IOException {
        if(!java.nio.file.Files.exists(uploadDir)){
            java.nio.file.Files.createDirectories(uploadDir);
        }
        String filename= StringUtils.cleanPath(file.getOriginalFilename());
        Path path=uploadDir.resolve(filename);
        java.nio.file.Files.copy(file.getInputStream(),path);
        Files files=new Files();
        files.setFilename(filename);
        files.setFiletype(file.getContentType());
        filesRepository.save(files);
    }
    public Resource loadFileAsResource(String filename){
        try{
            Path path=uploadDir.resolve(filename);
            Resource resource=new UrlResource(path.toUri());
            if(resource.exists())return resource;
            else throw new RuntimeException("file not found");
        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found");
        }
    }
}
