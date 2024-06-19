package io.github.gleysongomes.ensino.helper.v1;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageSourceHelper {

	private final MessageSource messageSource;

	public String getMessage(String key) {
		return messageSource.getMessage(key, null, Locale.getDefault());
	}
}
