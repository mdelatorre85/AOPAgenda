package com.ttf.Agenda.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.ttf.Agenda.shared.Agenda;
import com.ttf.Agenda.shared.Usuario;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Principal implements EntryPoint {

	public static final ServiceAsync service = GWT.create(Service.class);

	private static Usuario usuario;
	private static Agenda agenda;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		DialogoLogin login = new DialogoLogin();
		login.show();
	}

	public static Usuario getUsuario() {
		return usuario;
	}

	public static void setUsuario(Usuario usuario) {
		Principal.usuario = usuario;
	}

	public static Agenda getAgenda() {
		return agenda;
	}

	public static void setAgenda(Agenda agenda) {
		Principal.agenda = agenda;
	}

}
