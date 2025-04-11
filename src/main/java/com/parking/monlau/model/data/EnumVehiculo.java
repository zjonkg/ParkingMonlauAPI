package com.parking.monlau.model.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumVehiculo {
    AUTO("AUTO"),
    MOTO("MOTO"),
    CAMIONETA("CAMIONETA");

    private final String value;

    EnumVehiculo(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static EnumVehiculo fromValue(String value) {
        for (EnumVehiculo tipo : EnumVehiculo.values()) {
            if (tipo.value.equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de vehículo no válido: " + value);
    }
}