package com.chat.chatapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.chatapp.Model.Chat;
import com.chat.chatapp.Model.Message;
import com.chat.chatapp.Model.User;
import com.chat.chatapp.Repository.ChatRepository;
import com.chat.chatapp.Repository.MessageRepository;
import com.chat.chatapp.Repository.UserRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    public Message sendMessage(Long senderId, Long chatId, String content) {
        User sender = userRepository.findById(senderId).orElseThrow(() -> new RuntimeException("Sender not found"));
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found"));

        Message message = new Message();
        message.setSender(sender);
        message.setChat(chat);
        message.setContent(content);
        message.setRead(false);
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    public void markMessageAsRead(Long messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new RuntimeException("Message not found"));
        message.setRead(true);
        messageRepository.save(message);
    }

    public long getUnreadMessageCount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Chat> userChats = chatRepository.findByParticipantsContaining(user);

        return userChats.stream()
            .flatMap(chat -> messageRepository.findByChatAndIsRead(chat, false).stream())
            .count();
    }
}
