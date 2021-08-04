package com.example.demo2;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.services.EntradaOrdenar;
import com.example.services.OrdenarService;

@SpringBootApplication
@Controller
public class Demo2Application implements OrdenarService{

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

	@PostMapping(value = "/ordenar")
	public List<Persona> ordenar(
	@RequestBody EntradaOrdenar entradaOrdenar) {
		Collections.sort(entradaOrdenar.getListaPersona(), new Comparator<Persona>() {
		    @Override
		    public int compare(Persona p1, Persona p2) {
		    	if("AP".equals(entradaOrdenar.getFiltro())) {
		    		return p1.getApellido().compareTo(p2.getApellido());
		    	} else if("ED".equals(entradaOrdenar.getFiltro())) {
		    		return p1.getEdad() > p2.getEdad() ? -1 : (p1.getEdad() < p2.getEdad()) ? 1 : 0;
		    	} else {
		    		return p1.getNombre().compareTo(p2.getNombre());
		    	}
		    }
		});
		
		return entradaOrdenar.getListaPersona();
	}
}
