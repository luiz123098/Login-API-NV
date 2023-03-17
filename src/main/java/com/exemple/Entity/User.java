package com.exemple.Entity;


import com.exemple.ModelInterface.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
//ANNOTATIONS FOR USERS TABLE CREATION
@Entity
@Table( name = "usuarios",
        uniqueConstraints ={
                            @UniqueConstraint(columnNames = "id"),
                            @UniqueConstraint(columnNames = "login"),
                            @UniqueConstraint(columnNames = "cpf")
                            })
public class User {
//CAN BE CHANGED AS NEEDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login",nullable = false)
    private String login;
    @Column(name = "senha",nullable = false)
    private String password;
    @Column(name = "nome",nullable = false)
    private String name;
    @Column(name = "cpf",nullable = false)
    private String cpf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
