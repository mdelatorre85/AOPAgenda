package com.ttf.Agenda.client;

import java.util.TreeSet;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.ttf.Agenda.shared.Agenda;
import com.ttf.Agenda.shared.Persona;
import com.ttf.Agenda.shared.PersonaAmigo;
import com.ttf.Agenda.shared.PersonaEscuela;
import com.ttf.Agenda.shared.PersonaFamilia;
import com.ttf.Agenda.shared.Usuario;
import com.ttf.Agenda.shared.wrapper.AgendaWrapper;
import com.ttf.Agenda.shared.wrapper.LoginWrapper;

@RemoteServiceRelativePath("s")
public interface Service extends RemoteService {

	/**
	 * 
	 * @param nombreDeUsuario
	 *            el nombre de usuario como fue escrito por el mismo
	 * @param contrasenaDeUsuaro
	 *            la contraseña del usuario como fue escrito por el mismo
	 * @return en caso de éxito la información requerida para dibujar la ventana
	 *         principal; null de lo contrario
	 */
	public LoginWrapper doLogin(String nombreDeUsuario,
			String contrasenaDeUsuaro);

	/**
	 * Guarda el nuevo usuario
	 * 
	 * @return en caso de éxito regresa el usuario que ya ha sido guardado. En
	 *         casa de fallo (por ejemplo que ya exista un usuario con el nombre
	 *         propuesto) regresa null;
	 */
	public Usuario saveNewUsuario(Usuario nuevo);

	/**
	 * Guarda una nueva agenda
	 * 
	 * @param usuarioId
	 *            el usuario que crea una nueva agenda
	 * @param agenda
	 *            la agenda que desea creear el usuario
	 * @return En caso de éxito todas las agendas del usuario; de lo contrario
	 *         null
	 */
	public TreeSet<Agenda> saveNewAgenda(Long usuarioId, Agenda agenda);

	/**
	 * 
	 * @param usuarioId
	 *            el usuario que guarda a la persona
	 * @param agendaId
	 *            la agenda en la que iba a guardar la persona el usuario
	 * @param nueva
	 *            la persona a guaradar
	 * @return
	 */
	public Persona savePersona(Long usuarioId, Long agendaId, Persona nueva);

	/**
	 * 
	 * @param usuarioId
	 *            el usuario que solicita las personas de la agenda
	 * @param agendaId
	 *            la agenda de la que se solicitan las personas
	 * @return las personas de la agenda
	 */
	public AgendaWrapper getPersonas(Long usuarioId, Long agendaId);

	/**
	 * Obtiene a la persona solicitada para mostrar sus detalles
	 * 
	 * @param usuarioId
	 *            el identificador único del usuario que solicita la persona
	 * @param agendaId
	 *            el identificador único de la agenda de la que el usuario
	 *            solicita la persona
	 * @param personaId
	 *            el identificador único de la persona solicitada
	 * @return en caso de éxito la persona solicitada, null de lo contrario
	 */
	public PersonaFamilia getPersonaFamilia(Long usuarioId, Long agendaId,
			Long personaId);

	/**
	 * Obtiene a la persona solicitada para mostrar sus detalles
	 * 
	 * @param usuarioId
	 *            el identificador único del usuario que solicita la persona
	 * @param agendaId
	 *            el identificador único de la agenda de la que el usuario
	 *            solicita la persona
	 * @param personaId
	 *            el identificador único de la persona solicitada
	 * @return en caso de éxito la persona solicitada, null de lo contrario
	 */
	public PersonaAmigo getPersonaAmigo(Long usuarioId, Long agendaId,
			Long personaId);

	/**
	 * Obtiene a la persona solicitada para mostrar sus detalles
	 * 
	 * @param usuarioId
	 *            el identificador único del usuario que solicita la persona
	 * @param agendaId
	 *            el identificador único de la agenda de la que el usuario
	 *            solicita la persona
	 * @param personaId
	 *            el identificador único de la persona solicitada
	 * @return en caso de éxito la persona solicitada, null de lo contrario
	 */
	public PersonaEscuela getPersonaEscuela(Long usuarioId, Long agendaId,
			Long personaId);

}
