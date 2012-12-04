package com.ttf.Agenda.shared;

import java.io.Serializable;

import javax.persistence.Id;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;

public class Agenda implements Serializable, Comparable<Agenda> {

	private static final long serialVersionUID = -2463937721422650538L;

	@Id
	private Long id;

	@Parent
	private Key<Usuario> usuario;

	private String nombreDeAgenda;

	public Agenda() {
	}

	public Long getId() {
		return id;
	}

	public Key<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(Key<Usuario> usuario) {
		this.usuario = usuario;
	}

	public String getNombreDeAgenda() {
		return nombreDeAgenda;
	}

	public void setNombreDeAgenda(String nombreDeAgenda) {
		this.nombreDeAgenda = nombreDeAgenda;
	}

	@Override
	public int compareTo(Agenda o) {
		return nombreDeAgenda.compareTo(o.nombreDeAgenda);
	}

	@Override
	public String toString() {
		return id + " " + nombreDeAgenda;
	}

}
