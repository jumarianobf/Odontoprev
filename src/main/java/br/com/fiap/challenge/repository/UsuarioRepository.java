package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.UsuarioOdontoprev;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioOdontoprev, Long> {
    Optional<UsuarioOdontoprev> findById(Long id);
}
