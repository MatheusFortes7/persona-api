package com.bootcamp.personapi.controller;

import com.bootcamp.personapi.dto.request.PersonDTO;
import com.bootcamp.personapi.dto.response.MessageResponseDTO;
import com.bootcamp.personapi.entity.Person;
import com.bootcamp.personapi.exception.PersonNotFoundException;
import com.bootcamp.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listALL(){
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO uptadeById(@PathVariable Long id,@RequestBody PersonDTO personDTO) throws PersonNotFoundException {
        return personService.uptadeById(id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException{
        personService.deleteById(id);
    }
}
