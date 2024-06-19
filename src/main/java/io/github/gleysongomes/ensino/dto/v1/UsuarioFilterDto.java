package io.github.gleysongomes.ensino.dto.v1;

import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFilterDto {

	private Long id;

	private String email;

	private String nome;

	private ZonedDateTime dataCadastro;

	private ZonedDateTime dataAtualizacao;

	private Boolean ativo;
	
	private String fusoHorario;
}
