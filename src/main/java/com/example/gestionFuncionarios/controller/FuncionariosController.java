package com.example.gestionFuncionarios.controller;

import com.example.gestionFuncionarios.model.Funcionarios;
import com.example.gestionFuncionarios.service.FuncionariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/Gestion/Funcionarios")

public class FuncionariosController {

    @Autowired
    private FuncionariosService funcionariosService;

    @GetMapping
    public ResponseEntity<List<Funcionarios>> listar(){
            List<Funcionarios> funcionarios = funcionariosService.findAll();
            if (funcionarios.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(funcionarios);
    }

    @PostMapping
    public ResponseEntity<Funcionarios> guardar(@RequestBody Funcionarios funcionarios) {
        Funcionarios funcionarioNuevo = funcionariosService.save(funcionarios);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioNuevo);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Funcionarios> buscar(@PathVariable Integer id) {
        try{
            Funcionarios funcionarios = funcionariosService.findById(id);
            return ResponseEntity.ok(funcionarios);
        } catch ( Exception e ){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionarios> actualizar(@PathVariable Integer id, @RequestBody Funcionarios funcionarios) {
        try{
            Funcionarios fun = funcionariosService.findById(id);
            fun.setId(id);
            fun.setRun(funcionarios.getRun());
            fun.setNombreCompleto(funcionarios.getNombreCompleto());
            fun.setCorreo(funcionarios.getCorreo());
            fun.setTelefono(funcionarios.getTelefono());
            fun.setRol(funcionarios.getRol());
            fun.setEstado(funcionarios.getEstado());
            fun.setFechaIngreso(funcionarios.getFechaIngreso());

            funcionariosService.save(fun);
            return ResponseEntity.ok(funcionarios);
        } catch ( Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try{
            funcionariosService.delete(id);
            return ResponseEntity.noContent().build();
        } catch ( Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }
    

}
