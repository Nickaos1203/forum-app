package org.example.forumapp.service;

import jakarta.servlet.http.HttpSession;
import org.example.forumapp.dao.PersonRepository;
import org.example.forumapp.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    HttpSession httpSession;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person register(Person person) {
        return personRepository.save(person);
    }

    public boolean login (String username, String password) {
        Person person = personRepository.findByUsername(username);
        if (person == null) {
            return false;
        }
        if (person.getPassword().equals(password)) {
            httpSession.setAttribute("username", person.getUsername());
            httpSession.setAttribute("login", "OK");
            return true;
        }
        return false;
    }

    public boolean isLogged() {
        try {
            String isLogged = httpSession.getAttribute("login").toString();
            return isLogged.equals("OK");
        } catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        httpSession.invalidate();
    }

}
