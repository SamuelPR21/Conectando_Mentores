package com.example.crud.roles.CRUD_ROLES.Service.Documentos;

import com.example.crud.roles.CRUD_ROLES.model.Documentos;
import com.example.crud.roles.CRUD_ROLES.response.ArchivosRespuesta;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DocumentService {


    // Carga archivo a BBDD y lo asocia a un usuario
    Documentos store(MultipartFile file, int userId) throws IOException;

    // Descargar archivo de BBDD
    Optional<Documentos> getFile(int id) throws FileNotFoundException;

    // Consultar lista de archivos
    List<ArchivosRespuesta> getAllfile();

    // Consultar lista de archivos por usuario
    List<ArchivosRespuesta> getAllFilesByUserId(int userId);

    }

