package com.example.gestionFuncionarios.controller;

import com.example.gestionFuncionarios.model.Funcionarios;
import com.example.gestionFuncionarios.service.FuncionariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Gestion/Funcionarios/v2")
public class FuncionariosControllerV2 {

    @Autowired
    private FuncionariosService funcionariosService;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Funcionarios>> buscar(@PathVariable Integer id) {
        try {
            Funcionarios funcionario = funcionariosService.findById(id);
            EntityModel<Funcionarios> resource = EntityModel.of(funcionario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionariosControllerV2.class).buscar(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionariosControllerV2.class).listar()).withRel("all-funcionarios")
            );
            return ResponseEntity.ok(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        // Puedes implementar la versión HATEOAS para la lista aquí
        return ResponseEntity.ok("Listado HATEOAS aquí");
    }
}
