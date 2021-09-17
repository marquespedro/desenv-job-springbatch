package com.udemy.projetobatch.processor;

import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParImparProcessor {

	@Bean
	public FunctionItemProcessor<Integer, String> processor() {
		return new FunctionItemProcessor<>(
				item -> item % 2 == 0 ? String.format("Item %s é Par", item) : String.format("Item %s é Impar", item)
			);
	}
}
