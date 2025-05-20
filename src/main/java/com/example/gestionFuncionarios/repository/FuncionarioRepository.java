package com.example.gestionFuncionarios.repository;

import com.example.gestionFuncionarios.model.Funcionarios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionarios, Long> {
    
    List<Funcionarios> findById(String id);

    List<Funcionarios> findByNombre(String nombre);

}
