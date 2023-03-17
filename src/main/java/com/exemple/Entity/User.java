package com.exemple.Entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
//ANNOTATIONS FOR USERS TABLE CREATION
@Entity
@Table( name = "usuarios",
        uniqueConstraints ={
                            @UniqueConstraint(columnNames = "login"),
                            @UniqueConstraint(columnNames = "cpf")
                            })
public class User {
//CAN BE CHANGED AS NEEDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "login")
    private String login;
    @NotNull
    @Column(name = "senha")
    private String password;
    @NotNull
    @Column(name = "nome")
    private String name;
    @NotNull
    @Column(name = "cpf")
    private String cpf;

    public User() {

    }

    public long getId() {
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
