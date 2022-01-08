package com.chn.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chn.main.model.CuentaBancaria;

public interface ICuentaBancariaRepository extends JpaRepository<CuentaBancaria, Long>{

}
