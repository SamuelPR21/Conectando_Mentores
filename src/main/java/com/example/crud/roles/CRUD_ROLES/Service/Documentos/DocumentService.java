package com.example.crud.roles.CRUD_ROLES.Service.Documentos;

import com.example.crud.roles.CRUD_ROLES.model.Documentos;
import com.example.crud.roles.CRUD_ROLES.response.ArchivosRespuesta;
import org.apache.hc.core5.http.io.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DocumentService {

    Documentos store(MultipartFile file) throws  IOException;

    Optional<Documentos> getFile(int id) throws FileNotFoundException;

    List<ArchivosRespuesta> getAllfile();

    public interface FileService {
        // Permite almacenar o cargar archivos a la base de datos
        FileEntity store(MultipartFile file) throws IOException;

        //Permite descargar archivos
        Optional<Documentos> getFile(int id) throws FileNotFoundException;

        //Permite consultar la lista de archivos en nuetsra BBDD
        List<ArchivosRespuesta> getAllfile();



    }
}
