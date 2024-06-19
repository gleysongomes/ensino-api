package io.github.gleysongomes.ensino.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.gleysongomes.ensino.dto.v1.AtualizacaoUsuarioDto;
import io.github.gleysongomes.ensino.dto.v1.CriacaoUsuarioDto;
import io.github.gleysongomes.ensino.dto.v1.PageRequestDto;
import io.github.gleysongomes.ensino.dto.v1.PageResponseDto;
import io.github.gleysongomes.ensino.dto.v1.UsuarioDto;
import io.github.gleysongomes.ensino.dto.v1.UsuarioFilterDto;
import io.github.gleysongomes.ensino.service.v1.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

	private final UsuarioService usuarioService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDto save(@RequestBody @Valid CriacaoUsuarioDto criacaoUsuarioDto) {
		return usuarioService.save(criacaoUsuarioDto);
	}

	@PutMapping("/{id}")
	public UsuarioDto update(@PathVariable Long id, @RequestBody @Valid AtualizacaoUsuarioDto atualizacaoUsuarioDto) {
		return usuarioService.update(id, atualizacaoUsuarioDto);
	}

	@GetMapping("/{id}")
	public UsuarioDto findById(@PathVariable Long id) {
		return usuarioService.findById(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		usuarioService.deleteById(id);
	}

	@GetMapping
	public PageResponseDto<UsuarioDto> findAll(UsuarioFilterDto usuarioFilterDto, PageRequestDto pageRequestDto) {
		return usuarioService.findAll(usuarioFilterDto, pageRequestDto);
	}

}
