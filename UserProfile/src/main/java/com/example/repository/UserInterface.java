package com.example.repository;


import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserInterface extends JpaRepository<User, Integer> {

}
