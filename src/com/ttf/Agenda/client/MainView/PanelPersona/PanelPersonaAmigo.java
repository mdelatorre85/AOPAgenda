package com.ttf.Agenda.client.MainView.PanelPersona;

import com.google.gwt.user.client.ui.HTML;
import com.ttf.Agenda.client.MainView.MainPanel;
import com.ttf.Agenda.shared.PersonaAmigo;

public class PanelPersonaAmigo extends PanelPersona {

	PersonaAmigo personaAmigo;

	public PanelPersonaAmigo(MainPanel mainPanel, PersonaAmigo personaAmigo) {
		super(mainPanel, personaAmigo);
		this.personaAmigo = personaAmigo;
		if (personaAmigo instanceof PersonaAmigo) {
			StringBuilder sb = new StringBuilder();
			if (personaAmigo.getFacebook() != null
					&& personaAmigo.getFacebook().length() > 0) {
				sb.append("<H2>Facebook</H2>");
				sb.append("<p><a href=\"");
				sb.append(personaAmigo.getFacebook());
				sb.append("\">");
				sb.append(personaAmigo.getFacebook());
				sb.append("</a></p>");
			}
			if (personaAmigo.getTwitter() != null
					&& personaAmigo.getTwitter().length() > 0) {
				sb.append("<H2>Twitter</H2>");
				sb.append("<p><a href=\"");
				sb.append(personaAmigo.getTwitter());
				sb.append("\">");
				sb.append(personaAmigo.getTwitter());
				sb.append("</a></p>");
			}
			v.add(new HTML(sb.toString()));
		} else {
			throw new IllegalArgumentException(
					"parameter persona should be of the class PersonaAmigo");
		}

	}

}
