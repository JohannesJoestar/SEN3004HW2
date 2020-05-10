package com.homework.project.web;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

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
        Weather weather = new Weather(
            "Osaka",
            LocalDate.of(2020, Month.MAY, 3),
            17.0,
            26.0
        );

        // POST
        return getRestTemplate().postForLocation(
            "http://localhost:8080/rest/weatherdata",
            weather
        ).toString();
    }
    
    @RequestMapping(value = "/test-put", method = RequestMethod.GET)
    @ResponseBody
    public String testPUT(){
    	
    	// Workaround to get a single Weather object
    	// As the homework doesn't define a way to get a single one by ID
    	// Implementing a way to do so creates ambiguity and can be hard to wrap
    	// our head around it
    	List<?> raw = getRestTemplate().getForObject(
			"http://localhost:8080/rest/weatherdata",
			List.class
		);
    	
    	// PUT
    	try {
    		Weather weather = (Weather) raw.get(0);
    		weather.setMinTemperature(-237.15);
    		
            getRestTemplate().put("http://localhost:8080/rest/weatherdata/" + weather.getID(), weather);
            return "";
    	} catch (Exception E) {
    		return "error";
    	}

    }

	@RequestMapping(value = "/test-delete", method = RequestMethod.GET)
	@ResponseBody
	public String testDELETE() {

        // DELETE
		getRestTemplate().delete("http://localhost:8080/rest/weatherdata/2");
		return "";
	}
}