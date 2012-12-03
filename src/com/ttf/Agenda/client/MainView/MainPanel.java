package com.ttf.Agenda.client.MainView;

import java.util.TreeSet;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.ttf.Agenda.client.Principal;
import com.ttf.Agenda.client.MainView.PanelPersona.PanelPersona;
import com.ttf.Agenda.client.MainView.PanelPersona.PanelPersonaAmigo;
import com.ttf.Agenda.client.MainView.PanelPersona.PanelPersonaEscuela;
import com.ttf.Agenda.client.MainView.PanelPersona.PanelPersonaFamilia;
import com.ttf.Agenda.shared.Agenda;
import com.ttf.Agenda.shared.Persona;
import com.ttf.Agenda.shared.PersonaAmigo;
import com.ttf.Agenda.shared.PersonaEscuela;
import com.ttf.Agenda.shared.PersonaFamilia;
import com.ttf.Agenda.shared.Usuario;
import com.ttf.Agenda.shared.wrapper.LoginWrapper;

public class MainPanel extends HorizontalPanel {

	Usuario usuario;

	TreeSet<Agenda> agenda;
	Agenda agendaSeleccionada;

	TreeSet<PersonaAmigo> personasAmigo;
	TreeSet<PersonaEscuela> personasEscuela;
	TreeSet<PersonaFamilia> personasFamilia;

	Persona personaSeleccionada;

	PanelAgendas panelAgendas;
	PanelPersonas panelPersonas;
	PanelPersona panelPersona;

	int selectedAgendaIndex = -1;

	public MainPanel(LoginWrapper loginResult) {
		setSpacing(20);

		agenda = loginResult.getAgendas();
		if (agenda.size() > 0) {
			agendaSeleccionada = loginResult.getAgendas().first();
		}
		personasFamilia = loginResult.getPersonasFamilia();
		personasAmigo = loginResult.getPersonasAmigo();
		personasEscuela = loginResult.getPersonasEscuela();
		personaSeleccionada = loginResult.getPersona();

		usuario = loginResult.getUsuario();
		Principal.setUsuario(usuario);

		panelAgendas = new PanelAgendas(this);
		add(panelAgendas);

		panelPersonas = new PanelPersonas(this);
		add(panelPersonas);

		if (personaSeleccionada != null) {
			if (personaSeleccionada instanceof PersonaEscuela) {
				PanelPersonaEscuela ppe = new PanelPersonaEscuela(this,
						(PersonaEscuela) personaSeleccionada);
				panelPersona = ppe;
				add(ppe);
			} else if (personaSeleccionada instanceof PersonaAmigo) {
				PanelPersonaAmigo ppa = new PanelPersonaAmigo(this,
						(PersonaAmigo) personaSeleccionada);
				panelPersona = ppa;
				add(ppa);
			} else if (personaSeleccionada instanceof PersonaFamilia) {
				PanelPersonaFamilia ppf = new PanelPersonaFamilia(this,
						(PersonaFamilia) personaSeleccionada);
				panelPersona = ppf;
				add(ppf);
			}

		}
	}

	public MainPanel(Usuario usuario) {
		this.usuario = usuario;
		Principal.setUsuario(usuario);
		panelAgendas = new PanelAgendas(this);
		add(panelAgendas);
	}

	public void reDraw() {
		if (panelAgendas != null) {
			remove(panelAgendas);
			selectedAgendaIndex = panelAgendas.listaAgendas.getSelectedIndex();
		}
		if (panelPersonas != null) {
			remove(panelPersonas);
		}

		if (panelPersona != null) {
			remove(panelPersona);
		}

		panelAgendas = new PanelAgendas(this);
		panelAgendas.setSelectedIndex(selectedAgendaIndex);
		add(panelAgendas);

		panelPersonas = new PanelPersonas(this);
		add(panelPersonas);

		if (personaSeleccionada != null) {
			if (personaSeleccionada instanceof PersonaEscuela) {
				PanelPersonaEscuela ppe = new PanelPersonaEscuela(this,
						(PersonaEscuela) personaSeleccionada);
				add(ppe);
			} else if (personaSeleccionada instanceof PersonaAmigo) {
				PanelPersonaAmigo ppa = new PanelPersonaAmigo(this,
						(PersonaAmigo) personaSeleccionada);
				add(ppa);
			} else if (personaSeleccionada instanceof PersonaFamilia) {
				PanelPersonaFamilia ppf = new PanelPersonaFamilia(this,
						(PersonaFamilia) personaSeleccionada);
			}

		}
	}

}
