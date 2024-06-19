package io.github.gleysongomes.ensino.dto.v1;

import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {

	private Long id;

	private String email;

	private String nome;

	private String hashSenha;

	private ZonedDateTime dataCadastro;

	private ZonedDateTime dataAtualizacao;
}
