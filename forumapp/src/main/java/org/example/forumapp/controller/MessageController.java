package org.example.forumapp.controller;

import org.example.forumapp.entity.Message;
import org.example.forumapp.service.MessageService;
import org.example.forumapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageController {
    private final MessageService messageService;
    private final PersonService personService;

    @Autowired
    public MessageController(MessageService messageService, PersonService personService) {
        this.messageService = messageService;
        this.personService = personService;
    }

    @RequestMapping("/messages")
    public String listMessages(Model model) {
        model.addAttribute("messages", messageService.findAll());
        return "/message/list";
    }

    @RequestMapping("/messages/new")
    public String createMessage(Model model) {
        if (personService.isLogged()) {
            model.addAttribute("message", new Message());
            return "/message/create";
        }
        return "redirect:/login";
    }

    @PostMapping("/messages/add")
    public String addMessage(@ModelAttribute("message") Message message) {
        if (personService.isLogged()) {
            if (message.getId() != null) {
                messageService.updateMessage(message.getId(), message);
            } else {
                messageService.createMessage(message);
            }
        }
        return "redirect:/messages";

    }


}
