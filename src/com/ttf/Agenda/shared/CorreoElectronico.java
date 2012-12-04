package com.ttf.Agenda.shared;

import java.io.Serializable;

import javax.persistence.Id;

/**
 * un correo electrónico de algún contacto
 * 
 * @author miguelangeldelatorre
 * 
 */
public class CorreoElectronico implements Serializable,
		Comparable<CorreoElectronico>, ComunicacionesKind {

	private static final long serialVersionUID = 6233379005323858267L;

	public CorreoElectronico() {
	}

	public CorreoElectronico(String direccionDeCorreo, int tipoDeCorreo) {
		this.direccionDeCorreo = direccionDeCorreo;
		this.tipoDeCorreo = tipoDeCorreo;
	}

	@Id
	private Long id;

	private int tipoDeCorreo;
	private String direccionDeCorreo;

	@Override
	public int compareTo(CorreoElectronico o) {
		return (int) (getTipoDeCorreo() - o.getTipoDeCorreo());
	}

	public String getDireccionDeCorreo() {
		return direccionDeCorreo;
	}

	public void setDireccionDeCorreo(String direccionDeCorreo) {
		this.direccionDeCorreo = direccionDeCorreo;
	}

	public int getTipoDeCorreo() {
		return tipoDeCorreo;
	}

	public void setTipoDeCorreo(int tipoDeCorreo) {
		this.tipoDeCorreo = tipoDeCorreo;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return id + " " + direccionDeCorreo;
	}

}
