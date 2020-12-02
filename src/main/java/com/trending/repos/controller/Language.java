package com.trending.repos.controller;

import com.google.gson.JsonArray;

public class Language {
    private String name;

    public int getNumberOfRepos() {
        return NumberOfRepos;
    }

    public void setNumberOfRepos(int numberOfRepos) {
        NumberOfRepos = numberOfRepos;
    }
    public void incrementNumberOfRepo() {
        NumberOfRepos += 1;
    }

    private int NumberOfRepos = 0;
    private JsonArray repos = new JsonArray();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonArray getRepos() {
        return repos;
    }

    public void setRepos(JsonArray repos) {
        this.repos = repos;
    }
}
