package com.example.demo.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.models.City;
import com.example.demo.models.Coordinates;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Service
public class LocationService {

    private String apiKey;

    private RestTemplate restTemplate;

    public LocationService() {
        this.apiKey = System.getenv("api_key");
        this.restTemplate = new RestTemplate();
    }
    
    public City findCity(double latitude, double longitude) {

        String urlString = "https://api.geodatasource.com/v2/city?key=" + apiKey + "&lat=" + latitude + "&lng=" + longitude;

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlString, String.class);

        JsonObject responseJson = JsonParser.parseString(responseEntity.getBody()).getAsJsonObject();

        String city = responseJson.get("city").getAsString();
        String region = responseJson.get("region").getAsString();
        String country = responseJson.get("country").getAsString();

        return new City(city, region, country, new Coordinates(latitude, longitude));
    }
}
