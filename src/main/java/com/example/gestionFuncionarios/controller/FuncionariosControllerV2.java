package com.example.gestionFuncionarios.controller;

import com.example.gestionFuncionarios.assemblers.FuncionariosModelAssembler;
import com.example.gestionFuncionarios.model.Funcionarios;
import com.example.gestionFuncionarios.service.FuncionariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Gestion/Funcionarios/v2")
public class FuncionariosControllerV2 {

    @Autowired
    private FuncionariosService funcionariosService;

    @Autowired
    private FuncionariosModelAssembler assembler;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Funcionarios>> buscar(@PathVariable Integer id) {
        try {
            Funcionarios funcionario = funcionariosService.findById(id);
            return ResponseEntity.ok(assembler.toModel(funcionario));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Funcionarios>>> listar() {
        List<Funcionarios> funcionarios = funcionariosService.findAll();
        List<EntityModel<Funcionarios>> funcionariosHateoas = funcionarios.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Funcionarios>> collectionModel = CollectionModel.of(
                funcionariosHateoas,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionariosControllerV2.class).listar()).withSelfRel()
        );

        return ResponseEntity.ok(collectionModel);
    }
}
