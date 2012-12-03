package com.ttf.Agenda.client.MainView.PanelPersona;

import com.google.gwt.user.client.ui.HTML;
import com.ttf.Agenda.client.MainView.MainPanel;
import com.ttf.Agenda.shared.PersonaFamilia;
import com.ttf.Agenda.shared.RelacionKind;

public class PanelPersonaFamilia extends PanelPersona implements RelacionKind {

	PersonaFamilia personaFamilia;

	public PanelPersonaFamilia(MainPanel mainPanel,
			PersonaFamilia personaFamilia) {
		super(mainPanel, personaFamilia);
		this.personaFamilia = personaFamilia;
		StringBuilder sb = new StringBuilder();
		sb.append("<H2>Relación<H2>");

		switch (personaFamilia.getRelacion()) {
		case HIJO:
			sb.append("<p>Hijo<p>");
			break;

		case PADRE:
			sb.append("<p>Padre<p>");
			break;

		case HERMANO:
			sb.append("<p>Hermano<p>");
			break;

		case CONYUGUE:
			sb.append("<p>Conyugue<p>");
			break;

		case PRIMO:
			sb.append("<p>Primo<p>");
			break;

		case TIO:
			sb.append("<p>Tío<p>");
			break;

		case CUNADO:
			sb.append("<p>Cuñado<p>");
			break;

		case ABUELO:
			sb.append("<p>Abuelo<p>");
			break;
		}
		v.add(new HTML(sb.toString()));
	}

}
