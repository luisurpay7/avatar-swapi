package com.avatar.swapi.swapimodels;

import java.util.List;

public class SwapiPeople {
    private Long count;
    private String next;
    private String previous;
    private List<SwapiPerson> results;

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

    public List<SwapiPerson> getResults() {
        return results;
    }

    public void setResults(List<SwapiPerson> results) {
        this.results = results;
    }
}
