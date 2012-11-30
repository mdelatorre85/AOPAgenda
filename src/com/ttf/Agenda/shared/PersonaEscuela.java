package com.ttf.Agenda.shared;

import java.io.Serializable;
import java.util.TreeSet;

import javax.persistence.Embedded;

/**
 * un contacto de la libreta de escolar
 * 
 * @author miguelangeldelatorre
 * 
 */
public class PersonaEscuela extends Persona implements Serializable {

	private static final long serialVersionUID = 812549099096440949L;

	// Para los contactos en la escuela, se agregan las materias en las que se
	// tiene el contacto.
	@Embedded
	private TreeSet<Materia> materias;

	public TreeSet<Materia> getMaterias() {
		if (materias == null) {
			materias = new TreeSet<Materia>();
		}
		return materias;
	}

	@Override
	public void setAllAtributesNullButName() {
		super.setAllAtributesNullButName();
		materias = null;
	}

}
