package com.aluraCursos.Spring_Framework;
import com.aluraCursos.Spring_Framework.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringFrameworkApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringFrameworkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.showsTheMenu();
	}


}
