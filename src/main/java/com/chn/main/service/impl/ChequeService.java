package com.chn.main.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chn.main.model.Cheque;
import com.chn.main.repository.IChequeRepository;
import com.chn.main.service.IChequeService;

@Service
public class ChequeService implements IChequeService{

	@Autowired
	private IChequeRepository dao; 
	
	
	@Override
	public Cheque registrar(Cheque t) {
		return dao.save(t);
	}

	@Override
	public Cheque modificar(Cheque t) {
		return dao.save(t);
	}

	@Override
	public void eliminar(Long id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<Cheque> listar() {
		return dao.findAll();
	}

	@Override
	public Cheque listarId(Long id) {
		Optional<Cheque> tmp = dao.findById(id);
		if (tmp.isPresent()) {
			return tmp.get();
		}
		return null;
	}

}
