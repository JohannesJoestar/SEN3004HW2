package com.homework.project.service;

import java.util.List;

import javax.transaction.Transactional;

import com.homework.project.dao.HWRepository;
import com.homework.project.model.Weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HWService implements IWeather {

    //// Properties
    // References
    @Autowired
    HWRepository repository;

    //// Methods
    // Interface implementation
    @Override
    public List<Weather> findAll() {
        return repository.findAll();
    }
    @Override
    public Weather findByID(long ID) {
        return repository.findByID(ID);
    }
    @Override
    public Weather update(Weather weather) {
        return repository.update(weather);
    }
    @Override
    public void create(Weather weather) {
        repository.create(weather);
    }
    @Override
    public void delete(long ID) {
        repository.delete(ID);
    }
    
}