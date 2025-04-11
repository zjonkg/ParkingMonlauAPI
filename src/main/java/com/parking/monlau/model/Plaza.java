package com.parking.monlau.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.parking.monlau.model.data.EnumVehiculo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



import jakarta.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "plaza")
public class Plaza {
    // Getters y Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // "auto", "moto", "camioneta"
    private boolean ocupada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_id")
    @JsonBackReference
    private Parking parking;

    public Plaza() {}

    public Plaza(String tipo) {
        this.tipo = tipo;
        this.ocupada = false;
    }

}