package com.example.crud.roles.CRUD_ROLES.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "documentos")
public class Documentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDocumemtos;
    private String nombreDocumentos;
    private String materia;
    private String type;

    private String ruta;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private Users users;


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


//    public Users getUsers() {
//        return users;
//    }
//
//    public void setUsers(Users users) {
//        this.users = users;
//    }


    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
