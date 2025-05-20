package com.example.gestionFuncionarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "funcionario")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Funcionarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true, length = 8, nullable=false)
    private String run;

    @Column(length = 1, nullable=false)
    private String dv;

    @Column(nullable=false)
    private String nombre;

    @Column(nullable=false)
    private String appaterno;

    @Column(nullable=false)
    private String apmaterno;

    @Column
    private String correo;

    @Column
    private String telefono;
}
