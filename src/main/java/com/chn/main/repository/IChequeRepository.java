package com.chn.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chn.main.model.Cheque;

public interface IChequeRepository extends JpaRepository<Cheque, Long>{

}
