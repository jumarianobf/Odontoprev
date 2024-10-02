package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.ImagemUsuarioOdontoprev;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemUsuarioRepository extends JpaRepository<ImagemUsuarioOdontoprev, Long> {
}
