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
import com.chn.main.model.Cheque;
import com.chn.main.service.IChequeService;


@RestController 
@RequestMapping("/cheques")
public class ChequeController {
	@Autowired
	private IChequeService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cheque>> listar() {
		return new ResponseEntity<List<Cheque>>(service.listar(), HttpStatus.OK);
	}	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cheque> listarPorId(@PathVariable("id") Long id) {
		Cheque cheque = service.listarId(id);
		if (cheque == null) {
			throw new ModeloNotFoundException("No se encontró el cheque con el ID: " + id);
		}
		return new ResponseEntity<Cheque>(cheque, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrar(@Valid @RequestBody Cheque cheque) {
		Cheque a = service.registrar(cheque);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(a.getIdCheque()).toUri();
		return ResponseEntity.created(location).build(); 
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cheque> modificar(@Valid @RequestBody Cheque cheque) {		
		service.modificar(cheque);
		return new ResponseEntity<Cheque>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		Cheque cheque = service.listarId(id);
		if (cheque == null) {
			throw new ModeloNotFoundException("No se encontró el cheque con el ID: " + id);
		}
		service.eliminar(id);
	}
}
