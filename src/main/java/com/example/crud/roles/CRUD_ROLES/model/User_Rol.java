package com.example.crud.roles.CRUD_ROLES.model;


import java.io.Serializable;
import java.util.Objects;

public class User_Rol implements Serializable {
    private int user_id;
    private int rolId;

    @Override
    public boolean equals( Object o){
        if (this == o) return true;
        if(!(o instanceof  User_Rol that))return false;
        return Objects.equals(user_id, that.user_id) && Objects.equals(rolId, that.rolId);

    }
    @Override
    public  int hashCode(){
        return Objects.hash(user_id, rolId);
    }



}
