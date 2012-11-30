package com.ttf.Agenda.client.MainView;

import java.util.Iterator;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
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

		listas.add(new HTML("<h2>Familia</h2>"));
		listaFamiliares = new ListBox(true);
		listaFamiliares.addChangeHandler(this);
		listaFamiliares.setWidth("300px");
		listas.add(listaFamiliares);

		listas.add(new HTML("<h2>Amigos</h2>"));
		listaAmigos = new ListBox(true);
		listaAmigos.addChangeHandler(this);
		listaAmigos.setWidth("300px");
		listas.add(listaAmigos);

		listas.add(new HTML("<h2>Compañeros de Escuela</h2>"));
		listaCompaneros = new ListBox(true);
		listaCompaneros.addChangeHandler(this);
		listaCompaneros.setWidth("300px");
		listas.add(listaCompaneros);

		scrollPersonas = new ScrollPanel(listas);
		scrollPersonas.setPixelSize(320, 500);
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
		System.out.println("Cambio de selección");
	}

}