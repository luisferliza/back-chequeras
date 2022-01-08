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
import com.chn.main.model.Cliente;
import com.chn.main.service.IClienteService;

@RestController 
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private IClienteService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> listar() {
		return new ResponseEntity<List<Cliente>>(service.listar(), HttpStatus.OK);
	}	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> listarPorId(@PathVariable("id") Long id) {
		Cliente cliente = service.listarId(id);
		if (cliente == null) {
			throw new ModeloNotFoundException("No se encontró el cliente con el ID: " + id);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrar(@Valid @RequestBody Cliente cliente) {
		Cliente a = service.registrar(cliente);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(a.getIdCliente()).toUri();
		return ResponseEntity.created(location).build(); 
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> modificar(@Valid @RequestBody Cliente cliente) {		
		service.modificar(cliente);
		return new ResponseEntity<Cliente>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		Cliente cliente = service.listarId(id);
		if (cliente == null) {
			throw new ModeloNotFoundException("No se encontró el ciente con el ID: " + id);
		}
		service.eliminar(id);
	}
}
