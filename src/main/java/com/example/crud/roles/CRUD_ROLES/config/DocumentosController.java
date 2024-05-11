package com.example.crud.roles.CRUD_ROLES.config;

import com.example.crud.roles.CRUD_ROLES.repository.DocumentosRepository;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DocumentosController implements DocumentosRepository {

    @Value("${media.location}")
   private  String mediaLocation;

    private Path rootLocation;

    @SneakyThrows
    @Override
    @PostConstruct
    public void init() {

        rootLocation = Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);

    }

    @Override
    public String store(MultipartFile file) {
        try {
            if(file.isEmpty()){
                throw new RuntimeException("No se pueden almacenar archivos vacios");
            }
            String filename = file.getOriginalFilename();
            Path destinationFile = rootLocation.resolve(Paths.get(filename))
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return filename;
        }catch (IOException e){
            throw new RuntimeException("No se pudo almacenar el archivo");
        }
    }

    @SneakyThrows
    @Override
    public Resource loadResource(String filename) {
            Path file  = rootLocation.resolve(filename);
            Resource resource = new UrlResource((file.toUri()));

            if (resource.exists() || resource.isReadable()){
                return  resource;
            }else {
                throw new RuntimeException("No se puede leer el archivo" + filename);
            }
     }

}

