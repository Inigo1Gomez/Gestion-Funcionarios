package com.example.gestionFuncionarios.controller;

import com.example.gestionFuncionarios.model.Funcionarios;
import com.example.gestionFuncionarios.service.FuncionariosService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FuncionariosControllerTest {

    @Mock
    private FuncionariosService funcionariosService;

    @InjectMocks
    private FuncionariosController controller;

    @Test
    void testListarSinResultados() {
        when(funcionariosService.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<List<Funcionarios>> response = controller.listar();
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testGuardar() {
        Funcionarios f = new Funcionarios();
        when(funcionariosService.save(f)).thenReturn(f);
        ResponseEntity<Funcionarios> response = controller.guardar(f);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(f, response.getBody());
    }
}