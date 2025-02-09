package com.chat.chatapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chat.chatapp.Exception.ChatNotFoundException;
import com.chat.chatapp.Model.Message;
import com.chat.chatapp.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestParam Long senderId, @RequestParam Long chatId, @RequestParam String content)  {
        try {
            Message message = messageService.sendMessage(senderId, chatId, content);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } 
       
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
}
    }


    @PostMapping("/read/{messageId}")
    public ResponseEntity<?> markMessageAsRead(@PathVariable Long messageId) {
        messageService.markMessageAsRead(messageId);
        return ResponseEntity.ok("Message marked as read");
    }

    @GetMapping("/unread/{userId}")
    public ResponseEntity<?> getUnreadMessages(@PathVariable Long userId) {
        long unreadCount = messageService.getUnreadMessageCount(userId);
        return ResponseEntity.ok("Unread messages: " + unreadCount);
    }
}
