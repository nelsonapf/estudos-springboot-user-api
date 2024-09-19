package com.nelson.estudos.java.user_api.controller;

import com.nelson.estudos.java.user_api.dto.UserDTO;
import com.nelson.estudos.java.user_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    @GetMapping
    public List<UserDTO> getUsuarios() {
        return userService.getAll();
    }

    //################## BUSCA USUARIO POR ID VIA HTTP ##################
    @GetMapping("/{id}")
    public UserDTO findById (@PathVariable Long id) {
        return userService.findById(id);
    }

    //################## BUSCA USUARIO POR CPF VIA HTTP ##################
    @GetMapping("/{cpf}/cpf")
    public UserDTO findByCpf (@PathVariable String cpf) {
        return userService.findByCpf(cpf); //findByCpf(cpf, key)
    }

    //################## INSERE VIA HTTP COM JSON ##################
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO newUser(@RequestBody @Valid UserDTO userDTO){
        return userService.save(userDTO);
    }

    //################## DELETA VIA REQUISICAO HTTP ##################
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) //throws UserNotFoundExeption
    {
        userService.delete(id);
    }

    //################## BUSCA POR NOME ##################
    @GetMapping("/search")
    public List<UserDTO> queryByName ( @RequestParam(name="nome", required = true) String nome) {
        return userService.queryByName(nome);
    }

    //################## EDITA USUARIO ##################
    @PatchMapping("/{id}")
    public UserDTO editUser (@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.editUser(id, userDTO);
    }

    @GetMapping("/pageable")
    public Page<UserDTO> getUsersPage (Pageable pageable) {
        return userService.getAllPage(pageable);
    }
}
