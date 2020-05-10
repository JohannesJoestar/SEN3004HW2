package com.homework.project.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.homework.project.model.Weather;
import com.homework.project.service.IWeather;

import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepository implements IWeather {

    //// Properties
    // References
    @PersistenceContext
    private EntityManager manager;

    //// Methods
    // Interface implementation
    @Override
    public List<Weather> findAll(){
        return manager.createQuery("FROM Weather", Weather.class)
            .getResultList();
    }
    @Override
    public Weather findByID(long ID){
        return manager.find(Weather.class, ID);
    }
    @Override
    public List<Weather> findByCity(String city){
        return manager
            .createQuery("FROM Weather WHERE city = :city", Weather.class)
            .setParameter("city", city)
            .getResultList();
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