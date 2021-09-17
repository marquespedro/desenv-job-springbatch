package com.udemy.projetobatch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParImparWriter {
	
	@Bean
	public ItemWriter<String> imprimeWriter() {
		return items -> items.forEach(System.out::println);
	}
	
}
