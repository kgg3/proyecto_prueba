package com.example.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo2.Persona;

@RestController
public interface PersonasService {
	
	@RequestMapping(value = "/personas", method = RequestMethod.GET)
	public List<Persona> personas(
	@RequestParam(value = "literalBusqueda", defaultValue = "") String literalBusqueda, 
	@RequestParam(value = "filtro", defaultValue = "NO") String filtro,
	@RequestParam(value = "filtroCampo", defaultValue = "NO") String filtroCampo);
	

}
