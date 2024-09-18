package com.nelson.estudos.java.user_api.service;

import com.nelson.estudos.java.user_api.dto.UserDTO;
import com.nelson.estudos.java.user_api.model.User;
import com.nelson.estudos.java.user_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //################## RETORNA LISTA DE DTOS #######################
    public List<UserDTO> getAll() {
        List<User> usuarios = userRepository.findAll();
        return usuarios
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    //################## BUSCA USUER POR ID ###########################
    public UserDTO findById (long userId) {
        User usuario = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
        return UserDTO.convert(usuario);
    }

    //################## SALVA USUARIO ################################
    public UserDTO save(UserDTO userDTO) {
        userDTO.setDataCadastro(LocalDateTime.now());
        User user = userRepository.save(User.convert(userDTO));
        return UserDTO.convert(user);
    }

    //################## DELETA USUARIO ###############################
    public UserDTO delete(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado ao deletar"));
        userRepository.delete(user);
        return UserDTO.convert(user);
    }

    //################## BUSCA POR CPF ################################
    public UserDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        if (user != null) {
            return UserDTO.convert(user);
        }
        return null;
    }

    //################## BUSCA POR NOME ################################
    public List<UserDTO> queryByName(String name) {
        List<User> usuarios = userRepository.queryByNomeLike(name);
        return usuarios.stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    //################## EDITA USUARIO #################################
    public UserDTO editUser (Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado ao editar"));
        if (userDTO.getEmail() != null &&
                !user.getEmail().equals(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getTelefone() != null &&
                !user.getTelefone().equals(userDTO.getTelefone())) {
            user.setTelefone(userDTO.getTelefone());
        }
        if (userDTO.getEndereco() != null &&
                !user.getEndereco().equals(userDTO.getEndereco())) {
            user.setEndereco(userDTO.getEndereco());
        }

        user = userRepository.save(user);
        return UserDTO.convert(user);
    }

    //################## ENTENDER FUNCIONAMENTO #########################
    public Page<UserDTO> getAllPage (Pageable page) {
        Page<User> users = userRepository.findAll(page);
        return users.map(UserDTO::convert);
    }
}
