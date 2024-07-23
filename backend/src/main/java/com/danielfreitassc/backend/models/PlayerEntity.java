package com.danielfreitassc.backend.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;

@Entity
@Table(name = "players")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String name;

    @Email
    @Column(name = "e-mail")
    private String email;

    @Max(value = 14,message = "Número não pode ter mais que quatorze digitos")
    @Column(name = "phone-number")
    private String phoneNumber;

    @Column(name = "codiname")
    private String codename;

    @Column(name = "grupo")
    private String group;
}
