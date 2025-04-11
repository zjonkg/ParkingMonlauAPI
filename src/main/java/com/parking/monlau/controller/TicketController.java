package com.parking.monlau.controller;


import com.parking.monlau.model.Ticket;
import com.parking.monlau.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Ticket> guardarTicket(@RequestBody Ticket ticket) {
        Ticket ticketGuardado = ticketService.guardarTicket(ticket);
        return ResponseEntity.ok(ticketGuardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> buscarPorId(@PathVariable int id) {
        Ticket ticket = ticketService.buscarPorId(id);
        return ResponseEntity.ok(ticket);
    }
}