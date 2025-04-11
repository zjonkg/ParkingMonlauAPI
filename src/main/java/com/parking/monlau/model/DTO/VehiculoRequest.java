package com.parking.monlau.model.DTO;

import com.parking.monlau.model.data.EnumColor;
import com.parking.monlau.model.data.EnumVehiculo;

public class VehiculoRequest {

    private String matricula;

    private EnumVehiculo tipo;

    private EnumColor color; // Asegúrate de que este campo exista

    public VehiculoRequest() {}

    public VehiculoRequest(String matricula, EnumVehiculo tipo, EnumColor color) {
        this.matricula = matricula;
        this.tipo = tipo;
        this.color = color;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public EnumVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(EnumVehiculo tipo) {
        this.tipo = tipo;
    }

    public EnumColor getColor() {
        return color;
    }

    public void setColor(EnumColor color) {
        this.color = color;
    }
}