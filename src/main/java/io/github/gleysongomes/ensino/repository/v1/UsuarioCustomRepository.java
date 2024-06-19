package io.github.gleysongomes.ensino.repository.v1;

import java.util.List;

import io.github.gleysongomes.ensino.dto.v1.PageRequestDto;
import io.github.gleysongomes.ensino.dto.v1.UsuarioFilterDto;
import io.github.gleysongomes.ensino.model.v1.Usuario;

public interface UsuarioCustomRepository {

	List<Usuario> findAll(UsuarioFilterDto usuarioFilterDto, PageRequestDto pageRequestDto);

	Long count(UsuarioFilterDto usuarioFilterDto, PageRequestDto pageRequestDto);
}