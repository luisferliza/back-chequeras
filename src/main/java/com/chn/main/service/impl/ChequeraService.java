package com.chn.main.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chn.main.model.Chequera;
import com.chn.main.repository.IChequeraRepository;
import com.chn.main.service.IChequeraService;

@Service
public class ChequeraService implements IChequeraService{

	@Autowired
	private IChequeraRepository dao;
	
	@Override
	public Chequera registrar(Chequera t) {
		return dao.save(t);
	}

	@Override
	public Chequera modificar(Chequera t) {
		return dao.save(t);
	}

	@Override
	public void eliminar(Long id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<Chequera> listar() {
		return dao.findAll();
	}

	@Override
	public Chequera listarId(Long id) {
		Optional<Chequera> tmp = dao.findById(id);
		if (tmp.isPresent()) {
			return tmp.get();
		}
		return null;
	}

}
