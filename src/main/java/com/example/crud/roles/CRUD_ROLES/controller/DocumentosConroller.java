package com.example.crud.roles.CRUD_ROLES.controller;


import com.example.crud.roles.CRUD_ROLES.Service.Documentos.DocumentService;
import com.example.crud.roles.CRUD_ROLES.model.Documentos;
import com.example.crud.roles.CRUD_ROLES.response.ArchivosRespuesta;
import com.example.crud.roles.CRUD_ROLES.response.MensajeRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/fileManager")
public class DocumentosConroller {

    @Autowired
    private DocumentService documentService;


    @PostMapping("/upload")
    public ResponseEntity<MensajeRespuesta> uploadFile(@RequestParam("file") MultipartFile file,
                                                       @RequestParam("userId") int userId) throws IOException {

        documentService.store(file, userId);

        return ResponseEntity.status(HttpStatus.OK).body(new MensajeRespuesta("Archivo subido Exitosamente "));

    }


    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable int Id) throws FileNotFoundException{

        Documentos documentos = documentService.getFile(Id).orElseThrow(FileNotFoundException::new);
        String fileData = documentos.getData();

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documentos.getNombreDocumentos() + "\"")
                .body(fileData);
    }

    @GetMapping("/files")
    public  ResponseEntity<List<ArchivosRespuesta>> getListaArchvios(){
        List<ArchivosRespuesta> files = documentService.getAllfile();
        return ResponseEntity.status(HttpStatus.OK).body(files);

    }

    @GetMapping("/user/{userId}/files")
    public ResponseEntity<List<ArchivosRespuesta>> getListaArchviosPorUsuario(@PathVariable int userId) {
        List<ArchivosRespuesta> files = documentService.getAllFilesByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }


}
