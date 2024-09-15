package org.example.forumapp.service;

import org.example.forumapp.dao.MessageRepository;
import org.example.forumapp.dao.PersonRepository;
import org.example.forumapp.entity.Message;
import org.example.forumapp.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository, PersonRepository personRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Message findById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message updateMessage(Long id, Message updateMessage) {
        Message messageExist = findById(id);
        if (messageExist != null) {
            messageRepository.save(updateMessage);
        }
        return messageExist;
    }

    public void delete(Message message) {
        messageRepository.delete(message);
    }


}
