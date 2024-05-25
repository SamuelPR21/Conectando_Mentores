package com.example.crud.roles.CRUD_ROLES.Service.Documentos;


import com.example.crud.roles.CRUD_ROLES.model.Documentos;
import com.example.crud.roles.CRUD_ROLES.repository.DocumentosRepository;

import com.example.crud.roles.CRUD_ROLES.response.ArchivosRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentosService  implements DocumentService{

    @Autowired
    private DocumentosRepository documentosRepository;


    //Carga archvi a BBDD
    @Override
    public Documentos store(MultipartFile file) throws  IOException{
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        Documentos documentos = new Documentos();
        documentos.setNombreDocumentos(filename);
        documentos.setType(file.getContentType());
        documentos.setData(file.getBytes());

        return documentosRepository.save(documentos);
    }

    //Descargar archvio BBDD
    @Override
    public Optional<Documentos> getFile(int id) throws FileNotFoundException{

        Optional<Documentos> file = documentosRepository.findById(id);
        if(file.isPresent()){
            return file;
        }

        throw new FileNotFoundException();
    };


    //Consultar lista archivos
    @Override
    public List<ArchivosRespuesta> getAllfile(){
        List<ArchivosRespuesta> files = documentosRepository.findAll().stream().map(dbfile -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("api/fileManager/files/")
                    .path(String.valueOf(dbfile.getIdDocumemtos()))
                    .toUriString();

            return ArchivosRespuesta.builder()
                    .name(dbfile.getNombreDocumentos())
                    .url(fileDownloadUri)
                    .type(dbfile.getType())
                    .size(dbfile.getData().length).build();
        }).collect(Collectors.toList());

        return files;
    }

}