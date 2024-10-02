package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.UsuarioOdontoprev;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioOdontoprev, Long> {
}
