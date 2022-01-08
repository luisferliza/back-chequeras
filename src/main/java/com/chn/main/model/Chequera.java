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

@ApiModel(description = "TABLA DE CHEQUERAS")
@Entity
@Table(name = "CHEQUERA")
public class Chequera {
	@ApiModelProperty(notes = "OBLIGATORIO, IDENTIFICA LA CHEQUERA DE MANERA UNICA")
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	private Long idChequera;
	
	@ApiModelProperty(notes = "OBLIGATORIO, INDICA EL NÚMERO DE CHEQUERA, EL CAMPO ES ÚNICO") 
	@Column(name = "noChequera", nullable = false, length = 25, unique = true) 
	private String noChequera;
	
	@ApiModelProperty(notes = "OPCIONAL, INDICA EL NÚMERO DE CHEQUES EN LA CHEQUERA") 
	@Column(name = "cantidadCheques", nullable = true) 
	private int cantidadCheques;
	
	
	@ApiModelProperty(notes = "OBLIGATORIO, INDICA LA CUENTA A LA QUE PERTENECE")
	@ManyToOne
	@JoinColumn(name = "idCuenta", nullable = false, foreignKey = @ForeignKey(name = "chequera_cuenta"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private CuentaBancaria cuenta;	

	public Long getIdChequera() {
		return idChequera;
	}

	public void setIdChequera(Long idChequera) {
		this.idChequera = idChequera;
	}

	public String getNoChequera() {
		return noChequera;
	}

	public void setNoChequera(String noChequera) {
		this.noChequera = noChequera;
	}

	public int getCantidadCheques() {
		return cantidadCheques;
	}

	public void setCantidadCheques(int cantidadCheques) {
		this.cantidadCheques = cantidadCheques;
	}

	public CuentaBancaria getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaBancaria cuenta) {
		this.cuenta = cuenta;
	}
}
