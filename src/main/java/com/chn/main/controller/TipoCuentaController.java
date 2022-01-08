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
import com.chn.main.model.TipoCuenta;
import com.chn.main.service.ITipoCuentaService;

@RestController 
@RequestMapping("/tiposcuenta")
public class TipoCuentaController {
	@Autowired
	private ITipoCuentaService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipoCuenta>> listar() {
		return new ResponseEntity<List<TipoCuenta>>(service.listar(), HttpStatus.OK);
	}	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoCuenta> listarPorId(@PathVariable("id") Long id) {
		TipoCuenta tipoCuenta = service.listarId(id);
		if (tipoCuenta == null) {
			throw new ModeloNotFoundException("No se encontró el tipo de cuenta con el ID: " + id);
		}
		return new ResponseEntity<TipoCuenta>(tipoCuenta, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrar(@Valid @RequestBody TipoCuenta tipoCuenta) {
		TipoCuenta a = service.registrar(tipoCuenta);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(a.getIdTipoCuenta()).toUri();
		return ResponseEntity.created(location).build(); 
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoCuenta> modificar(@Valid @RequestBody TipoCuenta tipoCuenta) {		
		service.modificar(tipoCuenta);
		return new ResponseEntity<TipoCuenta>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		TipoCuenta tipoCuenta = service.listarId(id);
		if (tipoCuenta == null) {
			throw new ModeloNotFoundException("No se encontró el tipo de cuenta con el ID: " + id);
		}
		service.eliminar(id);
	}
}
