package io.github.gleysongomes.ensino.exception.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProblemType {

	RECURSO_NAO_ENCONTRADO("Recurso não encontrado."),
	REGRA_NEGOCIO("Regra de negócio.");

	private String title;
}
