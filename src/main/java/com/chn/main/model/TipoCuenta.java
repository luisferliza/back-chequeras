package com.chn.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "TABLA DE TIPOS DE CUENTA")
@Entity
@Table(name = "TIPO_CUENTA")
public class TipoCuenta {
	@ApiModelProperty(notes = "OBLIGATORIO, IDENTIFICA EL TIPO DE CUENTA BANCARIA DE MANERA UNICA")
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	private Long idTipoCuenta;
	
	@ApiModelProperty(notes = "OBLIGATORIO, INDICA EL NOMBRE DEL TIPO DE CUENTA")
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;

	public Long getIdTipoCuenta() {
		return idTipoCuenta;
	}

	public void setIdTipoCuenta(Long idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
