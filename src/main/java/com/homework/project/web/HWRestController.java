package com.homework.project.web;

import java.util.List;

import javax.persistence.NoResultException;

import com.homework.project.model.Weather;
import com.homework.project.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class HWRestController {
    
    //// Properties
    // References
    @Autowired
    WeatherService service;

    //// Methods
    // REST API
    @RequestMapping(value = "/weatherdata", method = RequestMethod.GET)
    public ResponseEntity<List<Weather>> getAllWeatherData(){
        try {
            
            // HTTP 200
            return ResponseEntity.ok(service.findAll());
        } catch (Exception E){

            // HTTP 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @RequestMapping(value = "/weatherdata/{city}", method = RequestMethod.GET)
	public ResponseEntity<List<Weather>> getWeatherDataByCity(@RequestParam("city") String city) {
        try {
            List<Weather> data = service.findByCity(city);

            // HTTP 200
            return ResponseEntity.ok(data);
        } catch (Exception E){

            // HTTP 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
   
    @RequestMapping(value = "/weatherdata", method = RequestMethod.POST)
    public ResponseEntity<?> createWeatherData(@RequestBody Weather weather) {
        try {
            service.create(weather);

            // HTTP 201
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception E) {

            // HTTP 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @RequestMapping(value = "/weatherdata/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateWeatherData(@PathVariable("id") Long ID, @RequestBody Weather weather) {
		try {
            Weather result = service.findByID(ID);
            result.acquireFrom(weather);
			service.update(result);

            // HTTP 200
			return ResponseEntity.ok().build();
        } catch (NoResultException NRE){

            // HTTP 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception E) {

            // HTTP 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }
    
    @RequestMapping(value = "/weatherdata/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteWeatherData(@PathVariable("id") Long ID) {
		try {
            service.delete(ID);

            // HTTP 200
			return ResponseEntity.ok().build();
        } catch (NoResultException NRE){

            // HTTP 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception E) {

            // HTTP 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}