package io.github.gleysongomes.ensino.repository.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.gleysongomes.ensino.model.v1.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioCustomRepository {

}
