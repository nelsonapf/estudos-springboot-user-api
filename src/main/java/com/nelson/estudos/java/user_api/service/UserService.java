package com.nelson.estudos.java.user_api.service;

import com.nelson.estudos.java.user_api.dto.UserDTO;
import com.nelson.estudos.java.user_api.model.User;
import com.nelson.estudos.java.user_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> getAll() {
        List<User> usuarios = userRepository.findAll();
        return usuarios
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }
}
