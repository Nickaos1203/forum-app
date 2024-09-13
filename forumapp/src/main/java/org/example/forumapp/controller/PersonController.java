package org.example.forumapp.controller;

import org.example.forumapp.entity.Person;
import org.example.forumapp.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/")
    public String homePage() {
        return "home";
    }

    @RequestMapping("/register")
    public String inscription(Model model) {
        model.addAttribute("person", new Person());
        return "/person/inscription-form";
    }

    @PostMapping("/register")
    public String inscriptionForm(@ModelAttribute("person") Person person) {
        personService.register(person);
        return "redirect:/messages";
    }

    @RequestMapping("/login")
    public String connexion(Model model) {
        return "/person/connexion-form";
    }

    @PostMapping("/login")
    public String connexionForm(@ModelAttribute("username") String username, @ModelAttribute("password") String password) {
        boolean connected = personService.login(username, password);
        if (connected) {
            return "redirect:/message/list";
        }
        return "redirect:/login";
    }

}
