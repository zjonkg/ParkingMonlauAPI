package com.parking.monlau.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.parking.monlau.model.data.EnumVehiculo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import jakarta.persistence.*;
import java.util.List;

import jakarta.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class Parking {
    // Getters y Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "parking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Indica que esta es la parte "principal" de la relación
    private List<Plaza> plazas;

    public Parking() {}

    public Parking(String nombre) {
        this.nombre = nombre;
    }

}