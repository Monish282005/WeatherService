package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime datetimeUtc;
    private String conds;
    private Double dewptm;
    private Boolean fog;
    private Boolean hail;
    private Double heatindexm;
    private Double hum;
    private Double precipm;
    private Double pressurem;
    private Boolean rain;
    private Boolean snow;
    private Double tempm;
    private Boolean thunder;
    private Boolean tornado;
    private Double vism;
    private Integer wdird;
    private String wdire;
    private Double wgustm;
    private Double windchillm;
    private Double wspdm;


}