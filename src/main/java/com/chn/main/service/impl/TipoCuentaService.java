package com.chn.main.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chn.main.model.TipoCuenta;
import com.chn.main.repository.ITipoCuentaRepository;
import com.chn.main.service.ITipoCuentaService;

@Service
public class TipoCuentaService implements ITipoCuentaService{

	@Autowired
	private ITipoCuentaRepository dao; 
	
	@Override
	public TipoCuenta registrar(TipoCuenta t) {
		return dao.save(t);
	}

	@Override
	public TipoCuenta modificar(TipoCuenta t) {
		return dao.save(t);
	}

	@Override
	public void eliminar(Long id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<TipoCuenta> listar() {
		return dao.findAll();
	}

	@Override
	public TipoCuenta listarId(Long id) {
		Optional<TipoCuenta> tmp = dao.findById(id);
		if (tmp.isPresent()) {
			return tmp.get();
		}
		return null;
	}

}
