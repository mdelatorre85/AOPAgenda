package com.ttf.Agenda.shared;

import java.io.Serializable;

import javax.persistence.Id;

/**
 * El telefono de algún contacto
 * 
 * @author miguelangeldelatorre
 * 
 */
public class Telefono implements Serializable, Comparable<Telefono>,
		ComunicacionesKind {

	private static final long serialVersionUID = 603394552390628253L;

	@Id
	private Long id;

	private int tipoDeTelefono;

	private String claveDeLargaDistancia;
	private String numeroTelefonico;
	private String extension;

	public Telefono() {
	}

	public Telefono(String telefono, int tipoDeTelefono) {
		numeroTelefonico = telefono;
		this.tipoDeTelefono = tipoDeTelefono;
	}

	public int getTipoDeTelefono() {
		return tipoDeTelefono;
	}

	public void setTipoDeTelefono(int tipoDeTelefono) {
		this.tipoDeTelefono = tipoDeTelefono;
	}

	public String getClaveDeLargaDistancia() {
		return claveDeLargaDistancia;
	}

	public void setClaveDeLargaDistancia(String claveDeLargaDistancia) {
		this.claveDeLargaDistancia = claveDeLargaDistancia;
	}

	public String getNumeroTelefonico() {
		return numeroTelefonico;
	}

	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int compareTo(Telefono o) {
		return tipoDeTelefono - o.tipoDeTelefono;
	}

	@Override
	public String toString() {
		return id + " " + numeroTelefonico;
	}

}
