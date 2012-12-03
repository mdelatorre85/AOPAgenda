package com.ttf.Agenda.client.MainView.PanelPersona;

import com.google.gwt.user.client.ui.HTML;
import com.ttf.Agenda.client.MainView.MainPanel;
import com.ttf.Agenda.shared.Materia;
import com.ttf.Agenda.shared.PersonaEscuela;

public class PanelPersonaEscuela extends PanelPersona {

	PersonaEscuela personaEscuela;

	public PanelPersonaEscuela(MainPanel mainPanel, PersonaEscuela personaEscuela) {
		super(mainPanel, personaEscuela);
		this.personaEscuela = personaEscuela;
		if (personaEscuela instanceof PersonaEscuela) {
			if (personaEscuela.getMaterias() != null
					&& personaEscuela.getMaterias().size() > 0) {
				StringBuilder sb = new StringBuilder();
				sb.append("<H2>Materias</H2>");
				for (Materia materia : personaEscuela.getMaterias()) {
					sb.append("<p><b>");
					sb.append(materia.getNombreDeLaMateria());
					sb.append("</b> ");

					String semestre = String.valueOf(materia.getSemestre());
					Integer i = Integer.parseInt(semestre.substring(semestre
							.length() - 1));
					switch (i) {
					case 0:
						sb.append("Enero - Junio ");
						sb.append(semestre.substring(0, semestre.length() - 1));
						break;
					case 1:
						sb.append("Verano ");
						sb.append(semestre.substring(0, semestre.length() - 1));
						break;
					case 2:
						sb.append("Agosto - Noviembre ");
						sb.append(semestre.substring(0, semestre.length() - 1));
						break;
					}

					sb.append("</p>");

				}
				v.add(new HTML(sb.toString()));
			}
		} else {
			throw new IllegalArgumentException(
					"parameter persona should be of the class PersonaEscuela");
		}

	}

}
