package com.parking.monlau.model.exceptions;

public class TicketNotFoundException extends Exception {


    public TicketNotFoundException (String errorMessage) {
        super(errorMessage);
    }
}
