package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.ClinicaOdontoprev;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicaRepository extends JpaRepository<ClinicaOdontoprev, Long> {
}
