package com.chat.chatapp.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.chatapp.Model.Chat;
import com.chat.chatapp.Model.User;
import com.chat.chatapp.Repository.ChatRepository;
import com.chat.chatapp.Repository.UserRepository;
@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    public Chat startChat(Long user1Id, Long user2Id) {
        User user1 = userRepository.findById(user1Id).orElseThrow(() -> new RuntimeException("User not found"));
        User user2 = userRepository.findById(user2Id).orElseThrow(() -> new RuntimeException("User not found"));

        Chat chat = new Chat();
        chat.setParticipants(new HashSet<>(Arrays.asList(user1, user2)));
        chat.setCreatedAt(LocalDateTime.now());

        return chatRepository.save(chat);
    }

    public void deleteChat(Long chatId) {
        chatRepository.deleteById(chatId);
    }
}

