package io.github.gleysongomes.ensino.service.v1;

import io.github.gleysongomes.ensino.dto.v1.AtualizacaoUsuarioDto;
import io.github.gleysongomes.ensino.dto.v1.CriacaoUsuarioDto;
import io.github.gleysongomes.ensino.dto.v1.PageRequestDto;
import io.github.gleysongomes.ensino.dto.v1.PageResponseDto;
import io.github.gleysongomes.ensino.dto.v1.UsuarioDto;
import io.github.gleysongomes.ensino.dto.v1.UsuarioFilterDto;

public interface UsuarioService {

	UsuarioDto save(CriacaoUsuarioDto criacaoUsuarioDto);

	UsuarioDto findById(Long id);

	void deleteById(Long id);

	UsuarioDto update(Long id, AtualizacaoUsuarioDto atualizacaoUsuarioDto);

	PageResponseDto<UsuarioDto> findAll(UsuarioFilterDto usuarioFilterDto, PageRequestDto pageRequestDto);
}
