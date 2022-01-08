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
import com.chn.main.model.CuentaBancaria;
import com.chn.main.service.ICuentaBancariaService;

@RestController 
@RequestMapping("/cuentas")
public class CuentaBancariaController {
	@Autowired
	private ICuentaBancariaService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CuentaBancaria>> listar() {
		return new ResponseEntity<List<CuentaBancaria>>(service.listar(), HttpStatus.OK);
	}	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CuentaBancaria> listarPorId(@PathVariable("id") Long id) {
		CuentaBancaria cuentaBancaria = service.listarId(id);
		if (cuentaBancaria == null) {
			throw new ModeloNotFoundException("No se encontró la cuenta con el ID: " + id);
		}
		return new ResponseEntity<CuentaBancaria>(cuentaBancaria, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrar(@Valid @RequestBody CuentaBancaria cuentaBancaria) {
		CuentaBancaria a = service.registrar(cuentaBancaria);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(a.getIdCuentaBancaria()).toUri();
		return ResponseEntity.created(location).build(); 
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CuentaBancaria> modificar(@Valid @RequestBody CuentaBancaria cuentaBancaria) {		
		service.modificar(cuentaBancaria);
		return new ResponseEntity<CuentaBancaria>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		CuentaBancaria cuentaBancaria = service.listarId(id);
		if (cuentaBancaria == null) {
			throw new ModeloNotFoundException("No se encontró la cuenta bancaria con el ID: " + id);
		}
		service.eliminar(id);
	}
}
