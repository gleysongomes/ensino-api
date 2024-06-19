package io.github.gleysongomes.ensino.dto.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequestDto {

	private Integer page;

	private Integer size;

	public PageRequestDto() {
		this.page = 1;
		this.size = 10;
	}

	@JsonIgnore
	public Integer getPageStart() {
		return size * (page - 1);
	}

	@JsonIgnore
	public Integer getPageEnd() {
		return size * page;
	}
}
