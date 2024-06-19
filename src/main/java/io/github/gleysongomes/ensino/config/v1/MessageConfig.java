package io.github.gleysongomes.ensino.config.v1;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageConfig {

	@Bean
	public MessageSource messageSource() {
		var source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:messages");
		source.setDefaultEncoding("UTF-8");
		return source;
	}
}
