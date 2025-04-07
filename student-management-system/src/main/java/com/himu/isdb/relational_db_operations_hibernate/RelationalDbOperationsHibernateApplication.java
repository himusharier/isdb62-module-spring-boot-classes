package com.himu.isdb.relational_db_operations_hibernate;

import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RelationalDbOperationsHibernateApplication {

	public static void main(String[] args) {
		// SpringApplication.run(RelationalDbOperationsHibernateApplication.class,
		// args);
		SpringApplication app = new SpringApplication(RelationalDbOperationsHibernateApplication.class);

		app.setBanner(new Banner() {
			@Override
			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {

				// out.println("");
				// out.println("====================================");
				// out.println(" Custom Spring Boot Banner! ");
				// out.println(" Sharier Himu ");
				// out.println("====================================");

				// out.println("__ _ _ _");
				// out.println("/ _\\ |__ __ _ _ __(_) ___ _ __ /\\ /(_)_ __ ___ _ _");
				// out.println("\\ \\| '_ \\ / _` | '__| |/ _ \\ '__| / /_/ / | '_ ` _ \\| | |
				// |");
				// out.println("_\\ \\ | | | (_| | | | | __/ | / __ /| | | | | | | |_| |");
				// out.println("\\__/|_| |_|\\__,_|_| |_|\\___|_| \\/ /_/ |_|_| |_| |_|\__,_|");
				// out.println("");

			}
		});

		app.run(args);
	}

}
