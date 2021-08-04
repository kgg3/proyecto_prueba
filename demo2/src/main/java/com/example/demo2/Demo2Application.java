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

import com.example.services.PersonasService;

@SpringBootApplication
@Controller
public class Demo2Application implements PersonasService{

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

	@RequestMapping(value = "/buscador", method = RequestMethod.GET)
	public List<Persona> personas(
	@RequestParam(value = "literalBusqueda", defaultValue = "") String literalBusqueda, 
	@RequestParam(value = "filtro", defaultValue = "NO") String filtro, 
	@RequestParam(value = "filtroCampo", defaultValue = "NO") String filtroCampo) {
		List<Persona> listaPersona = new ArrayList<Persona>();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		final String baseUrl = "http://localhost:12346/personas?literalBusqueda=" + literalBusqueda + "&filtro=" + filtro + "&filtroCampo=" + filtroCampo;
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(listaPersona,headers);
		ParameterizedTypeReference<List<Persona>> responseType = new ParameterizedTypeReference<List<Persona>>() {};
		ResponseEntity<List<Persona>> result = restTemplate.exchange(baseUrl, HttpMethod.GET, requestEntity, responseType);
		listaPersona = result.getBody();
		
		return listaPersona;
	}

}
