package com.nelson.estudos.java.user_api.repository;

import com.nelson.estudos.java.user_api.dto.UserDTO;
import com.nelson.estudos.java.user_api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByCpf (String cpf);

    List<User> queryByNomeLike (String name);

}
