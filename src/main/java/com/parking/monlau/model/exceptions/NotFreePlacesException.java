package com.parking.monlau.model.exceptions;

public class NotFreePlacesException extends Exception {

    // Excepcion para cuando no hay plazas libres

    public NotFreePlacesException (String errorMessage) {
        super(errorMessage);
    }
}
