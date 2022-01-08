package com.chn.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "TABLA DE CLIENTES")
@Entity
@Table(name = "CLIENTE")
public class Cliente {
	@ApiModelProperty(notes = "OBLIGATORIO, IDENTIFICA AL CLIENTE DE MANERA UNICA")
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	private Long idCliente;
	
	@ApiModelProperty(notes = "OBLIGATORIO, INDICA EL NOMBRE DEL USUARIO") 
	@Column(name = "nombre", nullable = false, length = 50) 
	private String nombre;
	
	@ApiModelProperty(notes = "OBLIGATORIO, INDICA EL NOMBRE DEL USUARIO") 
	@Column(name = "apellido", nullable = false, length = 50) 
	private String apellido;
	
	@ApiModelProperty(notes = "OBLIGATORIO, INDICA EL DPI DEL USUARIO")
	@Column(name = "DPI", nullable = false)
	private Long dpi;
	
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getDpi() {
		return dpi;
	}

	public void setDpi(Long dpi) {
		this.dpi = dpi;
	}

}
