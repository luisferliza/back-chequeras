package com.chn.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chn.main.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long>{

}
