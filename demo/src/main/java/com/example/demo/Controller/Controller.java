package com.example.demo.Controller;

import com.example.demo.Entity.Weather;
import com.example.demo.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private WeatherService weatherService;

     @GetMapping("/getById")
    public Weather getById(@RequestParam Long id){
         return weatherService.getById(id);
     }



}
