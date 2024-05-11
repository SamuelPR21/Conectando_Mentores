package com.example.crud.roles.CRUD_ROLES.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "documentos")
public class Documentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDocumemtos;
    private String nombreDocumentos;
    private String url;
    private int materia_id;
    private int user_id;
}
