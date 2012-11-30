package com.ttf.Agenda.client.MainView;

import java.util.TreeSet;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.ttf.Agenda.client.Principal;
import com.ttf.Agenda.shared.Agenda;

public class DialogoNuevaAgenda extends DialogBox implements ChangeHandler,
		ClickHandler {

	MainPanel mainPanel;

	VerticalPanel v;
	HorizontalPanel h;

	TextBox nuevaAgenda;
	Button crear;
	Button cancelar;

	public DialogoNuevaAgenda(MainPanel mainPanel) {

		this.mainPanel = mainPanel;
		v = new VerticalPanel();
		v.setSpacing(15);
		v.add(new HTML("<h1>Nueva Ageda</h1>"));
		nuevaAgenda = new TextBox();
		nuevaAgenda.setWidth("200px");
		nuevaAgenda.addChangeHandler(this);
		v.add(nuevaAgenda);

		h = new HorizontalPanel();
		h.setWidth("200px");
		h.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		cancelar = new Button("Cancelar");
		cancelar.addClickHandler(this);
		h.add(cancelar);
		v.add(h);

		crear = new Button("OK");
		crear.addClickHandler(this);
		h.add(crear);
		v.add(h);

		setWidget(v);

		setAutoHideEnabled(false);
		setGlassEnabled(true);
		center();
	}

	@Override
	public void onChange(ChangeEvent event) {
		crear.setEnabled(nuevaAgenda.getText().length() > 0);
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource().equals(cancelar)) {
			hide();
		} else if (event.getSource().equals(crear)) {
			Agenda agenda = new Agenda();
			agenda.setNombreDeAgenda(nuevaAgenda.getText());
			Principal.service.saveNewAgenda(Principal.getUsuario()
					.getUsuarioId(), agenda, new NuevaAgendaManager());
			hide();
		}
	}

	class NuevaAgendaManager implements AsyncCallback<TreeSet<Agenda>> {
		@Override
		public void onSuccess(TreeSet<Agenda> result) {
			mainPanel.agenda = result;
			mainPanel.panelAgendas.resetAgendas();
		}

		@Override
		public void onFailure(Throwable caught) {
			caught.printStackTrace();
		}
	}
}
