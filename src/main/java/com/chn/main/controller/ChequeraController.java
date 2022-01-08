package com.chn.main.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.chn.main.exception.ModeloNotFoundException;
import com.chn.main.model.Chequera;
import com.chn.main.service.IChequeraService;

@RestController 
@RequestMapping("/chequeras")
public class ChequeraController {
	@Autowired
	private IChequeraService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Chequera>> listar() {
		return new ResponseEntity<List<Chequera>>(service.listar(), HttpStatus.OK);
	}	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Chequera> listarPorId(@PathVariable("id") Long id) {
		Chequera chequera = service.listarId(id);
		if (chequera == null) {
			throw new ModeloNotFoundException("No se encontró la chequera con el ID: " + id);
		}
		return new ResponseEntity<Chequera>(chequera, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrar(@Valid @RequestBody Chequera chequera) {
		Chequera a = service.registrar(chequera);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(a.getIdChequera()).toUri();
		return ResponseEntity.created(location).build(); 
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Chequera> modificar(@Valid @RequestBody Chequera chequera) {		
		service.modificar(chequera);
		return new ResponseEntity<Chequera>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		Chequera chequera = service.listarId(id);
		if (chequera == null) {
			throw new ModeloNotFoundException("No se encontró la chequera con el ID: " + id);
		}
		service.eliminar(id);
	}
}
