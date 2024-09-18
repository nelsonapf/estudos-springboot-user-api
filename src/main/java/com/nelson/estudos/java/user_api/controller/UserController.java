package com.nelson.estudos.java.user_api.controller;

import com.nelson.estudos.java.user_api.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    public static List<UserDTO> usuarios = new ArrayList<UserDTO>();

    @PostConstruct
    public void inicializaLista() {
        UserDTO userDTO = new UserDTO();
        userDTO.setNome("Nelson");
        userDTO.setCpf("12345678");
        userDTO.setEndereco("Rua Maria Alice");
        userDTO.setEmail("nelson@nelson.nelson");
        userDTO.setTelefone("5555-5555");
        userDTO.setDataCadastro(LocalDateTime.now());

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setNome("Ericka");
        userDTO2.setCpf("987654432");
        userDTO2.setEndereco("Rua Maria Alice");
        userDTO2.setEmail("ericka@ericka.ericka");
        userDTO2.setTelefone("5555-66666");
        userDTO2.setDataCadastro(LocalDateTime.now());

        UserDTO userDTO3 = new UserDTO();
        userDTO3.setNome("Amazonka");
        userDTO3.setCpf("99999999");
        userDTO3.setEndereco("Rua Maria Alice");
        userDTO3.setEmail("amazonka@amazonka.amazonka");
        userDTO3.setTelefone("5555-8888");
        userDTO3.setDataCadastro(LocalDateTime.now());

        usuarios.add(userDTO);
        usuarios.add(userDTO2);
        usuarios.add(userDTO3);

    }

    @GetMapping
    public List<UserDTO> getUsuarios() {
        return usuarios;
    }
    @GetMapping("/{cpf}")
    public UserDTO getUsuarioPorCpf (@PathVariable String cpf) {
        return usuarios.stream()
                .filter(u -> u.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO inserir(@RequestBody @Valid UserDTO userDTO){
        userDTO.setDataCadastro(LocalDateTime.now());
        usuarios.add(userDTO);
        return userDTO;
    }

    @DeleteMapping("/{cpf}")
    public boolean deletar(@PathVariable String cpf){
        return usuarios
                .removeIf(u -> u.getCpf().equals(cpf));
    }
}
