package com.ttf.Agenda.shared;

import java.io.Serializable;

public class PersonaFamilia extends Persona implements Serializable, RelacionKind {

	private static final long serialVersionUID = -131313326335637286L;

	private int relacion;

	public int getRelacion() {
		return relacion;
	}

	public void setRelacion(int relacion) {
		this.relacion = relacion;
	}

}
