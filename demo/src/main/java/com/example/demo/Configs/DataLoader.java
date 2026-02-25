package com.example.demo.Configs;

import com.example.demo.Entity.Weather;
import com.example.demo.Repository.WeatherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(WeatherRepository repository) {
        return args -> {

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm");

            List<Weather> weatherList = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            getClass().getResourceAsStream("/testset.csv")))) {

                String line;
                reader.readLine();

                while ((line = reader.readLine()) != null) {

                    String[] parts = line.split(",", -1);

                    Weather weather = Weather.builder()
                            .datetimeUtc(parseDate(get(parts,0), formatter))
                            .conds(get(parts,1))
                            .dewptm(parseDouble(get(parts,2)))
                            .fog(parseBoolean(get(parts,3)))
                            .hail(parseBoolean(get(parts,4)))
                            .heatindexm(parseDouble(get(parts,5)))
                            .hum(parseDouble(get(parts,6)))
                            .precipm(parseDouble(get(parts,7)))
                            .pressurem(parseDouble(get(parts,8)))
                            .rain(parseBoolean(get(parts,9)))
                            .snow(parseBoolean(get(parts,10)))
                            .tempm(parseDouble(get(parts,11)))
                            .thunder(parseBoolean(get(parts,12)))
                            .tornado(parseBoolean(get(parts,13)))
                            .vism(parseDouble(get(parts,14)))
                            .wdird(parseInt(get(parts,15)))
                            .wdire(get(parts,16))
                            .wgustm(parseDouble(get(parts,17)))
                            .windchillm(parseDouble(get(parts,18)))
                            .wspdm(parseDouble(get(parts,19)))
                            .build();

                    weatherList.add(weather);
                }

                repository.saveAll(weatherList);

                System.out.println("Loaded records: " + weatherList.size());

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }


    private String get(String[] parts, int index) {
        if (index >= parts.length) return null;
        return parts[index].isEmpty() ? null : parts[index];
    }

    private LocalDateTime parseDate(String value, DateTimeFormatter formatter) {
        try {
            return value == null ? null : LocalDateTime.parse(value, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    private Double parseDouble(String s) {
        try {
            return s == null ? null : Double.valueOf(s);
        } catch (Exception e) {
            return null;
        }
    }

    private Integer parseInt(String s) {
        try {
            return s == null ? null : Integer.valueOf(s);
        } catch (Exception e) {
            return null;
        }
    }

    private Boolean parseBoolean(String s) {
        return s != null && (s.equals("1") || s.equalsIgnoreCase("true"));
    }
}