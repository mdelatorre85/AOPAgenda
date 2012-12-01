package com.ttf.Agenda.client.MainView;

import java.util.Iterator;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.ttf.Agenda.client.Principal;
import com.ttf.Agenda.shared.PersonaAmigo;
import com.ttf.Agenda.shared.PersonaEscuela;
import com.ttf.Agenda.shared.PersonaFamilia;

public class PanelPersonas extends DecoratorPanel implements ClickHandler,
		ChangeHandler {

	MainPanel mainPanel;

	ScrollPanel scrollPersonas;
	VerticalPanel v, listas;

	ListBox listaFamiliares, listaAmigos, listaCompaneros;
	Button botonNuevaContacto;

	public PanelPersonas(MainPanel mainPanel) {
		this.mainPanel = mainPanel;

		v = new VerticalPanel();
		v.setPixelSize(340, 640);
		v.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		v.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		v.setSpacing(5);

		botonNuevaContacto = new Button("+ Nuevo Contacto");
		botonNuevaContacto.setWidth("300px");
		botonNuevaContacto.addClickHandler(this);
		v.add(botonNuevaContacto);

		listas = new VerticalPanel();
		listas.setHeight("500");
		listas.add(new HTML("<h2>Familia</h2>"));
		listaFamiliares = new ListBox(true);
		listaFamiliares.addChangeHandler(this);
		listaFamiliares.setHeight("130px");
		listaFamiliares.setWidth("300px");
		listas.add(listaFamiliares);

		listas.add(new HTML("<h2>Amigos</h2>"));
		listaAmigos = new ListBox(true);
		listaAmigos.addChangeHandler(this);
		listaAmigos.setHeight("130px");
		listaAmigos.setWidth("300px");
		listas.add(listaAmigos);

		listas.add(new HTML("<h2>Compañeros de Escuela</h2>"));
		listaCompaneros = new ListBox(true);
		listaCompaneros.addChangeHandler(this);
		listaCompaneros.setHeight("130px");
		listaCompaneros.setWidth("300px");
		listas.add(listaCompaneros);

		scrollPersonas = new ScrollPanel(listas);
		scrollPersonas.setPixelSize(320, 570);
		v.add(scrollPersonas);

		setWidget(v);

		if (mainPanel.personasFamilia != null)
			if (!mainPanel.personasFamilia.isEmpty()) {
				Iterator<PersonaFamilia> i = mainPanel.personasFamilia
						.iterator();
				while (i.hasNext()) {
					listaFamiliares.addItem(i.next().getNombreCompleto());
				}
				listaFamiliares.setSelectedIndex(0);
			}
		if (mainPanel.personasAmigo != null)
			if (!mainPanel.personasAmigo.isEmpty()) {
				Iterator<PersonaAmigo> i = mainPanel.personasAmigo.iterator();
				while (i.hasNext()) {
					listaAmigos.addItem(i.next().getNombreCompleto());
				}
				if (mainPanel.personasFamilia.isEmpty()) {
					listaAmigos.setSelectedIndex(0);
				}
			}
		if (mainPanel.personasEscuela != null)
			if (!mainPanel.personasEscuela.isEmpty()) {
				Iterator<PersonaEscuela> i = mainPanel.personasEscuela
						.iterator();
				while (i.hasNext()) {
					listaCompaneros.addItem(i.next().getNombreCompleto());
				}
				if (mainPanel.personasFamilia.isEmpty()
						&& mainPanel.personasFamilia.isEmpty()) {
					listaAmigos.setSelectedIndex(0);
				}
			}

	}

	@Override
	public void onClick(ClickEvent event) {
		new DialogoNuevoContacto(mainPanel).show();
	}

	@Override
	public void onChange(ChangeEvent event) {

		if (event.getSource().equals(listaAmigos)) {
			listaCompaneros.setSelectedIndex(-1);
			listaFamiliares.setSelectedIndex(-1);

			int selectedIndex = listaAmigos.getSelectedIndex();
			int i = 0;
			for (PersonaAmigo amigo : mainPanel.personasAmigo) {
				if (selectedIndex == i) {
					mainPanel.personaSeleccionada = amigo;
					Principal.service.getPersonaAmigo(Principal.getUsuario()
							.getUsuarioId(), mainPanel.agendaSeleccionada
							.getId(), amigo.getId(),
							new AsyncCallback<PersonaAmigo>() {

								@Override
								public void onSuccess(PersonaAmigo result) {
									// TODO pintar al mono que ya viene de
									// regreso
								}

								@Override
								public void onFailure(Throwable caught) {
									listaAmigos.setSelectedIndex(-1);

								}
							});
					break;
				}
				i++;
			}

		} else if (event.getSource().equals(listaCompaneros)) {
			listaAmigos.setSelectedIndex(-1);
			listaFamiliares.setSelectedIndex(-1);

			int selectedIndex = listaCompaneros.getSelectedIndex();
			int i = 0;
			for (PersonaEscuela amigo : mainPanel.personasEscuela) {
				if (selectedIndex == i) {
					mainPanel.personaSeleccionada = amigo;
					Principal.service.getPersonaEscuela(Principal.getUsuario()
							.getUsuarioId(), mainPanel.agendaSeleccionada
							.getId(), amigo.getId(),
							new AsyncCallback<PersonaEscuela>() {

								@Override
								public void onSuccess(PersonaEscuela result) {
									// TODO pintar al mono que ya viene de
									// regreso
								}

								@Override
								public void onFailure(Throwable caught) {
									listaCompaneros.setSelectedIndex(-1);

								}
							});
					break;
				}
				i++;
			}

		} else if (event.getSource().equals(listaFamiliares)) {
			listaCompaneros.setSelectedIndex(-1);
			listaAmigos.setSelectedIndex(-1);

			int selectedIndex = listaFamiliares.getSelectedIndex();
			int i = 0;
			for (PersonaFamilia amigo : mainPanel.personasFamilia) {
				if (selectedIndex == i) {
					mainPanel.personaSeleccionada = amigo;
					Principal.service.getPersonaFamilia(Principal.getUsuario()
							.getUsuarioId(), mainPanel.agendaSeleccionada
							.getId(), amigo.getId(),
							new AsyncCallback<PersonaFamilia>() {

								@Override
								public void onSuccess(PersonaFamilia result) {
									// TODO pintar al mono que ya viene de
									// regreso
								}

								@Override
								public void onFailure(Throwable caught) {
									listaFamiliares.setSelectedIndex(-1);

								}
							});
					break;
				}
				i++;
			}
		}

	}
}