package io.github.gleysongomes.ensino.exception.v1;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class Problem {

	private String type;

	private String title;

	private Integer status;

	private String detail;

	private String instance;

	private OffsetDateTime timestamp;
}
