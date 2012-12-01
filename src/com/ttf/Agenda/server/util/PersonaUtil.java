package com.ttf.Agenda.server.util;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.ttf.Agenda.server.HelpMeLog;
import com.ttf.Agenda.shared.Agenda;
import com.ttf.Agenda.shared.PersonaAmigo;
import com.ttf.Agenda.shared.PersonaEscuela;
import com.ttf.Agenda.shared.PersonaFamilia;

public class PersonaUtil {

	public static PersonaEscuela gePersonaEscuela(Key<Agenda> agenda,
			Long personaEscuelaId) {

		if (agenda == null || personaEscuelaId == null) {
			throw new IllegalArgumentException("Atributos Null");
		}

		Objectify ofy = ObjectifyService.begin();
		RegisterUtil.registerClassInObjectify(PersonaEscuela.class);

		Key<PersonaEscuela> personaEscuelaKey = new Key<PersonaEscuela>(agenda,
				PersonaEscuela.class, personaEscuelaId);
		PersonaEscuela retorno = ofy.find(personaEscuelaKey);
		if (retorno != null) {
			return retorno;
		} else {
			StringBuilder sb = new StringBuilder(
					"No existe la PersonaEscuela de la agenda: ");
			sb.append(agenda.toString());
			sb.append(" con el Id: ");
			sb.append(personaEscuelaId);
			HelpMeLog.log(sb.toString());
			return null;
		}
	}

	public static PersonaFamilia getPersonaFamilia(Key<Agenda> agenda,
			Long personaFamiliaId) {

		if (agenda == null || personaFamiliaId == null) {
			throw new IllegalArgumentException("Atributos Null");
		}

		Objectify ofy = ObjectifyService.begin();
		RegisterUtil.registerClassInObjectify(PersonaEscuela.class);

		Key<PersonaFamilia> personaEscuelaKey = new Key<PersonaFamilia>(agenda,
				PersonaFamilia.class, personaFamiliaId);
		PersonaFamilia retorno = ofy.find(personaEscuelaKey);
		if (retorno != null) {
			return retorno;
		} else {
			StringBuilder sb = new StringBuilder(
					"No existe la PersonaFamilia de la agenda: ");
			sb.append(agenda.toString());
			sb.append(" con el Id: ");
			sb.append(personaFamiliaId);
			HelpMeLog.log(sb.toString());
			return null;
		}

	}

	public static PersonaAmigo getPersonaAmigo(Key<Agenda> agenda,
			Long personaAmigoId) {

		if (agenda == null || personaAmigoId == null) {
			throw new IllegalArgumentException("Atributos Null");
		}

		Objectify ofy = ObjectifyService.begin();
		RegisterUtil.registerClassInObjectify(PersonaEscuela.class);

		Key<PersonaAmigo> personaAmigoKey = new Key<PersonaAmigo>(agenda,
				PersonaAmigo.class, personaAmigoId);
		PersonaAmigo retorno = ofy.find(personaAmigoKey);
		if (retorno != null) {
			return retorno;
		} else {
			StringBuilder sb = new StringBuilder(
					"No existe la PersonaFamilia de la agenda: ");
			sb.append(agenda.toString());
			sb.append(" con el Id: ");
			sb.append(personaAmigoId);
			HelpMeLog.log(sb.toString());
			return null;
		}
	}
}
