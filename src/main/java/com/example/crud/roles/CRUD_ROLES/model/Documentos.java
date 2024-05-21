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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMateria_id() {
        return materia_id;
    }

    public void setMateria_id(int materia_id) {
        this.materia_id = materia_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
