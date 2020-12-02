package com.trending.repos.controller;

import com.google.gson.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@Controller
@RequestMapping("/")
public class RestController {
    @RequestMapping("api")
    private static String getRepositories() {

        LocalDate now = LocalDate.now(); // 2015-11-24
        LocalDate date = now.minusMonths(1); // 2015-10-24

        final String uri = "https://api.github.com/search/repositories?q=created:>" + date + "&sort=stars&order=desc&page=0&per_page=100";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        JsonParser jsonParser = new JsonParser();
        JsonObject repositories = (JsonObject) jsonParser.parse(result);
        List <Language> languages = new ArrayList<Language>();
        for (int j = 0; j < repositories.getAsJsonArray("items").size(); j++) {
            String lang = repositories.getAsJsonArray("items").get(j).getAsJsonObject().get("language").toString();
            JsonObject repo = repositories.getAsJsonArray("items").get(j).getAsJsonObject();
            if (!languages.stream().anyMatch(language -> language.getName().equals(lang))) {
                Language language = new Language();
                language.setName(lang);
                language.getRepos().add(repo);
                language.incrementNumberOfRepo();
                languages.add(language);
            } else {
                languages.stream().filter(language -> language.getName().equals(lang)).findFirst().get().getRepos().add(repo);
                languages.stream().filter(language -> language.getName().equals(lang)).findFirst().get().incrementNumberOfRepo();

            }

        }
        String json = new Gson().toJson(languages );

         return json;
        }
    }
