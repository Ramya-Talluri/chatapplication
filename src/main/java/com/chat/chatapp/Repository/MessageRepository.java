package com.chat.chatapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.chatapp.Model.Chat;
import com.chat.chatapp.Model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatAndIsRead(Chat chat, boolean isRead);
}
