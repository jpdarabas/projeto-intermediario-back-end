package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.City;
import com.example.demo.services.LocationService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
public class LocationController {

    @Autowired
    LocationService locationService;

    @Value("${app.estudante}")
    private String estudante;

    @Value("${app.projeto}")
    private String projeto;

    public List<City> cityList = new ArrayList<>();

    @GetMapping("/ajuda")
    public ResponseEntity<Object> ajuda() {
        JsonObject responseJson = new JsonObject();

        responseJson.addProperty("estudante", this.estudante);
        responseJson.addProperty("projeto", this.projeto);

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseJson);

        return ResponseEntity.status(200).body(jsonResponse);
    }

    @GetMapping("/cidade")
    public ResponseEntity<Object> cidade(
        @RequestParam("latitude") double latitude,
        @RequestParam("longitude") double longitude) {
        City cidade = locationService.findCity(latitude, longitude);

        cityList.add(cidade);
        
        return ResponseEntity.status(200).body(cidade);
    }

    @GetMapping("/listaCidades")
    public ResponseEntity<List<City>> getCidades() {
        return ResponseEntity.status(200).body(cityList);
    }

    @PostMapping("/distancia")
    public ResponseEntity<Object> distancia(@RequestBody Map<String, String> requestBody) {
        String cidade1 = requestBody.get("cidade1");
        String cidade2 = requestBody.get("cidade2");
        String distancia = locationService.distance(cityList, cidade1,cidade2);

        return ResponseEntity.status(200).body(distancia);
    }
}
