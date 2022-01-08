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

@ApiModel(description = "TABLA DE CHEQUES")
@Entity
@Table(name = "CHEQUE")
public class Cheque {
	@ApiModelProperty(notes = "OBLIGATORIO, IDENTIFICA AL CHEQUE DE MANERA UNICA")
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	private Long idCheque;
	
	
	@ApiModelProperty(notes = "OBLIGATORIO, INDICA EL NÚMERO DE CHEQUE, EL CAMPO ES ÚNICO") 
	@Column(name = "noCheque", nullable = false, length = 25, unique = true) 
	private String noCheque;
	
	@ApiModelProperty(notes = "OBLIGATORIO, INDICA LA CHEQUERA A LA QUE PERTENECE")
	@ManyToOne
	@JoinColumn(name = "idChequera", nullable = false, foreignKey = @ForeignKey(name = "cheque_chequera"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Chequera chequera;
	
	@ApiModelProperty(notes = "OBLIGATORIO, INDICA SI EL CHEQUE HA SIDO COBRADO O NO.") 
	@Column(name = "cobrado", nullable = false) 
	private Boolean cobrado = false;

	public Long getIdCheque() {
		return idCheque;
	}

	public void setIdCheque(Long idCheque) {
		this.idCheque = idCheque;
	}

	public String getNoCheque() {
		return noCheque;
	}

	public void setNoCheque(String noCheque) {
		this.noCheque = noCheque;
	}

	public Chequera getChequera() {
		return chequera;
	}

	public void setChequera(Chequera chequera) {
		this.chequera = chequera;
	}

	public Boolean getCobrado() {
		return cobrado;
	}

	public void setCobrado(Boolean cobrado) {
		this.cobrado = cobrado;
	}	
}
