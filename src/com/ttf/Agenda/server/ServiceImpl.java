package com.ttf.Agenda.server;

import java.util.TreeSet;

import com.google.appengine.api.datastore.QueryResultIterator;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.ttf.Agenda.client.Service;
import com.ttf.Agenda.server.util.PersonaUtil;
import com.ttf.Agenda.server.util.RegisterUtil;
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

	// public static final ApplicationContext ct = new
	// ClassPathXmlApplicationContext(
	// "spring.xml");

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

		// Validacion de parametros
		if (nombreDeUsuario == null) {
			throw new IllegalArgumentException("Parametro nombreDeUsuario null");
		} else {
			if (nombreDeUsuario.length() == 0) {
				throw new IllegalArgumentException(
						"Parametro nombreDeUsuario vacio");
			}
		}

		if (contrasenaDeUsuario == null) {
			throw new IllegalArgumentException("Parametro nombreDeUsuario null");
		} else {
			if (contrasenaDeUsuario.length() == 0) {
				throw new IllegalArgumentException(
						"Parametro contrasenaDeUsuaro vacio");
			}
		}

		Objectify ofy = ObjectifyService.begin();

		RegisterUtil.registerClassInObjectify(Usuario.class);
		Usuario usuario = ofy.query(Usuario.class)
				.filter("nombreDeUsuario", nombreDeUsuario)
				.filter("contrasenaDeUsuario", contrasenaDeUsuario).limit(1)
				.get();
		if (usuario != null) {
			LoginWrapper retorno = new LoginWrapper();
			retorno.setUsuario(usuario);
			Key<Usuario> usuarioKey = new Key<Usuario>(Usuario.class,
					usuario.getUsuarioId());

			// Se obtienen todos las agendas del usuario
			RegisterUtil.registerClassInObjectify(Agenda.class);
			retorno.getAgendas().addAll(
					ofy.query(Agenda.class).ancestor(usuarioKey).list());

			if (retorno.getAgendas().size() > 0) {

				Agenda agenda = retorno.getAgendas().first();

				if (agenda != null) {
					Key<Agenda> agendaKey = new Key<Agenda>(usuarioKey,
							Agenda.class, agenda.getId());

					// Se obtienen las Personas Familia de la primera libreta;
					RegisterUtil.registerClassInObjectify(PersonaFamilia.class);
					QueryResultIterator<PersonaFamilia> familiares = ofy
							.query(PersonaFamilia.class).ancestor(agendaKey)
							.iterator();
					PersonaFamilia familiar;
					if (familiares.hasNext()) {
						retorno.setPersona(familiares.next());
						retorno.getPersonasFamilia().add(
								(PersonaFamilia) retorno.getPersona());
					}
					while (familiares.hasNext()) {
						familiar = familiares.next();
						familiar.setAllAtributesNullButName();
						retorno.getPersonasFamilia().add(familiar);
					}

					// Se obtienen las Personas Amigos de la primera libreta;
					RegisterUtil.registerClassInObjectify(PersonaAmigo.class);
					QueryResultIterator<PersonaAmigo> amigos = ofy
							.query(PersonaAmigo.class).ancestor(agendaKey)
							.iterator();
					if (retorno.getPersona() == null) {
						if (amigos.hasNext()) {
							retorno.setPersona(amigos.next());
							retorno.getPersonasAmigo().add(
									(PersonaAmigo) retorno.getPersona());
						}
					}
					PersonaAmigo amigo;
					while (amigos.hasNext()) {
						amigo = amigos.next();
						amigo.setAllAtributesNullButName();
						retorno.getPersonasAmigo().add(amigo);
					}

					// Se obtienen las Personas Escuela de la primera libreta;
					RegisterUtil.registerClassInObjectify(PersonaEscuela.class);
					QueryResultIterator<PersonaEscuela> companeros = ofy
							.query(PersonaEscuela.class).ancestor(agendaKey)
							.iterator();
					if (retorno.getPersona() == null) {
						if (companeros.hasNext()) {
							retorno.setPersona(companeros.next());
							retorno.getPersonasEscuela().add(
									(PersonaEscuela) retorno.getPersona());
						}
					}
					PersonaEscuela companero;
					while (companeros.hasNext()) {
						companero = companeros.next();
						companero.setAllAtributesNullButName();
						retorno.getPersonasEscuela().add(companero);
					}
				}
			}
			return retorno;
		} else {
			// No se ha encontrado un usuario con la combinación
			return null;
		}
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
		if (nuevo == null) {
			throw new IllegalArgumentException("Parámetro usuario null");
		}
		Objectify ofy = ObjectifyService.begin();
		RegisterUtil.registerClassInObjectify(Usuario.class);

		int size = ofy.query(Usuario.class)
				.filter("nombreDeUsuario", nuevo.getNombreDeUsuario())
				.listKeys().size();
		if (size > 0) {
			// ya existe un usuario con ese nombre registrado;
			return null;
		} else {
			ofy.put(nuevo);
			return nuevo;
		}
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
		if (usuarioId == null || agenda == null) {
			throw new IllegalArgumentException("Parámetros Null");
		}
		Key<Usuario> usuarioKey = new Key<Usuario>(Usuario.class, usuarioId);
		agenda.setUsuario(usuarioKey);

		Objectify ofy = ObjectifyService.begin();

		// Se valida si el usuario tiene ya alguna agenda con ese nombre, en
		// caso afirmativo se le agrega un indice a el nombre
		int yaExisteLaAgenda = ofy.query(Agenda.class).ancestor(usuarioKey)
				.filter("nombreDeAgenda", agenda.getNombreDeAgenda())
				.listKeys().size();
		if (yaExisteLaAgenda != 0) {
			agenda.setNombreDeAgenda(agenda.getNombreDeAgenda()
					+ yaExisteLaAgenda);
		}

		ofy.put(agenda);

		TreeSet<Agenda> retorno = new TreeSet<Agenda>();
		retorno.addAll(ofy.query(Agenda.class).ancestor(usuarioKey).list());
		return retorno;
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
		if (usuarioId == null || agendaId == null || nueva == null) {
			throw new IllegalArgumentException("Parámetros Null.");
		}
		Key<Usuario> usuarioKey = new Key<Usuario>(Usuario.class, usuarioId);
		Key<Agenda> agendaKey = new Key<Agenda>(usuarioKey, Agenda.class,
				agendaId);

		Objectify ofy = ObjectifyService.begin();
		RegisterUtil.registerClassInObjectify(Persona.class);
		nueva.setAgenda(agendaKey);
		ofy.put(nueva);
		// TODO Hay que checar si ofy guarda en la clase padre o en la clase
		// hijas

		return nueva;
	}

	@Override
	public AgendaWrapper getPersonas(Long usuarioId, Long agendaId) {
		if (usuarioId == null || agendaId == null) {
			throw new IllegalArgumentException("Atributo null");
		}

		Key<Usuario> usuarioKey = new Key<Usuario>(Usuario.class, usuarioId);
		Key<Agenda> agendaKey = new Key<Agenda>(usuarioKey, Agenda.class,
				agendaId);

		Objectify ofy = ObjectifyService.begin();
		AgendaWrapper retorno = new AgendaWrapper();

		// Se obtienen las Personas Familia de la primera libreta;
		RegisterUtil.registerClassInObjectify(PersonaFamilia.class);
		QueryResultIterator<PersonaFamilia> familiares = ofy
				.query(PersonaFamilia.class).ancestor(agendaKey).iterator();
		PersonaFamilia familiar;
		if (familiares.hasNext()) {
			PersonaFamilia f = familiares.next();
			retorno.setPersona(f);
			retorno.getPersonasFamilia().add(f);
		}
		while (familiares.hasNext()) {
			familiar = familiares.next();
			familiar.setAllAtributesNullButName();
			retorno.getPersonasFamilia().add(familiar);
		}

		// Se obtienen las Personas Amigos de la primera libreta;
		RegisterUtil.registerClassInObjectify(PersonaAmigo.class);
		QueryResultIterator<PersonaAmigo> amigos = ofy
				.query(PersonaAmigo.class).ancestor(agendaKey).iterator();
		if (retorno.getPersona() == null) {
			if (amigos.hasNext()) {
				PersonaAmigo a = amigos.next();
				retorno.setPersona(a);
				retorno.getPersonasAmigo().add(a);
			}
		}
		PersonaAmigo amigo;
		while (amigos.hasNext()) {
			amigo = amigos.next();
			amigo.setAllAtributesNullButName();
			retorno.getPersonasAmigo().add(amigo);
		}

		// Se obtienen las Personas Escuela de la primera libreta;
		RegisterUtil.registerClassInObjectify(PersonaEscuela.class);
		QueryResultIterator<PersonaEscuela> companeros = ofy
				.query(PersonaEscuela.class).ancestor(agendaKey).iterator();
		if (retorno.getPersona() == null) {
			if (companeros.hasNext()) {
				PersonaEscuela c = companeros.next();
				retorno.setPersona(c);
				retorno.getPersonasEscuela().add(c);
			}
		}
		PersonaEscuela companero;
		while (companeros.hasNext()) {
			companero = companeros.next();
			companero.setAllAtributesNullButName();
			retorno.getPersonasEscuela().add(companero);
		}
		return retorno;
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
		Key<Usuario> usuarioKey = new Key<Usuario>(Usuario.class, usuarioId);
		Key<Agenda> agendaKey = new Key<Agenda>(usuarioKey, Agenda.class,
				agendaId);
		return PersonaUtil.getPersonaFamilia(agendaKey, personaId);
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
		Key<Usuario> usuarioKey = new Key<Usuario>(Usuario.class, usuarioId);
		Key<Agenda> agendaKey = new Key<Agenda>(usuarioKey, Agenda.class,
				agendaId);
		return PersonaUtil.getPersonaAmigo(agendaKey, personaId);
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
		Key<Usuario> usuarioKey = new Key<Usuario>(Usuario.class, usuarioId);
		Key<Agenda> agendaKey = new Key<Agenda>(usuarioKey, Agenda.class,
				agendaId);
		return PersonaUtil.gePersonaEscuela(agendaKey, personaId);
	}

}
