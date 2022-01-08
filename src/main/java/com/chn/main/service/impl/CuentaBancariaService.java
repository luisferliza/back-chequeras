package com.chn.main.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chn.main.model.CuentaBancaria;
import com.chn.main.repository.ICuentaBancariaRepository;
import com.chn.main.service.ICuentaBancariaService;

@Service
public class CuentaBancariaService implements ICuentaBancariaService{

	@Autowired
	private ICuentaBancariaRepository dao; 
	
	@Override
	public CuentaBancaria registrar(CuentaBancaria t) {
		return dao.save(t);
	}

	@Override
	public CuentaBancaria modificar(CuentaBancaria t) {
		return dao.save(t);
	}

	@Override
	public void eliminar(Long id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<CuentaBancaria> listar() {
		return dao.findAll();
	}

	@Override
	public CuentaBancaria listarId(Long id) {
		Optional<CuentaBancaria> tmp = dao.findById(id);
		if (tmp.isPresent()) {
			return tmp.get();
		}
		return null;
	}

}
