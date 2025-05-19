package com.example.gestionFuncionarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
//ke
@Entity
@Table(name= "funcionario")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Funcionarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true, length = 13, nullable=false)
    private String run;

    @Column(nullable=false)
    private String nombreCompleto;

    @Column(nullable=false)
    private String correo;

    @Column(nullable=false)
    private String telefono;

    @Column(nullable=false)
    private String rol;

    @Column(nullable=false)
    private String estado;

    @Column(nullable=false)
    private String fechaIngreso;
}
