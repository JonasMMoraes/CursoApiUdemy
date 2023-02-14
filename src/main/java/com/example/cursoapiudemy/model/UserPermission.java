package com.example.cursoapiudemy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_permission")

public class UserPermission implements Serializable {

    @Id
    @Column(name = "id_user")
    private long idUser;

    @Column(name = "id_permission")
    private long idPermission;

    public UserPermission() {
    }

    public UserPermission(long idUser, long idPermission) {
        this.idUser = idUser;
        this.idPermission = idPermission;
    }
}
