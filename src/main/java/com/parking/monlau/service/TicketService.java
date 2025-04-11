package com.parking.monlau.service;


import com.parking.monlau.model.Ticket;
import org.springframework.stereotype.Service;
import com.parking.monlau.repository.TicketRepository;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket guardarTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket buscarPorId(int id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
    }
}