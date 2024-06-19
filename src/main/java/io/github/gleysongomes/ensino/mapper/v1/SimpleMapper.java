package io.github.gleysongomes.ensino.mapper.v1;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SimpleMapper {

	private final ModelMapper mapper;

	public <S, D> D map(S source, Class<D> destinationType) {
		return mapper.map(source, destinationType);
	}

	public void map(Object source, Object destination) {
		mapper.map(source, destination);
	}

	public <S, D> List<D> map(List<S> sources, Class<D> destinationType) {
		return sources.stream().map(source -> map(source, destinationType)).toList();
	}

}
