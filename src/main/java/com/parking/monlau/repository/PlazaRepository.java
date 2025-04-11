package com.parking.monlau.repository;
import com.parking.monlau.model.Plaza;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface PlazaRepository extends JpaRepository<Plaza, Long> {
    List<Plaza> findByTipoAndOcupadaFalse(String tipo);
    List<Plaza> findByOcupadaFalse(); // Nuevo método

}