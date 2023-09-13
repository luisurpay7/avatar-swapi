package com.avatar.swapi.service;

import com.avatar.swapi.model.Film;
import com.avatar.swapi.model.FilmPerson;
import com.avatar.swapi.repository.FilmPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmPersonService {
    @Autowired
    private FilmPersonRepository filmPersonRepository;

    public FilmPerson save(FilmPerson filmPerson) {
        return filmPersonRepository.save(filmPerson);
    }
}
