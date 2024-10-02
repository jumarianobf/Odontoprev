package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.ContatoUsuarioOdontoprev;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContatoUsuarioRepository extends JpaRepository<ContatoUsuarioOdontoprev, Long> {
}
