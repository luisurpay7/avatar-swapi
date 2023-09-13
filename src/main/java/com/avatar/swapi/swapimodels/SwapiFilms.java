package com.avatar.swapi.swapimodels;

import java.util.List;

public class SwapiFilms {
    private Long count;
    private String next;
    private String previous;
    private List<SwapiFilm> results;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<SwapiFilm> getResults() {
        return results;
    }

    public void setResults(List<SwapiFilm> results) {
        this.results = results;
    }
}
