package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

        return ResponseEntity.status(200).body(cidade);
    }
}
