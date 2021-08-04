package com.example.demo2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.services.EntradaOrdenar;
import com.example.services.PersonasService;

@SpringBootApplication
@Controller
public class Demo2Application implements PersonasService{

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

	@RequestMapping(value = "/personas", method = RequestMethod.GET)
	public List<Persona> personas(
	@RequestParam(value = "literalBusqueda", defaultValue = "") String literalBusqueda, 
	@RequestParam(value = "filtro", defaultValue = "NO") String filtro, 
	@RequestParam(value = "filtroCampo", defaultValue = "NO") String filtroCampo) {
		List<Persona> listaPersona = new ArrayList<Persona>();
		listaPersona.add(new Persona());
		listaPersona.add(new Persona("Alba", "Guerra", 28));
		listaPersona.add(new Persona("Carlos", "Fernandez", 25));
		listaPersona.add(new Persona("Paco", "Garcia", 60));
		listaPersona.add(new Persona("Maria", "Guitierrez", 44));
		listaPersona.add(new Persona("Gerardo", "Franco", 44));
		
		if("NO".equals(filtroCampo)) {
			listaPersona.removeIf(persona -> !persona.getNombre().contains(literalBusqueda));
		} else if("AP".equals(filtroCampo)) {
			listaPersona.removeIf(persona -> !persona.getApellido().contains(literalBusqueda));
		}	
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		EntradaOrdenar entradaOrdenar = new EntradaOrdenar(listaPersona, filtroCampo);
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(entradaOrdenar,headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Persona>> rateResponse = restTemplate.exchange("http://localhost:12347/ordenar", HttpMethod.POST, requestEntity,new ParameterizedTypeReference<List<Persona>>() {});
		listaPersona = rateResponse.getBody();
		
		return listaPersona;
	}
}
