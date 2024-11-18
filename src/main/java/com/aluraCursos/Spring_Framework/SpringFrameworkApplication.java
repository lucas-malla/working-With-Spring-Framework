package com.aluraCursos.Spring_Framework;

import com.aluraCursos.Spring_Framework.model.SerieDatas;
import com.aluraCursos.Spring_Framework.service.ApiConsumption;
import com.aluraCursos.Spring_Framework.service.CombinesDatas;
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
		var addApi = new ApiConsumption();
		var json = addApi.getDatas("http://www.omdbapi.com/?t=game+of+thrones&apikey=25408fcb");

		System.out.println(json);
		//creamos una intancia PARA CONVERTIR LOS DATOS
		var convert = new CombinesDatas();

		//llamamos al metodo par acombertir datos
		//


		var datas = convert.getDatas(json, SerieDatas.class);
		System.out.println(datas);

	}
}
