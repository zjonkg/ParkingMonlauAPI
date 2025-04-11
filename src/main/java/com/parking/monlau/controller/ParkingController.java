package com.parking.monlau.controller;
import com.parking.monlau.model.DTO.VehiculoRequest;
import com.parking.monlau.model.Plaza;
import com.parking.monlau.model.Ticket;
import com.parking.monlau.model.Vehiculo;
import com.parking.monlau.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/parking")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;


    @PostMapping("/vehiculos")
    public ResponseEntity<?> registrarVehiculo(@RequestBody VehiculoRequest vehiculoRequest) {
        try {
            Vehiculo vehiculo = parkingService.registrarVehiculo(vehiculoRequest);

            // Construir una respuesta más detallada
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Vehículo registrado con éxito");
            response.put("id", vehiculo.getId());
            response.put("matricula", vehiculo.getMatricula());
            response.put("tipo", vehiculo.getTipo());
            response.put("color", vehiculo.getColor());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reservar")
    public ResponseEntity<Map<String, Object>> reservarPlaza(@RequestBody Vehiculo vehiculo) {
        // Llamar al servicio para reservar la plaza
        Ticket ticket = parkingService.reservarPlaza(vehiculo.getMatricula(), vehiculo.getTipo(), vehiculo.getColor());

        // Construir la respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Ticket generado con éxito");
        response.put("ticketId", ticket.getId());
        response.put("matricula", ticket.getVehiculo().getMatricula());
        response.put("tipo", ticket.getVehiculo().getTipo());

        return ResponseEntity.ok(response);
    }
    @PostMapping("/liberar/{ticketId}")
    public Ticket liberarPlaza(@PathVariable Integer ticketId) {
        return parkingService.liberarPlaza(ticketId);
    }

    @GetMapping("/plazas-disponibles")
    public List<Plaza> obtenerPlazasDisponibles() {
        return parkingService.obtenerPlazasDisponibles();
    }

    @GetMapping("/plazas")
    public List<Plaza> obtenerPlazasNoDisponibles() {
        return parkingService.obtenerPlazas();
    }

}