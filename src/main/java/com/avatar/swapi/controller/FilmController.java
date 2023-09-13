package com.avatar.swapi.controller;

import com.avatar.swapi.model.Film;
import com.avatar.swapi.model.Person;
import com.avatar.swapi.service.FilmService;
import com.avatar.swapi.service.PersonService;
import com.avatar.swapi.swapimodels.SwapiPeople;
import com.avatar.swapi.swapimodels.SwapiPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/api/films")
public class FilmController {
    @Autowired
    private FilmService filmService;
    @Autowired
    private PersonService personService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    private ResponseEntity<List<Film>> listFilms(){
        String url = "https://swapi.dev/api/people";
        SwapiPeople results = restTemplate.getForObject(url, SwapiPeople.class);

        for(int i=0; i<results.getResults().size(); i++){
            Person person = mapPerson(results.getResults().get(i));
            personService.save(person);
        }
        return ResponseEntity.ok(filmService.getFilms());
    }

    private Person mapPerson(SwapiPerson swapiPerson){
        Person person = new Person();
        person.setName(swapiPerson.getName());
        person.setBirth_year(swapiPerson.getBirth_year());
        person.setEye_color(swapiPerson.getEye_color());
        person.setGender(swapiPerson.getGender());
        person.setHair_color(swapiPerson.getHair_color());
        person.setHeight(swapiPerson.getHeight());
        person.setMass(swapiPerson.getMass());
        person.setSkin_color(swapiPerson.getSkin_color());
        person.setHomeworld(swapiPerson.getHomeworld());
        person.setUrl(swapiPerson.getUrl());
        person.setCreated(swapiPerson.getCreated());
        person.setEdited(swapiPerson.getEdited());
        return person;
    }
}
