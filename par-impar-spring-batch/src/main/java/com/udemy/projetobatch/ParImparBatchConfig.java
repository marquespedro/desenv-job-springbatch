package com.udemy.projetobatch;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pedro
 *
 * @EnableBatchProcessing habilita o uso de componentes do framework spring
 *                        batch
 */
@EnableBatchProcessing
@Configuration
public class ParImparBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job imprimiParImparJob() {
		return jobBuilderFactory.get("imprimiParImparJob").start(imprimeParImparStep()).incrementer(new RunIdIncrementer())
				.build();
	}

	/**
	 * o tamanho do chunk deve ser compativel com o tamanho disponivel em memoria
	 * para processamento dos dados.
	 * o numero de commits ou seja transacoes com banco ira depender do tamanho do chunk
	 * ou seja quando maior o chunk menor o numero de transacoes aberta com banco dados.
	 * 
	 * @return
	 */
	public Step imprimeParImparStep() {
		return stepBuilderFactory
				.get("imprimeParImparStep")
				.<Integer, String>chunk(1)
				.reader(contaDezReader()) //recebe a lista para processar
				.processor(parOuImparProcessor()) //recebe um item para processar
				.writer(imprimeWriter())//recebe a lista processada
				.build();
	}	

	public ItemWriter<String> imprimeWriter() {
		return itens -> itens.forEach(System.out::println);
	}

	/**
	 * FunctionItemProcessor implementacao de ItemProcessor para uma funcao java
	 * 
	 * @return
	 */
	public FunctionItemProcessor<Integer, String> parOuImparProcessor() {

		return new FunctionItemProcessor<Integer, String>(
				item -> item % 2 == 0 ? String.format("Item %s é Par", item) : String.format("Item %s é Ímpar", item));
	}

	/**
	 * IteratorItemReader implementacao de ItemReader para um iterador,
	 * disponibilizado pelo proprio spring
	 * 
	 * @return
	 */
	public IteratorItemReader<Integer> contaDezReader() {
		List<Integer> numerosAteDez = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		return new IteratorItemReader<Integer>(numerosAteDez.iterator());
	}

}
