package com.ttf.Agenda.shared.wrapper;

import java.io.Serializable;
import java.util.TreeSet;

import com.ttf.Agenda.shared.Persona;
import com.ttf.Agenda.shared.PersonaAmigo;
import com.ttf.Agenda.shared.PersonaEscuela;
import com.ttf.Agenda.shared.PersonaFamilia;

public class AgendaWrapper implements Serializable {

	private static final long serialVersionUID = -6869210873955110208L;
	private TreeSet<PersonaEscuela> personasEscuela;
	private TreeSet<PersonaAmigo> personasAmigo;
	private TreeSet<PersonaFamilia> personasFamilia;

	private Persona persona;

	public AgendaWrapper() {
	}

	public TreeSet<PersonaEscuela> getPersonasEscuela() {
		if (personasEscuela == null) {
			personasEscuela = new TreeSet<PersonaEscuela>();
		}
		return personasEscuela;
	}

	public void setPersonasEscuela(TreeSet<PersonaEscuela> personasEscuela) {
		this.personasEscuela = personasEscuela;
	}

	public TreeSet<PersonaAmigo> getPersonasAmigo() {
		if (personasAmigo == null) {
			personasAmigo = new TreeSet<PersonaAmigo>();
		}
		return personasAmigo;
	}

	public void setPersonasAmigo(TreeSet<PersonaAmigo> personasAmigo) {
		this.personasAmigo = personasAmigo;
	}

	public TreeSet<PersonaFamilia> getPersonasFamilia() {
		if (personasFamilia == null) {
			personasFamilia = new TreeSet<PersonaFamilia>();
		}
		return personasFamilia;
	}

	public void setPersonasFamilia(TreeSet<PersonaFamilia> personasFamilia) {
		this.personasFamilia = personasFamilia;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
