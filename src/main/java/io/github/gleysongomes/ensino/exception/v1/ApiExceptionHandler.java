package io.github.gleysongomes.ensino.exception.v1;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<Object> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex) {
		var problem = createProblemBuilder(HttpStatus.NOT_FOUND, ProblemType.RECURSO_NAO_ENCONTRADO)
				.detail(ex.getMessage()).build();
		return ResponseEntity.status(problem.getStatus()).body(problem);
	}

	@ExceptionHandler(NovaSenhaException.class)
	public ResponseEntity<Object> handleRecursoNaoEncontradoException(NovaSenhaException ex) {
		var problem = createProblemBuilder(HttpStatus.BAD_REQUEST, ProblemType.REGRA_NEGOCIO).detail(ex.getMessage())
				.build();
		return ResponseEntity.status(problem.getStatus()).body(problem);
	}

	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType type) {
		var problem = Problem.builder().status(status.value()).type(type.getTitle()).timestamp(OffsetDateTime.now());
		return problem;
	}

}
