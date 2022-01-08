package com.chn.main.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chn.main.model.Cliente;
import com.chn.main.repository.IClienteRepository;
import com.chn.main.service.IClienteService;

@Service
public class ClienteService implements IClienteService{

	
	@Autowired
	private IClienteRepository dao; 
	
	@Override
	public Cliente registrar(Cliente t) {
		return dao.save(t);
	}

	@Override
	public Cliente modificar(Cliente t) {
		return dao.save(t);
	}

	@Override
	public void eliminar(Long id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<Cliente> listar() {
		return dao.findAll();
	}

	@Override
	public Cliente listarId(Long id) {
		Optional<Cliente> tmp = dao.findById(id);
		if (tmp.isPresent()) {
			return tmp.get();
		}
		return null;
	}

}
