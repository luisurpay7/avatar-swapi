package com.avatar.swapi.controller;

import com.avatar.swapi.model.Film;
import com.avatar.swapi.model.FilmPerson;
import com.avatar.swapi.model.Person;
import com.avatar.swapi.service.FilmPersonService;
import com.avatar.swapi.service.FilmService;
import com.avatar.swapi.service.PersonService;
import com.avatar.swapi.swapimodels.SwapiFilm;
import com.avatar.swapi.swapimodels.SwapiFilms;
import com.avatar.swapi.swapimodels.SwapiPeople;
import com.avatar.swapi.swapimodels.SwapiPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/films")
public class FilmController {
    @Autowired
    private FilmService filmService;
    @Autowired
    private PersonService personService;
    @Autowired
    private FilmPersonService filmPersonService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping()
    public String listFilms(Model model){
        List<Film> films = filmService.getFilms();
        if(films.isEmpty()){
            saveSwapiPeople();
            saveSwapiFims();
        }
        model.addAttribute("films", filmService.getFilms());
        return "films";
    }

    @GetMapping("/{id}")
    public String getFilm(@PathVariable Long id, Model model){
        model.addAttribute("film", filmService.findById(id).get());
        return "film_details";
    }

    private void saveSwapiPeople(){
        String url = "https://swapi.dev/api/people";
        SwapiPeople results = restTemplate.getForObject(url, SwapiPeople.class);
        int numPage=1;
        String nextUrl;
        do{
            for(int i=0; i<results.getResults().size(); i++){
                Person person = mapPerson(results.getResults().get(i));
                personService.save(person);
            }
            numPage++;
            nextUrl = url + "/?page=" + numPage;
            results = restTemplate.getForObject(nextUrl, SwapiPeople.class);
        }while(results.getNext() != null);

        // Save 2 additional people
        for(int i=82; i<=83; i++){
            nextUrl = "https://swapi.dev/api/people/"+i;
            SwapiPerson swapiPerson = restTemplate.getForObject(nextUrl, SwapiPerson.class);
            Person person = mapPerson(swapiPerson);
            personService.save(person);
        }
    }

    private void saveSwapiFims(){
        String url = "https://swapi.dev/api/films";
        SwapiFilms results = restTemplate.getForObject(url, SwapiFilms.class);

        for(int i=0; i<results.getResults().size(); i++){
            List<Person> people= getCharacters(results.getResults().get(i));
            Film film = mapFilm(results.getResults().get(i));
            Film filmSaved = filmService.save(film);

            for(int j=0; j<people.size(); j++) {
                FilmPerson filmPerson = new FilmPerson();
                filmPerson.setFilm(filmSaved);
                filmPerson.setPerson(people.get(j));
                filmPersonService.save(filmPerson);
            }
        }
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

    private Film mapFilm(SwapiFilm swapiFilm){
        Film film = new Film();

        List<FilmPerson> filmPeople = new ArrayList<>();
        for(int i=0; i<swapiFilm.getCharacters().size(); i++){
            FilmPerson filmPerson = new FilmPerson();
            String personUrl = swapiFilm.getCharacters().get(i);
            List<Person> people = personService.getByUrl(personUrl);
            Person person = new Person();
            if(people.size() == 1) person = people.get(0);
            filmPerson.setPerson(person);
            filmPeople.add(filmPerson);
        }

        film.setTitle(swapiFilm.getTitle());
        film.setEpisodeId(swapiFilm.getEpisode_id());
        film.setOpening_crawl(swapiFilm.getOpening_crawl());
        film.setDirector(swapiFilm.getDirector());
        film.setProducer(swapiFilm.getProducer());
        //film.setRelease_date(swapiFilm.getRelease_date());
        film.setCreated(swapiFilm.getCreated());
        film.setEdited(swapiFilm.getEdited());
        film.setUrl(swapiFilm.getUrl());
        film.setFilmPeople(filmPeople);
        return film;
    }

    private List<Person> getCharacters(SwapiFilm swapiFilm){
        List<Person> people = new ArrayList<>();
        for(int i=0; i<swapiFilm.getCharacters().size(); i++){
            FilmPerson filmPerson = new FilmPerson();
            String personUrl = swapiFilm.getCharacters().get(i);
            List<Person> peopleFound = personService.getByUrl(personUrl);
            Person person = new Person();
            if(peopleFound.size() == 1) person = peopleFound.get(0);
            people.add(person);
        }
        return people;
    }
}
