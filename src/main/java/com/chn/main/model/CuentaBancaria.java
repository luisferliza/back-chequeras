package com.chn.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "TABLA DE CUENTAS BANCARIAS")
@Entity
@Table(name = "CUENTA_BANCARIA")
public class CuentaBancaria {
	@ApiModelProperty(notes = "OBLIGATORIO, IDENTIFICA LA CUENTA BANCARIA DE MANERA UNICA")
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	private Long idCuentaBancaria;
	
	@ApiModelProperty(notes = "OBLIGATORIO, INDICA EL NÚMERO DE CUENTA, EL CAMPO ES ÚNICO") 
	@Column(name = "noCuenta", nullable = false, length = 25, unique = true) 
	private String noCuenta;
	
	@ApiModelProperty(notes = "OBLIGATORIO, INDICA EL TIPO DE CUENTA AL QUE PERTENECE")
	@ManyToOne
	@JoinColumn(name = "idTipoCuenta", nullable = false, foreignKey = @ForeignKey(name = "cuenta_tipo"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private TipoCuenta tipoCuenta;	
	
	@ApiModelProperty(notes = "OBLIGATORIO, INDICA EL CLIENTE AL QUE PERTENECE LA CUENTA")
	@ManyToOne
	@JoinColumn(name = "idCliente", nullable = false, foreignKey = @ForeignKey(name = "cuenta_cliente"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Cliente cliente; 	
	
	
	@ApiModelProperty(notes = "OBLIGATORIO, INDICA EL SALDO ACTUAL DE LA CUENTA")
	@Column(name = "saldo", nullable = false)
	private Double saldo; 

	public Long getIdCuentaBancaria() {
		return idCuentaBancaria;
	}

	public void setIdCuentaBancaria(Long idCuentaBancaria) {
		this.idCuentaBancaria = idCuentaBancaria;
	}

	public String getNoCuenta() {
		return noCuenta;
	}

	public void setNoCuenta(String noCuenta) {
		this.noCuenta = noCuenta;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	
}	
