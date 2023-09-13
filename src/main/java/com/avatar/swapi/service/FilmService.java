package com.avatar.swapi.service;

import com.avatar.swapi.model.Film;
import com.avatar.swapi.model.FilmPerson;
import com.avatar.swapi.model.Person;
import com.avatar.swapi.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;

    public List<Film> getFilms(){
        return filmRepository.findAll();
    }

    public Film save(Film film) {
        return filmRepository.save(film);
    }
}
