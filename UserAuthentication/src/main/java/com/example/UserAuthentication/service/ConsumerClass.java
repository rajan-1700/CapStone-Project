package com.example.UserAuthentication.service;

import com.example.UserAuthentication.model.UserDTO;
import com.example.UserAuthentication.repository.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerClass {
    @Autowired
    private UserRepo userRepo; // Assuming you have a UserRepo to save the UserDTO
    @KafkaListener(topics = "user-topic", groupId = "group_id")
    public void consumeMessage(String message) throws JsonMappingException, JsonProcessingException {
        // Logic to consume a message from Kafka

        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO ud=objectMapper.readValue(message, new TypeReference<UserDTO>(){});
        userRepo.save(ud);

    }
    @KafkaListener(topics = "user-delete-topic", groupId = "user_group")
    public void consumeDeleteEvent(String userIdStr) {
        try {
            int userId = Integer.parseInt(userIdStr);
            userRepo.deleteById(userId);
            System.out.println("Deleted user from UserDTO table with ID: " + userId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
