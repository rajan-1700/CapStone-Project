package com.example;

import java.util.List;

public class Author {
    private String key;
    private String name;
    private Integer birth_date;

    public Author(String key, String name, Integer birth_date, Integer death_date, List<String> top_work) {
        this.key = key;
        this.name = name;
        this.birth_date = birth_date;
        this.death_date = death_date;
        this.top_work = top_work;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Integer birth_date) {
        this.birth_date = birth_date;
    }

    public Integer getDeath_date() {
        return death_date;
    }

    public void setDeath_date(Integer death_date) {
        this.death_date = death_date;
    }

    public List<String> getTop_work() {
        return top_work;
    }

    public void setTop_work(List<String> top_work) {
        this.top_work = top_work;
    }

    private Integer death_date;
    private List<String> top_work;
}
