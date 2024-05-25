package com.example.crud.roles.CRUD_ROLES.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "documentos")
public class Documentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDocumemtos;
    private String nombreDocumentos;
    private String materia;
    private String type;

    @Lob
    private byte[] data;
    private int user_id;


    public int getIdDocumemtos() {
        return idDocumemtos;
    }

    public void setIdDocumemtos(int idDocumemtos) {
        this.idDocumemtos = idDocumemtos;
    }

    public String getNombreDocumentos() {
        return nombreDocumentos;
    }

    public void setNombreDocumentos(String nombreDocumentos) {
        this.nombreDocumentos = nombreDocumentos;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
