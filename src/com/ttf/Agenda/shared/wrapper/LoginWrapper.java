package com.ttf.Agenda.shared.wrapper;

import java.io.Serializable;
import java.util.TreeSet;

import com.ttf.Agenda.shared.Agenda;
import com.ttf.Agenda.shared.Persona;
import com.ttf.Agenda.shared.PersonaAmigo;
import com.ttf.Agenda.shared.PersonaEscuela;
import com.ttf.Agenda.shared.PersonaFamilia;
import com.ttf.Agenda.shared.Usuario;

public class LoginWrapper implements Serializable {

	private static final long serialVersionUID = -2304079935377692197L;

	private Usuario usuario;

	private TreeSet<Agenda> agendas;

	private TreeSet<PersonaEscuela> personasEscuela;
	private TreeSet<PersonaAmigo> personasAmigo;
	private TreeSet<PersonaFamilia> personasFamilia;

	private Persona persona;

	public LoginWrapper() {
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TreeSet<Agenda> getAgendas() {
		if (agendas == null) {
			agendas = new TreeSet<Agenda>();
		}
		return agendas;
	}

	public void setAgendas(TreeSet<Agenda> agendas) {
		this.agendas = agendas;
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