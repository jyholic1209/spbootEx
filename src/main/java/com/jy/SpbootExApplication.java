package com.jy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheEnvironmentCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import com.samskivert.mustache.Mustache;


@SpringBootApplication
@ComponentScan({"com.jy"})
public class SpbootExApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpbootExApplication.class, args);
	}
	
	//Override MustacheAutoConfiguration to support defaultValue("")
    @Bean
    public Mustache.Compiler mustacheCompiler(Mustache.TemplateLoader mustacheTemplateLoader,
                                              Environment environment) {

        MustacheEnvironmentCollector collector = new MustacheEnvironmentCollector();
        collector.setEnvironment(environment);

		// default value
        Mustache.Compiler compiler = Mustache.compiler().defaultValue("")
			.withLoader(mustacheTemplateLoader)
            .withCollector(collector);
        return compiler;

    }

}
