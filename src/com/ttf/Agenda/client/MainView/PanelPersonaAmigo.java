package com.ttf.Agenda.client.MainView;

import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.ttf.Agenda.shared.PersonaAmigo;

public class PanelPersonaAmigo extends DecoratorPanel {
	MainPanel mainPanel;
	VerticalPanel v;
	PersonaAmigo persona;

	public PanelPersonaAmigo(MainPanel mainPanel, PersonaAmigo persona) {
		this.mainPanel = mainPanel;
		this.persona = persona;

		v = new VerticalPanel();
		v.setPixelSize(600, 640);

	}

}
