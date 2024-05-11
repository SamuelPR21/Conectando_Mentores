package com.example.crud.roles.CRUD_ROLES.repository;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentosRepository {

    //Preparacion
    void init();

    //Almacenar un archivo
    String store(MultipartFile file);

    //Cargar un archivo
    Resource loadResource(String filename);


}
