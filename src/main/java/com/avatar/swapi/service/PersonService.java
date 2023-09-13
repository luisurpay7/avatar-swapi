package com.avatar.swapi.service;

import com.avatar.swapi.model.Person;
import com.avatar.swapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public List<Person> getByUrl(String url){
        return personRepository.findByUrl(url);
    }

 }
