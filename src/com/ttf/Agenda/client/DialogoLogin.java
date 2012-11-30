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
import com.ttf.Agenda.shared.wrapper.LoginWrapper;

public class DialogoLogin extends DialogBox implements ChangeHandler,
		ClickHandler {

	private VerticalPanel v;
	private HorizontalPanel h;

	private TextBox campoNombreDeUsuario;
	private PasswordTextBox campoPassword;

	private Button botonNuevoUsuaro;
	private Button botonLogin;

	public DialogoLogin() {

		v = new VerticalPanel();
		v.setPixelSize(400, 400);
		v.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		v.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		v.setSpacing(15);

		v.add(new HTML("<h1>Login</h1>"));

		v.add(new HTML("<p>Nombre de Usuario:</p>"));
		campoNombreDeUsuario = new TextBox();
		campoNombreDeUsuario.setWidth("310px");
		campoNombreDeUsuario.addChangeHandler(this);
		v.add(campoNombreDeUsuario);

		v.add(new HTML("<p>Password:</p>"));
		campoPassword = new PasswordTextBox();
		campoPassword.setWidth("310px");
		campoPassword.addChangeHandler(this);
		v.add(campoPassword);

		h = new HorizontalPanel();
		h.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		h.setWidth("310px");
		v.add(h);

		botonNuevoUsuaro = new Button("Nuevo Usuario");
		botonNuevoUsuaro.setEnabled(true);
		botonNuevoUsuaro.addClickHandler(this);
		h.add(botonNuevoUsuaro);

		botonLogin = new Button("Login");
		botonLogin.setEnabled(false);
		botonLogin.addClickHandler(this);
		h.add(botonLogin);

		setWidget(v);
		setGlassEnabled(true);
		center();
	}

	@Override
	public void onChange(ChangeEvent event) {
		botonLogin
				.setEnabled((campoNombreDeUsuario.getText().length() > 0 && campoPassword
						.getText().length() > 0));
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource().equals(botonLogin)) {
			botonLogin.setEnabled(false);
			Principal.service.doLogin(campoNombreDeUsuario.getText(),
					campoPassword.getText(), new AsyncCallback<LoginWrapper>() {

						@Override
						public void onSuccess(LoginWrapper result) {
							if (result != null) {
								MainPanel main = new MainPanel(result);
								RootPanel.get().add(main);
								hide();
							} else {
								Window.alert("Contraseña incorrecta o Usuario no existe o error en el servidor.");
							}
						}

						@Override
						public void onFailure(Throwable caught) {
							caught.printStackTrace();
						}
					});
		} else if (event.getSource().equals(botonNuevoUsuaro)) {
			DialogoNuevoUsuario nuevo = new DialogoNuevoUsuario();
			hide();
			nuevo.show();
		}

	}
}
