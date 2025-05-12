package com.example.gestionFuncionarios.service;

import com.example.gestionFuncionarios.model.Funcionarios;
import com.example.gestionFuncionarios.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FuncionariosService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionarios> findAll(){
        return funcionarioRepository.findAll();
    }

    public Funcionarios findById(long id){
        return funcionarioRepository.findById(id).get();
    }

    public Funcionarios save(Funcionarios funcionarios){
        return funcionarioRepository.save(funcionarios);
    }

    public void delete(Long id) {
        funcionarioRepository.deleteById(id);
    }
}
