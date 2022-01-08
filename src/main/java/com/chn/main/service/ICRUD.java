package com.chn.main.service;

import java.util.List;

public interface ICRUD<T> {
	T registrar(T t);
	T modificar(T t);
	void eliminar(Long id);
	List<T> listar();
	T listarId(Long id);
	
}
