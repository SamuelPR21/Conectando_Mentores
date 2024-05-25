package com.example.crud.roles.CRUD_ROLES.repository;

import com.example.crud.roles.CRUD_ROLES.model.Documentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface DocumentosRepository extends JpaRepository<Documentos, Integer> {

}
