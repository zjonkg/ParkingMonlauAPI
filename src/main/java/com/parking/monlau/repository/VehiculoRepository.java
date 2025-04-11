package com.parking.monlau.repository;
import com.parking.monlau.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    Vehiculo findByMatricula(String matricula);
}