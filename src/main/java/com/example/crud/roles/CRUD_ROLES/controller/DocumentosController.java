package com.example.crud.roles.CRUD_ROLES.controller;


import com.example.crud.roles.CRUD_ROLES.Service.DocumentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
public class DocumentosController {

    @Autowired
    DocumentosService documentosService;

    //Subir archivos
    @PostMapping("/enviable")
    private ResponseEntity<String> subirDocumentos(@RequestParam("file") MultipartFile file) throws Exception{
        System.out.println("entró a enviable");
        return new ResponseEntity<>(documentosService.handleFileUpload(file) , HttpStatus.OK);
    }
}
