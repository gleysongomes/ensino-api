package io.github.gleysongomes.ensino.dto.v1;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriacaoUsuarioDto {

	@NotBlank
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String senha;
}
