package com.chat.chatapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.chatapp.Model.Chat;
import com.chat.chatapp.Model.User;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByParticipantsContaining(User user);
}
