package com.parking.monlau.model;

import com.parking.monlau.model.data.EnumColor;
import com.parking.monlau.model.data.EnumVehiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricula;

    @Enumerated(EnumType.STRING) // Almacena el enum como una cadena en la base de datos
    private EnumColor color;

    @Enumerated(EnumType.STRING)
    private EnumVehiculo tipo;

    public Vehiculo(String matricula, EnumVehiculo tipo, EnumColor color) {
        this.matricula = matricula;
        this.tipo = tipo;
        this.color = color;
    }
}