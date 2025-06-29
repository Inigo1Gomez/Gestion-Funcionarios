package com.example.gestionFuncionarios;

import com.example.gestionFuncionarios.model.Funcionarios;
import com.example.gestionFuncionarios.repository.FuncionarioRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es"));
        Random random = new Random();

            for (int i = 0; i < 20; i++) { 

            Funcionarios funcionario = new Funcionarios();

            
            String run = String.valueOf(faker.number().numberBetween(10000000, 25000000));
            char[] dvOptions = "0123456789K".toCharArray();
            String dv = String.valueOf(dvOptions[random.nextInt(dvOptions.length)]);

            funcionario.setRun(run);
            funcionario.setDv(dv);
            funcionario.setNombre(faker.name().firstName());
            funcionario.setAppaterno(faker.name().lastName());
            funcionario.setApmaterno(faker.name().lastName());
            funcionario.setTelefono(faker.phoneNumber().cellPhone().replaceAll("[^0-9]", "").substring(0, 9));
            funcionario.setCorreo(faker.internet().emailAddress());

            funcionarioRepository.save(funcionario);
        }
    }
}

