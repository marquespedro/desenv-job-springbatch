package com.udemy.projetobatch.step;



import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pedro
 	 * o tamanho do chunk deve ser compativel com o tamanho disponivel em memoria
	 * para processamento dos dados.
	 * o numero de commits ou seja transacoes com banco ira depender do tamanho do chunk
	 * ou seja quando maior o chunk menor o numero de transacoes aberta com banco dados.
	 * 
	 * 
 */
@Configuration
public class ParImparStep {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step imprimeParImparStep(ItemReader<Integer> reader,
								    ItemProcessor<Integer, String> processor, 
								    ItemWriter<String> writer) {
		return stepBuilderFactory
				.get("imprimeParImparStep")
				.<Integer, String>chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)			
				.build();
	}
	
}
