package com.bootcamp.personapi.service;

import com.bootcamp.personapi.dto.request.PersonDTO;
import com.bootcamp.personapi.dto.response.MessageResponseDTO;
import com.bootcamp.personapi.entity.Person;
import com.bootcamp.personapi.exception.PersonNotFoundException;
import com.bootcamp.personapi.mapper.PersonMapper;
import com.bootcamp.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO.builder().message("Created person with ID " + savedPerson.getId()).build();
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }

    public void deleteById(Long id) throws PersonNotFoundException{
        verifyIfExists(id);

        personRepository.deleteById(id);
    }

    public MessageResponseDTO uptadeById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToUpdate);
        return MessageResponseDTO.builder().message("Updated person with ID " + savedPerson.getId()).build();
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException{
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }
}
