package com.homework.project.web;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import com.homework.project.model.Weather;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class HWController {

    //// Methods
    // Singletons
    @Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
    }
    // Tests
    @RequestMapping(value = "/test-post", method = RequestMethod.GET)
    @ResponseBody
    public String testPOST(){
    	try {
            Weather weather = new Weather(
                "Osaka",
                LocalDate.of(2020, Month.MAY, 3),
                17.0,
                26.0
            );

            // POST
            getRestTemplate().postForLocation(
                "http://localhost:8080/rest/weatherdata",
                weather
            );
            return "Succesfully POSTed";
    	} catch (Exception E) {
    		return E.getMessage();
    	}
    }
    
    @RequestMapping(value = "/test-put", method = RequestMethod.GET)
    @ResponseBody
    public String testPUT(){
    	try {
    		
        	// Workaround to get a single Weather object
        	// As the homework doesn't define a way to get a single one by ID
        	// Implementing a way to do so creates ambiguity and can be hard to wrap
        	// our head around it
        	Weather[] data = getRestTemplate().getForObject(
    			"http://localhost:8080/rest/weatherdata",
    			Weather[].class
    		);
    		
        	// PUT
			Weather weather = data[0];
    		weather.setMinTemperature(-237.15);
    		
            getRestTemplate().put("http://localhost:8080/rest/weatherdata/" + weather.getID(), weather);
            return "Successfully PUT.";

    	} catch (Exception E) {
    		return E.getMessage();
    	}

    }

	@RequestMapping(value = "/test-delete", method = RequestMethod.GET)
	@ResponseBody
	public String testDELETE() {
		try {
			// DELETE a random Weather data
	    	Weather[] data = getRestTemplate().getForObject(
				"http://localhost:8080/rest/weatherdata",
				Weather[].class
			);
	    	if (data.length == 0) {
	    		return "There is nothing to DELETE.";
	    	} else {
	    		
	        	long sacrifice = data[data.length == 1 ? 0 : new Random().nextInt(data.length - 1)].getID();

	            // DELETE
	    		getRestTemplate().delete("http://localhost:8080/rest/weatherdata/" + sacrifice);
	    		return "Succesfully DELETEd Weather " + sacrifice + ".";
	    	}
		} catch (Exception E) {
			return E.getMessage();
		}
	}
}