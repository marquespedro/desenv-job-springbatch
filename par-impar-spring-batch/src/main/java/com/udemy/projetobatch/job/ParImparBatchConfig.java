package com.udemy.projetobatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pedro
 *
 * @EnableBatchProcessing habilita o uso de componentes do framework spring
 *                        batch
 */
@Configuration
@EnableBatchProcessing
public class ParImparBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Bean
	public Job imprimiParImparJob(Step parImparStep) {
		return jobBuilderFactory
				.get("imprimiParImparJob")
				.start(parImparStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}

}
