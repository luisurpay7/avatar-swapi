package com.avatar.swapi.repository;

import com.avatar.swapi.model.FilmPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmPersonRepository extends JpaRepository<FilmPerson, Long> {
}
