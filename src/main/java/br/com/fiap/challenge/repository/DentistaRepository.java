package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.DentistaOdontoprev;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistaRepository extends JpaRepository<DentistaOdontoprev, Long> {
}
