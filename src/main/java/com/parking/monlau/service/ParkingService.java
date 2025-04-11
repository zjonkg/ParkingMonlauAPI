package com.parking.monlau.service;

import com.parking.monlau.model.DTO.VehiculoRequest;
import com.parking.monlau.model.Parking;
import com.parking.monlau.model.Plaza;
import com.parking.monlau.model.Ticket;
import com.parking.monlau.model.Vehiculo;
import com.parking.monlau.model.data.EnumColor;
import com.parking.monlau.model.data.EnumVehiculo;
import com.parking.monlau.repository.ParkingRepository;
import com.parking.monlau.repository.PlazaRepository;
import com.parking.monlau.repository.TicketRepository;
import com.parking.monlau.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingService {
    @Autowired
    private PlazaRepository plazaRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public List<Plaza> obtenerPlazasDisponibles() {
        return plazaRepository.findByOcupadaFalse();
    }

    public List<Plaza> obtenerPlazas() {
        return plazaRepository.findAll();
    }

    public Vehiculo registrarVehiculo(VehiculoRequest vehiculoRequest) {
        // Validar que la matrícula no esté vacía
        if (vehiculoRequest.getMatricula() == null || vehiculoRequest.getMatricula().trim().isEmpty()) {
            throw new IllegalArgumentException("La matrícula no puede estar vacía");
        }

        // Validar que el tipo de vehículo no sea nulo
        if (vehiculoRequest.getTipo() == null) {
            throw new IllegalArgumentException("El tipo de vehículo es obligatorio");
        }

        // Validar que el color no sea nulo
        if (vehiculoRequest.getColor() == null) {
            throw new IllegalArgumentException("El color del vehículo es obligatorio");
        }

        // Verificar si ya existe un vehículo con la misma matrícula
        if (vehiculoRepository.findByMatricula(vehiculoRequest.getMatricula()) != null) {
            throw new IllegalArgumentException("Ya existe un vehículo con la matrícula: " + vehiculoRequest.getMatricula());
        }

        // Crear el vehículo
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMatricula(vehiculoRequest.getMatricula());
        vehiculo.setTipo(vehiculoRequest.getTipo());
        vehiculo.setColor(vehiculoRequest.getColor()); // Asegúrate de que este campo se asigne

        return vehiculoRepository.save(vehiculo);
    }

    public Ticket reservarPlaza(String matricula, EnumVehiculo tipo, EnumColor color) {
        // Buscar vehículo por matrícula
        Vehiculo vehiculo = vehiculoRepository.findByMatricula(matricula);
        if (vehiculo == null) {
            vehiculo = new Vehiculo(matricula, tipo, color);
            vehiculoRepository.save(vehiculo);
        }

        // Buscar una plaza disponible
        Plaza plaza = plazaRepository.findByTipoAndOcupadaFalse(tipo.toString()).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("No hay plazas disponibles para el tipo: " + tipo));

        // Ocupar la plaza
        plaza.setOcupada(true);
        plazaRepository.save(plaza);

        // Crear el ticket
        Ticket ticket = new Ticket(vehiculo, plaza, LocalDateTime.now());
        return ticketRepository.save(ticket); // Guardar y devolver el ticket
    }
    public Ticket liberarPlaza(Integer ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

        // Liberar la plaza
        Plaza plaza = ticket.getPlaza();
        plaza.setOcupada(false);
        plazaRepository.save(plaza);

        // Registrar salida
        ticket.setFechaSalida(LocalDateTime.now());
        ticket.setCosto(calcularCosto(ticket));
        return ticketRepository.save(ticket);
    }

    private double calcularCosto(Ticket ticket) {
        long minutos = java.time.Duration.between(ticket.getFechaEntrada(), ticket.getFechaSalida()).toMinutes();
        return minutos * 0.5; // Ejemplo: $0.1 por minuto
    }
}