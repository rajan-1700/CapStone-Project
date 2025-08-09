package com.example.service;

import com.example.globalexceptionhandler.UserAlreadyExistException;
import com.example.model.User;
import com.example.repository.UserInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserInterface userInterface;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public User addUser(User u) {
        if (userInterface.findById(u.getUid()).isPresent()) {
            throw new UserAlreadyExistException("User with ID " + u.getUid() + " already exists.");
        }

        // Create a new map with only required fields for Kafka
        Map<String, Object> userKafkaPayload = new HashMap<>();
        userKafkaPayload.put("uid", u.getUid());
        userKafkaPayload.put("password", u.getPassword());
        userKafkaPayload.put("email", u.getEmail());      // Include if needed

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String userStrng = objectMapper.writeValueAsString(userKafkaPayload);
            kafkaTemplate.send("user-topic", userStrng);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Use logger in real apps
        }

        return userInterface.save(u); // Save full object (with age) in DB
    }

    public List<User> getAllUsers() {
        return userInterface.findAll();
    }


    public Optional<User> getUserById(int id) {
        return userInterface.findById(id);
    }

    public User updateUser(int id, User updatedUser) {
        return userInterface.findById(id).map(existingUser -> {
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            return userInterface.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void deleteUser(int id) {
        if (!userInterface.existsById(id)) {
            throw new RuntimeException("User with ID " + id + " not found");
        }

        // Delete from producer DB
        userInterface.deleteById(id);

        // Send only the user ID as a string to Kafka
        kafkaTemplate.send("user-delete-topic", String.valueOf(id));
    }




}
