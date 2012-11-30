package com.ttf.Agenda.client.MainView;

import java.util.Iterator;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.ttf.Agenda.client.Principal;
import com.ttf.Agenda.shared.Agenda;
import com.ttf.Agenda.shared.wrapper.AgendaWrapper;

public class PanelAgendas extends DecoratorPanel implements ClickHandler,
		ChangeHandler {

	Button botonNuevaAgenda;
	ScrollPanel scrollAgendas;
	ListBox listaAgendas;
	VerticalPanel v;
	MainPanel mainPanel;

	public PanelAgendas(MainPanel mainPanel) {

		this.mainPanel = mainPanel;

		v = new VerticalPanel();
		v.setPixelSize(200, 500);
		v.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		v.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		v.setSpacing(5);

		botonNuevaAgenda = new Button("+ Nueva Agenda");
		botonNuevaAgenda.setWidth("180px");
		botonNuevaAgenda.addClickHandler(this);
		v.add(botonNuevaAgenda);

		listaAgendas = new ListBox(true);
		listaAgendas.addChangeHandler(this);
		listaAgendas.setPixelSize(160, 600);

		scrollAgendas = new ScrollPanel(listaAgendas);
		scrollAgendas.setPixelSize(180, 600);
		v.add(scrollAgendas);

		setWidget(v);

		if (mainPanel.agenda != null)
			if (!mainPanel.agenda.isEmpty()) {
				Iterator<Agenda> i = mainPanel.agenda.iterator();
				while (i.hasNext()) {
					listaAgendas.addItem(i.next().getNombreDeAgenda());
				}
				listaAgendas.setSelectedIndex(0);
				Principal.setAgenda(mainPanel.agenda.first());
			}
	}

	@Override
	public void onClick(ClickEvent event) {
		new DialogoNuevaAgenda(mainPanel).show();
	}

	@Override
	public void onChange(ChangeEvent event) {
		int indice = listaAgendas.getSelectedIndex(), k = 0;
		Iterator<Agenda> i = mainPanel.agenda.iterator();
		while (i.hasNext()) {
			mainPanel.agendaSeleccionada = i.next();
			if (k == indice) {
				break;
			} else {
				k++;
			}
		}
		Principal.service.getPersonas(mainPanel.usuario.getUsuarioId(),
				mainPanel.agendaSeleccionada.getId(),
				new AsyncCallback<AgendaWrapper>() {

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(AgendaWrapper result) {
						mainPanel.personaSeleccionada = result.getPersona();
						mainPanel.personasAmigo = result.getPersonasAmigo();
						mainPanel.personasEscuela = result.getPersonasEscuela();
						mainPanel.personasFamilia = result.getPersonasFamilia();

						mainPanel.reDraw();
					}
				});
	}

	public void resetAgendas() {
		while (listaAgendas.getItemCount() > 0) {
			listaAgendas.removeItem(0);
		}
		if (mainPanel.agenda != null)
			if (!mainPanel.agenda.isEmpty()) {
				Iterator<Agenda> i = mainPanel.agenda.iterator();
				while (i.hasNext()) {
					listaAgendas.addItem(i.next().getNombreDeAgenda());
				}
			}

		// TODO limpiar el resto de los paneles para reflejar que no hay
		// ninguna agenda seleccionada
	}
}
