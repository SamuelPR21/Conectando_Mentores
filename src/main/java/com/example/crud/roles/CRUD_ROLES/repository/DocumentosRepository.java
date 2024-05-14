package com.example.crud.roles.CRUD_ROLES.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface DocumentosRepository {

    String handleFileUpload (MultipartFile file) throws Exception;

}
