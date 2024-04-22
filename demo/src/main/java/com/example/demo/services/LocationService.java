package com.example.demo.services;

import java.util.List;

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

    public String distance(List<City> listaCidades, String cidade1, String cidade2){
        Coordinates cidade1Coordinates = null;
        Coordinates cidade2Coordinates = null;
        for (City cidade : listaCidades) {
            if (cidade.getName().equalsIgnoreCase(cidade1)) {
                cidade1Coordinates = cidade.getCoordinates();
            }
        }
        for (City cidade : listaCidades) {
            if (cidade.getName().equalsIgnoreCase(cidade2)) {
                cidade2Coordinates = cidade.getCoordinates();
            }
        }

        if (cidade1Coordinates != null && cidade2Coordinates != null) {

            double lat1 = cidade1Coordinates.getLatitude();
            double lon1 = cidade1Coordinates.getLongitude();
            
            double lat2 = cidade2Coordinates.getLatitude();
            double lon2 = cidade2Coordinates.getLongitude();
            
			double angulo = lon1 - lon2;
            
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(angulo));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.853159616;
			
			return (int) dist + "km";
		
        }else{
            return "Cidade não encontrada";
        }
    }

}
