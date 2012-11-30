package com.ttf.Agenda.client;

import java.util.TreeSet;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ttf.Agenda.shared.Agenda;
import com.ttf.Agenda.shared.Persona;
import com.ttf.Agenda.shared.PersonaAmigo;
import com.ttf.Agenda.shared.PersonaEscuela;
import com.ttf.Agenda.shared.PersonaFamilia;
import com.ttf.Agenda.shared.Usuario;
import com.ttf.Agenda.shared.wrapper.AgendaWrapper;
import com.ttf.Agenda.shared.wrapper.LoginWrapper;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ServiceAsync {

	void doLogin(String nombreDeUsuario, String contrasenaDeUsuaro,
			AsyncCallback<LoginWrapper> callback);

	void saveNewUsuario(Usuario nuevo, AsyncCallback<Usuario> callback);

	void saveNewAgenda(Long usuarioId, Agenda agenda,
			AsyncCallback<TreeSet<Agenda>> callback);

	void savePersona(Long usuarioId, Long agendaId, Persona nueva,
			AsyncCallback<Persona> callback);

	void getPersonas(Long usuarioId, Long agendaId,
			AsyncCallback<AgendaWrapper> callback);

	void getPersonaFamilia(Long usuarioId, Long agendaId, Long personaId,
			AsyncCallback<PersonaFamilia> callback);

	void getPersonaAmigo(Long usuarioId, Long agendaId, Long personaId,
			AsyncCallback<PersonaAmigo> callback);

	void getPersonaEscuela(Long usuarioId, Long agendaId, Long personaId,
			AsyncCallback<PersonaEscuela> callback);

}
