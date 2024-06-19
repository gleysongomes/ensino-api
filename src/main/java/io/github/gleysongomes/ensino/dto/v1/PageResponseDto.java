package io.github.gleysongomes.ensino.dto.v1;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageResponseDto<T> {

	private Integer page;

	private Integer size;

	private Long totalElements;

	private List<T> content;
}
