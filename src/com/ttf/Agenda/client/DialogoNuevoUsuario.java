package com.ttf.Agenda.client;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.ttf.Agenda.client.MainView.MainPanel;
import com.ttf.Agenda.shared.Usuario;

public class DialogoNuevoUsuario extends DialogBox implements ChangeHandler,
		ClickHandler {

	private VerticalPanel v;

	private TextBox campoNombreDeUsuario;
	private PasswordTextBox campoPassword;
	private PasswordTextBox campoPasswordConfirmacion;

	private Button botonCancelar;
	private Button botonGuardar;

	public DialogoNuevoUsuario() {
		v = new VerticalPanel();
		v.setPixelSize(400, 400);
		v.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		v.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		v.setSpacing(15);

		v.add(new HTML("<h1>Nuevo Usuario</h1>"));

		HorizontalPanel h = new HorizontalPanel();
		h.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		h.setWidth("310px");
		h.add(new HTML("Nombre de Usuario: "));
		campoNombreDeUsuario = new TextBox();
		campoNombreDeUsuario.setWidth("180px");
		campoNombreDeUsuario.addChangeHandler(this);
		h.add(campoNombreDeUsuario);
		v.add(h);

		h = new HorizontalPanel();
		h.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		h.setWidth("310px");
		h.add(new HTML("Password: "));
		campoPassword = new PasswordTextBox();
		campoPassword.setWidth("180px");
		campoPassword.addChangeHandler(this);
		h.add(campoPassword);
		v.add(h);

		h = new HorizontalPanel();
		h.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		h.setWidth("310px");
		h.add(new HTML("Cornfirma Password: "));
		campoPasswordConfirmacion = new PasswordTextBox();
		campoPasswordConfirmacion.setWidth("180px");
		campoPasswordConfirmacion.addChangeHandler(this);
		h.add(campoPasswordConfirmacion);
		v.add(h);

		h = new HorizontalPanel();
		h.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		h.setWidth("310px");
		botonCancelar = new Button("Cancelar");
		botonCancelar.addClickHandler(this);
		h.add(botonCancelar);
		botonGuardar = new Button("Guardar");
		botonGuardar.addClickHandler(this);
		botonGuardar.setEnabled(false);
		h.add(botonGuardar);
		v.add(h);

		setWidget(v);
		setAnimationEnabled(true);
		setGlassEnabled(true);
		center();
	}

	@Override
	public void onChange(ChangeEvent event) {
		botonGuardar.setEnabled(campoNombreDeUsuario.getText().length() > 0
				&& campoPassword.getText().length() > 0
				&& campoPasswordConfirmacion.getText().length() > 0);
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource().equals(botonCancelar)) {
			DialogoLogin login = new DialogoLogin();
			hide();
			login.show();
		} else if (event.getSource().equals(botonGuardar)) {
			if (campoPassword.getText().equals(
					campoPasswordConfirmacion.getText())) {
				Usuario nuevo = new Usuario();
				nuevo.setNombreDeUsuario(campoNombreDeUsuario.getText());
				nuevo.setContrasenaDeUsuario(campoPassword.getText());
				Principal.service.saveNewUsuario(nuevo,
						new AsyncCallback<Usuario>() {

							@Override
							public void onSuccess(Usuario result) {
								if (result != null) {
									MainPanel main = new MainPanel(result);
									RootPanel.get().add(main);
									hide();
								}else {
									Window.alert("El nombre de usuario ya existe u ocurrio un error.");
								}
							}

							@Override
							public void onFailure(Throwable caught) {
								caught.printStackTrace();
							}
						});

			} else {
				Window.alert("El password y la confirmación no coinciden.");
				campoPassword.setText("");
				campoPasswordConfirmacion.setText("");
				botonGuardar.setEnabled(false);
			}
		}
	}
}
