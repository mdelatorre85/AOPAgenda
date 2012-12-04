package com.ttf.Agenda.server;

import java.util.TreeSet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ttf.Agenda.client.Service;
import com.ttf.Agenda.shared.Agenda;
import com.ttf.Agenda.shared.Persona;
import com.ttf.Agenda.shared.PersonaAmigo;
import com.ttf.Agenda.shared.PersonaEscuela;
import com.ttf.Agenda.shared.PersonaFamilia;
import com.ttf.Agenda.shared.Usuario;
import com.ttf.Agenda.shared.wrapper.AgendaWrapper;
import com.ttf.Agenda.shared.wrapper.LoginWrapper;

@SuppressWarnings("serial")
public class ServiceImpl extends RemoteServiceServlet implements Service {

	/**
	 * 
	 * @param nombreDeUsuario
	 *            el nombre de usuario como fue escrito por el mismo
	 * @param contrasenaDeUsuaro
	 *            la contraseña del usuario como fue escrito por el mismo
	 * @return en caso de éxito la información requerida para dibujar la ventana
	 *         principal; null de lo contrario
	 */
	@Override
	@Log
	public LoginWrapper doLogin(String nombreDeUsuario,
			String contrasenaDeUsuario) {

		ApplicationContext ct = new ClassPathXmlApplicationContext("spring.xml");
		ServiceBean sb = ct.getBean("serviceBean", ServiceBean.class);
		return sb.doLogin(nombreDeUsuario, contrasenaDeUsuario);
	}

	/**
	 * Guarda el nuevo usuario
	 * 
	 * @return en caso de éxito regresa el usuario que ya ha sido guardado. En
	 *         casa de fallo (por ejemplo que ya exista un usuario con el nombre
	 *         propuesto) regresa null;
	 */
	@Override
	public Usuario saveNewUsuario(Usuario nuevo) {
		ApplicationContext ct = new ClassPathXmlApplicationContext("spring.xml");
		ServiceBean sb = ct.getBean("serviceBean", ServiceBean.class);
		return sb.saveNewUsuario(nuevo);
	}

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
	@Override
	public TreeSet<Agenda> saveNewAgenda(Long usuarioId, Agenda agenda) {
		ApplicationContext ct = new ClassPathXmlApplicationContext("spring.xml");
		ServiceBean sb = ct.getBean("serviceBean", ServiceBean.class);
		return sb.saveNewAgenda(usuarioId, agenda);
	}

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
	@Override
	public Persona savePersona(Long usuarioId, Long agendaId, Persona nueva) {
		ApplicationContext ct = new ClassPathXmlApplicationContext("spring.xml");
		ServiceBean sb = ct.getBean("serviceBean", ServiceBean.class);
		return sb.savePersona(usuarioId, agendaId, nueva);
	}

	@Override
	public AgendaWrapper getPersonas(Long usuarioId, Long agendaId) {
		ApplicationContext ct = new ClassPathXmlApplicationContext("spring.xml");
		ServiceBean sb = ct.getBean("serviceBean", ServiceBean.class);
		return sb.getPersonas(usuarioId, agendaId);
	}

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
	@Override
	public PersonaFamilia getPersonaFamilia(Long usuarioId, Long agendaId,
			Long personaId) {
		ApplicationContext ct = new ClassPathXmlApplicationContext("spring.xml");
		ServiceBean sb = ct.getBean("serviceBean", ServiceBean.class);
		return sb.getPersonaFamilia(usuarioId, agendaId, personaId);
	}

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
	@Override
	public PersonaAmigo getPersonaAmigo(Long usuarioId, Long agendaId,
			Long personaId) {
		ApplicationContext ct = new ClassPathXmlApplicationContext("spring.xml");
		ServiceBean sb = ct.getBean("serviceBean", ServiceBean.class);
		return sb.getPersonaAmigo(usuarioId, agendaId, personaId);
	}

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
	@Override
	public PersonaEscuela getPersonaEscuela(Long usuarioId, Long agendaId,
			Long personaId) {
		ApplicationContext ct = new ClassPathXmlApplicationContext("spring.xml");
		ServiceBean sb = ct.getBean("serviceBean", ServiceBean.class);
		return sb.getPersonaEscuela(usuarioId, agendaId, personaId);
	}

}
