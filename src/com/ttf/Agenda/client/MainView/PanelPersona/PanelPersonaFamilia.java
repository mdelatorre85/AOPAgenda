package com.ttf.Agenda.client.MainView.PanelPersona;

import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.ttf.Agenda.client.MainView.MainPanel;
import com.ttf.Agenda.shared.PersonaFamilia;

public class PanelPersonaFamilia extends DecoratorPanel {

	MainPanel mainPanel;
	VerticalPanel v;
	PersonaFamilia persona;

	public PanelPersonaFamilia(MainPanel mainPanel, PersonaFamilia persona) {
		this.mainPanel = mainPanel;
		this.persona = persona;

		v = new VerticalPanel();
		v.setPixelSize(600, 640);

	}

}
