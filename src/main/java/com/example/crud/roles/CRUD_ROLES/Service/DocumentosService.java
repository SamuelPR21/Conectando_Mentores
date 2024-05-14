package com.example.crud.roles.CRUD_ROLES.Service;

import com.example.crud.roles.CRUD_ROLES.repository.DocumentosRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class DocumentosService implements DocumentosRepository {

    @Override
    public String handleFileUpload(MultipartFile file) throws Exception {
       try {
           String fileName = UUID.randomUUID().toString();
           byte[] bytes = file.getBytes();
           String nombreArchivoOriginal = file.getOriginalFilename();


           //Otro filtro referwnete al tamaño del archivo
           long tamañoArchivo = file.getSize();
           long maxTamañoArchivo =  8 *  1024 * 1000;

           if (tamañoArchivo > maxTamañoArchivo){
               return "El tamaño del archizo debe ser igual o menor a 8 MB ";
           }

           //Filtro para subir archivos
           if(
               !nombreArchivoOriginal.endsWith(".jpg") &&
               !nombreArchivoOriginal.endsWith(".jpeg") &&
               !nombreArchivoOriginal.endsWith(".docx") &&
               !nombreArchivoOriginal.endsWith(".pdf") &&
               !nombreArchivoOriginal.endsWith(".xlsx")
           ){
               return "Solo los archivos aceptados";
           }

           //Cambio el nombre del archivo
           String extensionArchivo = nombreArchivoOriginal.substring(nombreArchivoOriginal.lastIndexOf("."));
           String newnombreArchivoOriginal = fileName + extensionArchivo;

           //Carpeta donde se alohja el archivo
           File folder = new File("src/main/resourcer/documentos/");
           if(!folder.exists()){
               folder.mkdirs();

           }

           Path path= Paths.get("src/main/resourcer/documentos/" + newnombreArchivoOriginal);
           Files.write(path, bytes);
           return "Archivo subido exitosamente";

       }catch (Exception e){
           throw new Exception(e.getMessage());
       }
    }

}
