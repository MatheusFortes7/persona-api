package com.bootcamp.personapi.service;

import com.bootcamp.personapi.dto.MessageResponseDTO;
import com.bootcamp.personapi.entity.Person;
import com.bootcamp.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person){
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO.builder().message("Created person with ID " + savedPerson.getId()).build();
    }
}
