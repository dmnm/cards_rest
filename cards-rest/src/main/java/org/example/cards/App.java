package org.example.cards;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("org.example.cards")
public class App {
	public static void main(final String[] args) {
		new SpringApplicationBuilder(App.class).run(args);
	}
}
