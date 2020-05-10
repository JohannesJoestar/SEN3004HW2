package com.homework.project.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.homework.project.model.Weather;
import com.homework.project.service.IWeather;

public class HWRepository implements IWeather {

    //// Properties
    // References
    private EntityManager manager;

    //// Methods
    // Interface implementation
    @Override
    public List<Weather> findAll(){
        return manager.createQuery("FROM weatherdata", Weather.class)
            .getResultList();
    }
    @Override
    public Weather findByID(long ID){
        return manager.find(Weather.class, ID);
    }
    @Override
    public Weather update(Weather weather){
        return manager.merge(weather);
    }
	@Override
	public void create(Weather weather) {
		manager.persist(weather); 
	}
	@Override
	public void delete(long ID) {
		manager.remove(
            manager.getReference(Weather.class, ID)
        );
	} 

}