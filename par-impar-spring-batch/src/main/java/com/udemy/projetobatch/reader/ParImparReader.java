package com.udemy.projetobatch.reader;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParImparReader {

	@Bean
	public IteratorItemReader<Integer> reader() {
		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		return new IteratorItemReader<Integer>(numeros.iterator());
	}
}
