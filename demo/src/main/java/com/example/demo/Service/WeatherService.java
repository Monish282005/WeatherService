package com.example.demo.Service;

import com.example.demo.Entity.Weather;
import com.example.demo.Repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;


    public Weather getById(Long id){
        return weatherRepository.findById(id).orElse(null);
    }

}
