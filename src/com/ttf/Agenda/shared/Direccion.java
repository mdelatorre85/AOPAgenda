package com.ttf.Agenda.shared;

import java.io.Serializable;

public class Direccion implements Serializable {

	private static final long serialVersionUID = 1189899899889071779L;

	// @Id
	// private Long id;

	private String calle;
	private String numero;
	private String colonia;
	private String codigoPostal;
	private String municipio;
	private String estado;
	private String pais;

	public Direccion() {

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(calle);
		sb.append(" ");
		sb.append(numero);
		sb.append(" ");
		sb.append(colonia);
		return sb.toString();
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	// public Long getId() {
	// return id;
	// }

}
