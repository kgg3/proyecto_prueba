package com.example.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo2.Persona;

@RestController
public interface OrdenarService {
	
	@RequestMapping(value = "/ordenar", method = RequestMethod.POST)
	public List<Persona> ordenar(@RequestBody EntradaOrdenar entradaOrdenar);
	

}
