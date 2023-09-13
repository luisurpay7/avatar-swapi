package com.avatar.swapi.repository;

import com.avatar.swapi.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
