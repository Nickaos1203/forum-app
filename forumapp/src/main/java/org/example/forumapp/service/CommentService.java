package org.example.forumapp.service;

import org.example.forumapp.dao.CommentRepository;
import org.example.forumapp.dao.MessageRepository;
import org.example.forumapp.entity.Comment;
import org.example.forumapp.entity.Message;

import java.util.Collections;
import java.util.List;

public class CommentService {
    private final CommentRepository commentRepository;
    private final MessageRepository messageRepository;

    public CommentService(CommentRepository commentRepository, MessageRepository messageRepository) {
        this.commentRepository = commentRepository;
        this.messageRepository = messageRepository;
    }

}
