package com.example.gestionFuncionarios.repository;

import com.example.gestionFuncionarios.model.Funcionarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionarios, Long> {
    List<Funcionarios> findbyApellidos
}
