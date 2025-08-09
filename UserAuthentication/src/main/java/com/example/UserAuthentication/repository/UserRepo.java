package com.example.UserAuthentication.repository;

import com.example.UserAuthentication.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserDTO, Integer> {
}
