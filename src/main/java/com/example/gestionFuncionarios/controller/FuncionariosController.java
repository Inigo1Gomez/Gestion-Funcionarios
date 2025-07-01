package com.example.gestionFuncionarios.controller;

import com.example.gestionFuncionarios.model.Funcionarios;
import com.example.gestionFuncionarios.service.FuncionariosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Funcionarios", description = "Operaciones relacionadas con los funcionarios")
@RestController
@RequestMapping("/Gestion/Funcionarios")

public class FuncionariosController {

    @Autowired
    private FuncionariosService funcionariosService;

    @Operation(summary = "reportes de Funcionarios",description = "reportes de funcionarios")
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
    public ResponseEntity<EntityModel<Funcionarios>> buscar(@PathVariable Integer id) {
        try {
            Funcionarios funcionario = funcionariosService.findById(id);
            EntityModel<Funcionarios> resource = EntityModel.of(funcionario,
                linkTo(methodOn(FuncionariosController.class).buscar(id)).withSelfRel(),
                linkTo(methodOn(FuncionariosController.class).listar()).withRel("all-funcionarios")
            );
            return ResponseEntity.ok(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionarios> actualizar(@PathVariable Integer id, @RequestBody Funcionarios funcionarios) {
        try{
            Funcionarios fun = funcionariosService.findById(id);
            fun.setId(id);
            fun.setRun(funcionarios.getRun());
            fun.setDv(funcionarios.getDv());
            fun.setNombre(funcionarios.getNombre());
            fun.setAppaterno(funcionarios.getAppaterno());
            fun.setApmaterno(funcionarios.getApmaterno());
            fun.setCorreo(funcionarios.getCorreo());
            fun.setTelefono(funcionarios.getTelefono());

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
