package com.parking.monlau.model;
import lombok.Getter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




@Setter
@Getter
@Entity
public class Ticket {
    // Getters y Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vehiculo vehiculo;

    @ManyToOne
    private Plaza plaza;

    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private double costo;

    public Ticket() {}

    public Ticket(Vehiculo vehiculo, Plaza plaza, LocalDateTime fechaEntrada) {
        this.vehiculo = vehiculo;
        this.plaza = plaza;
        this.fechaEntrada = fechaEntrada;
    }

}