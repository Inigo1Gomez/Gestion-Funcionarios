package com.example.gestionFuncionarios.assemblers;

import com.example.gestionFuncionarios.model.Funcionarios;
import com.example.gestionFuncionarios.controller.FuncionariosControllerV2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class FuncionariosModelAssembler implements RepresentationModelAssembler<Funcionarios, EntityModel<Funcionarios>> {

    @Override
    public EntityModel<Funcionarios> toModel(Funcionarios funcionario) {
        return EntityModel.of(funcionario,
                linkTo(methodOn(FuncionariosControllerV2.class).buscar(funcionario.getId())).withSelfRel(),
                linkTo(methodOn(FuncionariosControllerV2.class).listar()).withRel("all-funcionarios")
        );
    }
}
