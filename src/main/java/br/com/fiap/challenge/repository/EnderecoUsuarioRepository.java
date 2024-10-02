package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.EnderecoUsuarioOdontoprev;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoUsuarioRepository extends JpaRepository<EnderecoUsuarioOdontoprev, Long> {
}
