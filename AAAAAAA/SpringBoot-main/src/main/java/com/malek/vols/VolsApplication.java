package com.malek.vols;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.malek.vols.entities.Aeroport;
import com.malek.vols.entities.Vol;

@SpringBootApplication
public class VolsApplication implements CommandLineRunner{

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(VolsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Vol.class,Aeroport.class);

	}
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

}
