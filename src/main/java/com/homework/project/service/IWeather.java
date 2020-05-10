package com.homework.project.service;

import java.util.List;

import com.homework.project.model.Weather;

public interface IWeather {

    //// Methods
    // Interface bodies
	public List<Weather> findAll();
    public Weather findByID(long ID);
    public Weather update(Weather weather);
	public void create(Weather weather);
	public void delete(long ID);
}
