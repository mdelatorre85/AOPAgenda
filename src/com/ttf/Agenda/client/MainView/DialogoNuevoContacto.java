package com.ttf.Agenda.client.MainView;

import java.util.Date;
import java.util.TreeSet;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.ttf.Agenda.client.Principal;
import com.ttf.Agenda.shared.ComunicacionesKind;
import com.ttf.Agenda.shared.CorreoElectronico;
import com.ttf.Agenda.shared.Direccion;
import com.ttf.Agenda.shared.Materia;
import com.ttf.Agenda.shared.Persona;
import com.ttf.Agenda.shared.PersonaAmigo;
import com.ttf.Agenda.shared.PersonaEscuela;
import com.ttf.Agenda.shared.PersonaFamilia;
import com.ttf.Agenda.shared.Telefono;

public class DialogoNuevoContacto extends DialogBox implements ChangeHandler,
		ClickHandler {

	MainPanel mainPanel;

	VerticalPanel v;
	TextBox nombre;
	TextBox apellidoPaterno;
	TextBox apellidoMaterno;

	TextBox calle;
	TextBox numero;
	TextBox colonia;
	TextBox codigoPostal;
	TextBox municipio;
	TextBox estado;
	TextBox pais;

	TextBox telefonoCelular;
	TextBox telefonoOficina;
	TextBox telefonoCasa;

	TextBox mailPersonal;
	TextBox mailTrabajo;

	DatePicker cumpleanos;

	PanelFamilia panelFamilia;
	PanelAmigos panelAmigos;
	PanelEscuela panelEscuela;

	RadioButton botonFamilia;
	RadioButton botonAmigos;
	RadioButton botonEscuela;

	boolean isReady;

	public DialogoNuevoContacto(MainPanel mainPanel) {

		this.mainPanel = mainPanel;

		v = new VerticalPanel();
		v.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		v.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		v.setSpacing(15);
		v.setPixelSize(600, 600);
		setWidget(v);

		HorizontalPanel hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		hUtil.setWidth("570px");
		hUtil.add(new HTML("<h1>Nuevo Contacto</h1>"));
		v.add(hUtil);

		hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		hUtil.setWidth("570px");
		hUtil.add(new HTML("Nombre:"));
		nombre = new TextBox();
		nombre.setWidth("200px");
		nombre.addChangeHandler(this);
		hUtil.add(nombre);
		v.add(hUtil);

		hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		hUtil.setWidth("570px");
		hUtil.add(new HTML("Apellido Paterno: "));
		apellidoPaterno = new TextBox();
		apellidoPaterno.setWidth("200px");
		hUtil.add(apellidoPaterno);
		v.add(hUtil);

		hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		hUtil.setWidth("570px");
		hUtil.add(new HTML("Apellido Materno: "));
		apellidoMaterno = new TextBox();
		apellidoMaterno.setWidth("200px");
		hUtil.add(apellidoMaterno);
		v.add(hUtil);

		VerticalPanel vUtil = new VerticalPanel();
		vUtil.setWidth("570px");
		hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		hUtil.setWidth("570px");
		hUtil.add(new HTML("Calle: "));
		calle = new TextBox();
		calle.setWidth("200px");
		hUtil.add(calle);
		vUtil.add(hUtil);
		hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		hUtil.setWidth("570px");
		hUtil.add(new HTML("Número: "));
		numero = new TextBox();
		numero.setWidth("200px");
		hUtil.add(numero);
		vUtil.add(hUtil);
		hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		hUtil.setWidth("570px");
		hUtil.add(new HTML("Colonia: "));
		colonia = new TextBox();
		colonia.setWidth("200px");
		hUtil.add(colonia);
		vUtil.add(hUtil);
		hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		hUtil.setWidth("570px");
		hUtil.add(new HTML("Código Postal: "));
		codigoPostal = new TextBox();
		codigoPostal.setWidth("200px");
		hUtil.add(codigoPostal);
		vUtil.add(hUtil);
		hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		hUtil.setWidth("570px");
		hUtil.add(new HTML("Delegación o Municipio: "));
		municipio = new TextBox();
		municipio.setWidth("200px");
		hUtil.add(municipio);
		vUtil.add(hUtil);
		hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		hUtil.setWidth("570px");
		hUtil.add(new HTML("Estado: "));
		estado = new TextBox();
		estado.setWidth("200px");
		hUtil.add(estado);
		vUtil.add(hUtil);
		hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		hUtil.setWidth("570px");
		hUtil.add(new HTML("Pais: "));
		pais = new TextBox();
		pais.setWidth("200px");
		hUtil.add(pais);
		vUtil.add(hUtil);
		DisclosurePanel discluosurePanelUtil = new DisclosurePanel("Dirección");
		discluosurePanelUtil.setWidth("570px");
		discluosurePanelUtil.add(vUtil);
		v.add(discluosurePanelUtil);

		vUtil = new VerticalPanel();
		vUtil.setWidth("570px");
		hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		hUtil.setWidth("570px");
		hUtil.add(new HTML("Telefono Celular: "));
		telefonoCelular = new TextBox();
		telefonoCelular.setWidth("200px");
		hUtil.add(telefonoCelular);
		vUtil.add(hUtil);
		hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		hUtil.setWidth("570px");
		hUtil.add(new HTML("Telefono Casa: "));
		telefonoCasa = new TextBox();
		telefonoCasa.setWidth("200px");
		hUtil.add(telefonoCasa);
		vUtil.add(hUtil);
		hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		hUtil.setWidth("570px");
		hUtil.add(new HTML("Telefono Oficina: "));
		telefonoOficina = new TextBox();
		telefonoOficina.setWidth("200px");
		hUtil.add(telefonoOficina);
		vUtil.add(hUtil);
		discluosurePanelUtil = new DisclosurePanel("Teléfonos");
		discluosurePanelUtil.setWidth("570px");
		discluosurePanelUtil.add(vUtil);
		v.add(discluosurePanelUtil);

		vUtil = new VerticalPanel();
		vUtil.setWidth("570px");
		hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		hUtil.setWidth("570px");
		hUtil.add(new HTML("E-Mail Personal: "));
		mailPersonal = new TextBox();
		mailPersonal.setWidth("200px");
		hUtil.add(mailPersonal);
		vUtil.add(hUtil);
		hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		hUtil.setWidth("570px");
		hUtil.add(new HTML("E-Mail Trabajo: "));
		mailTrabajo = new TextBox();
		mailTrabajo.setWidth("200px");
		hUtil.add(mailTrabajo);
		vUtil.add(hUtil);
		discluosurePanelUtil = new DisclosurePanel("E-Mail");
		discluosurePanelUtil.setWidth("570px");
		discluosurePanelUtil.add(vUtil);
		v.add(discluosurePanelUtil);

		vUtil = new VerticalPanel();
		vUtil.setWidth("570px");
		cumpleanos = new DatePicker();
		cumpleanos.setValue(new Date(), true);
		vUtil.add(cumpleanos);
		discluosurePanelUtil = new DisclosurePanel("Cumpleaños");
		discluosurePanelUtil.setWidth("570px");
		discluosurePanelUtil.add(vUtil);
		v.add(discluosurePanelUtil);

		panelFamilia = new PanelFamilia();
		panelAmigos = new PanelAmigos();
		panelEscuela = new PanelEscuela();

		hUtil = new HorizontalPanel();
		hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		hUtil.setWidth("570px");

		botonFamilia = new RadioButton("a", "Familia");
		botonFamilia.setValue(true);
		botonFamilia.addClickHandler(this);
		hUtil.add(botonFamilia);

		botonAmigos = new RadioButton("a", "Amigo");
		botonAmigos.addClickHandler(this);
		hUtil.add(botonAmigos);

		botonEscuela = new RadioButton("a", "Compañero");
		botonEscuela.addClickHandler(this);
		hUtil.add(botonEscuela);
		v.add(hUtil);

		v.add(panelFamilia);

		setGlassEnabled(true);
		center();
	}

	class PanelFamilia extends DecoratorPanel implements ClickHandler,
			ChangeHandler {

		Button botonCancelar;
		Button botonGuardar;
		VerticalPanel v;
		ListBox relaciones;

		public PanelFamilia() {
			v = new VerticalPanel();
			v.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
			v.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
			v.setWidth("550px");
			v.setSpacing(15);

			relaciones = new ListBox(false);
			relaciones.addItem("Hijo");
			relaciones.addItem("Padre");
			relaciones.addItem("Hermano");
			relaciones.addItem("Conyugue");
			relaciones.addItem("Primo");
			relaciones.addItem("Tio");
			relaciones.addItem("Cuñado");
			relaciones.addItem("Abuelo");

			v.add(relaciones);

			HorizontalPanel hUtil = new HorizontalPanel();
			hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
			hUtil.setWidth("570px");
			botonCancelar = new Button("Cancelar");
			botonCancelar.addClickHandler(this);
			hUtil.add(botonCancelar);
			botonGuardar = new Button("Guardar");
			botonGuardar.addClickHandler(this);
			hUtil.add(botonGuardar);
			botonGuardar.setEnabled(false);
			v.add(hUtil);
			setWidget(v);
		}

		@Override
		public void onChange(ChangeEvent event) {
			botonGuardar.setEnabled(isReady);
		}

		@Override
		public void onClick(ClickEvent event) {
			if (event.getSource().equals(botonCancelar)) {
				hide();
			} else if (event.getSource().equals(botonGuardar)) {
				botonGuardar.setEnabled(false);
				PersonaFamilia persona = new PersonaFamilia();
				persona.setNombre(nombre.getText());
				persona.setApellidoPaterno(apellidoPaterno.getText());
				persona.setApellidoMaterno(apellidoMaterno.getText());

				Direccion direccion;
				direccion = new Direccion();
				direccion.setCalle(calle.getText());
				direccion.setNumero(numero.getText());
				direccion.setColonia(colonia.getText());
				direccion.setCodigoPostal(codigoPostal.getText());
				direccion.setMunicipio(municipio.getText());
				direccion.setEstado(estado.getText());
				direccion.setPais(pais.getText());
				persona.setDireccionDomicilio(direccion);

				if (telefonoCelular.getText().length() > 0) {
					persona.getTelefonos().add(
							new Telefono(telefonoCelular.getText(),
									ComunicacionesKind.CELULAR));
				}
				if (telefonoCasa.getText().length() > 0) {
					persona.getTelefonos().add(
							new Telefono(telefonoCasa.getText(),
									ComunicacionesKind.CASA));
				}
				if (telefonoOficina.getText().length() > 0) {
					persona.getTelefonos().add(
							new Telefono(telefonoOficina.getText(),
									ComunicacionesKind.OFICINA));
				}

				if (mailPersonal.getText().length() > 0) {
					persona.getCorreos().add(
							new CorreoElectronico(mailPersonal.getText(),
									ComunicacionesKind.CASA));
				}
				if (mailTrabajo.getText().length() > 0) {
					persona.getCorreos().add(
							new CorreoElectronico(mailTrabajo.getText(),
									ComunicacionesKind.OFICINA));
				}
				persona.setFechaDeNacimiento(cumpleanos.getValue().getTime());
				persona.setRelacion(relaciones.getSelectedIndex());

				Principal.service.savePersona(Principal.getUsuario()
						.getUsuarioId(), Principal.getAgenda().getId(),
						persona, new AsyncCallback<Persona>() {

							@Override
							public void onFailure(Throwable caught) {
								caught.printStackTrace();

							}

							@Override
							public void onSuccess(Persona result) {
								if (mainPanel.personasFamilia == null) {
									mainPanel.personasFamilia = new TreeSet<PersonaFamilia>();
								}
								mainPanel.personasFamilia
										.add((PersonaFamilia) result);
								mainPanel.reDraw();
								hide();
							}

						});
			}
		}

	}

	class PanelAmigos extends DecoratorPanel implements ClickHandler,
			ChangeHandler {

		VerticalPanel v;
		TextBox campoFacebook;
		TextBox campoTwitter;

		Button botonCancelar;
		Button botonGuardar;

		public PanelAmigos() {
			v = new VerticalPanel();
			v.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
			v.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
			v.setWidth("550px");
			v.setSpacing(15);
			HorizontalPanel hUtil = new HorizontalPanel();
			hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
			hUtil.setWidth("570px");
			hUtil.add(new HTML("Facebook: "));
			campoFacebook = new TextBox();
			campoFacebook.setWidth("200px");
			campoFacebook.addChangeHandler(this);
			hUtil.add(campoFacebook);
			v.add(hUtil);
			hUtil = new HorizontalPanel();
			hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
			hUtil.setWidth("570px");
			hUtil.add(new HTML("Twitter: "));
			campoTwitter = new TextBox();
			campoTwitter.setWidth("200px");
			campoTwitter.addChangeHandler(this);
			hUtil.add(campoTwitter);
			v.add(hUtil);

			hUtil = new HorizontalPanel();
			hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
			hUtil.setWidth("570px");
			botonCancelar = new Button("Cancelar");
			botonCancelar.addClickHandler(this);
			hUtil.add(botonCancelar);
			botonGuardar = new Button("Guardar");
			botonGuardar.addClickHandler(this);
			hUtil.add(botonGuardar);
			botonGuardar.setEnabled(false);
			v.add(hUtil);

			setWidget(v);
		}

		@Override
		public void onClick(ClickEvent event) {
			if (event.getSource().equals(botonCancelar)) {
				hide();
			} else if (event.getSource().equals(botonGuardar)) {
				botonGuardar.setEnabled(false);
				PersonaAmigo persona = new PersonaAmigo();
				persona.setNombre(nombre.getText());
				persona.setApellidoPaterno(apellidoPaterno.getText());
				persona.setApellidoMaterno(apellidoMaterno.getText());

				Direccion direccion;
				direccion = new Direccion();
				direccion.setCalle(calle.getText());
				direccion.setNumero(numero.getText());
				direccion.setColonia(colonia.getText());
				direccion.setCodigoPostal(codigoPostal.getText());
				direccion.setMunicipio(municipio.getText());
				direccion.setEstado(estado.getText());
				direccion.setPais(pais.getText());
				persona.setDireccionDomicilio(direccion);

				if (telefonoCelular.getText().length() > 0) {
					persona.getTelefonos().add(
							new Telefono(telefonoCelular.getText(),
									ComunicacionesKind.CELULAR));
				}
				if (telefonoCasa.getText().length() > 0) {
					persona.getTelefonos().add(
							new Telefono(telefonoCasa.getText(),
									ComunicacionesKind.CASA));
				}
				if (telefonoOficina.getText().length() > 0) {
					persona.getTelefonos().add(
							new Telefono(telefonoOficina.getText(),
									ComunicacionesKind.OFICINA));
				}

				if (mailPersonal.getText().length() > 0) {
					persona.getCorreos().add(
							new CorreoElectronico(mailPersonal.getText(),
									ComunicacionesKind.CASA));
				}
				if (mailTrabajo.getText().length() > 0) {
					persona.getCorreos().add(
							new CorreoElectronico(mailTrabajo.getText(),
									ComunicacionesKind.OFICINA));
				}
				persona.setFechaDeNacimiento(cumpleanos.getValue().getTime());
				persona.setFacebook(campoFacebook.getText());
				persona.setTwitter(campoTwitter.getText());

				Principal.service.savePersona(Principal.getUsuario()
						.getUsuarioId(), Principal.getAgenda().getId(),
						persona, new AsyncCallback<Persona>() {

							@Override
							public void onFailure(Throwable caught) {
								caught.printStackTrace();

							}

							@Override
							public void onSuccess(Persona result) {
								if (mainPanel.personasFamilia == null) {
									mainPanel.personasFamilia = new TreeSet<PersonaFamilia>();
								}
								mainPanel.personasAmigo
										.add((PersonaAmigo) result);
								mainPanel.reDraw();
								hide();
							}

						});
			}
		}

		@Override
		public void onChange(ChangeEvent event) {
			botonGuardar.setEnabled(isReady
					&& campoFacebook.getText().length() > 0
					&& campoTwitter.getText().length() > 0);
		}
	}

	class PanelEscuela extends DecoratorPanel implements ClickHandler,
			ChangeHandler {
		VerticalPanel v;

		Button botonCancelar;
		Button botonGuardar;

		Button botonAgregar;

		TextBox campoMateria;

		ListBox listaSemestres;
		ListBox listaAños;

		TreeSet<Materia> materias;
		FlexTable tablaMateria;

		public PanelEscuela() {
			materias = new TreeSet<Materia>();

			v = new VerticalPanel();
			v.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
			v.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
			v.setWidth("550px");
			v.setSpacing(15);

			Grid grid = new Grid(2, 3);
			grid.setWidget(0, 0, new HTML("Materia"));
			grid.setWidget(0, 1, new HTML("Año / Semestre"));

			campoMateria = new TextBox();
			campoMateria.setWidth("200px");
			grid.setWidget(1, 0, campoMateria);

			listaSemestres = new ListBox(false);
			listaSemestres.addItem("Enero-Junio");
			listaSemestres.addItem("Verano");
			listaSemestres.addItem("Agosto-Noviembre");

			listaAños = new ListBox(false);
			listaAños.addItem("2000");
			listaAños.addItem("2001");
			listaAños.addItem("2002");
			listaAños.addItem("2003");
			listaAños.addItem("2004");
			listaAños.addItem("2005");
			listaAños.addItem("2006");
			listaAños.addItem("2007");
			listaAños.addItem("2008");
			listaAños.addItem("2009");
			listaAños.addItem("2010");
			listaAños.addItem("2011");
			listaAños.addItem("2012");
			listaAños.addItem("2013");
			listaAños.addItem("2014");
			listaAños.addItem("2015");
			listaAños.setSelectedIndex(12);

			HorizontalPanel hUtil = new HorizontalPanel();
			hUtil.add(listaAños);
			hUtil.add(listaSemestres);
			grid.setWidget(1, 1, hUtil);

			botonAgregar = new Button("Agregar Materia");
			botonAgregar.addClickHandler(this);

			grid.setWidget(1, 2, botonAgregar);
			v.add(grid);

			v.add(new HTML("<h3>Agregadas>/h3>"));
			materias = new TreeSet<Materia>();
			tablaMateria = new FlexTable();
			tablaMateria.setWidth("570px");
			tablaMateria.setCellSpacing(5);
			tablaMateria.setCellPadding(3);

			tablaMateria.setWidget(0, 0, new HTML("<b>Materia</b>"));
			tablaMateria.setWidget(0, 1, new HTML("<b>Año/Semestre</b>"));

			v.add(tablaMateria);

			hUtil = new HorizontalPanel();
			hUtil.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
			hUtil.setWidth("400px");
			botonCancelar = new Button("Cancelar");
			botonCancelar.addClickHandler(this);
			hUtil.add(botonCancelar);
			botonGuardar = new Button("Guardar");
			botonGuardar.addClickHandler(this);
			hUtil.add(botonGuardar);
			botonGuardar.setEnabled(false);
			v.add(hUtil);
			setWidget(v);
		}

		@Override
		public void onClick(ClickEvent event) {
			if (event.getSource().equals(botonCancelar)) {
				hide();
			} else if (event.getSource().equals(botonAgregar)) {
				if (campoMateria.getText().length() > 0) {
					Materia materia = new Materia();
					materia.setNombreDeLaMateria(campoMateria.getText());
					int year = 2000 + listaAños.getSelectedIndex();

					int numRows = tablaMateria.getRowCount();
					numRows++;
					tablaMateria.setWidget(numRows, 0,
							new HTML(materia.getNombreDeLaMateria()));
					StringBuilder sb = new StringBuilder();
					sb.append(year);
					sb.append("/");

					year *= 10;
					year += listaSemestres.getSelectedIndex();
					sb.append(listaSemestres.getSelectedIndex());
					tablaMateria.setWidget(numRows, 1, new HTML(sb.toString()));
					materia.setSemestre(year);
					materias.add(materia);

					onChange(null);
				}

			} else if (event.getSource().equals(botonGuardar)) {
				botonGuardar.setEnabled(false);
				PersonaEscuela persona = new PersonaEscuela();
				persona.setNombre(nombre.getText());
				persona.setApellidoPaterno(apellidoPaterno.getText());
				persona.setApellidoMaterno(apellidoMaterno.getText());

				Direccion direccion;
				direccion = new Direccion();
				direccion.setCalle(calle.getText());
				direccion.setNumero(numero.getText());
				direccion.setColonia(colonia.getText());
				direccion.setCodigoPostal(codigoPostal.getText());
				direccion.setMunicipio(municipio.getText());
				direccion.setEstado(estado.getText());
				direccion.setPais(pais.getText());
				persona.setDireccionDomicilio(direccion);

				if (telefonoCelular.getText().length() > 0) {
					persona.getTelefonos().add(
							new Telefono(telefonoCelular.getText(),
									ComunicacionesKind.CELULAR));
				}
				if (telefonoCasa.getText().length() > 0) {
					persona.getTelefonos().add(
							new Telefono(telefonoCasa.getText(),
									ComunicacionesKind.CASA));
				}
				if (telefonoOficina.getText().length() > 0) {
					persona.getTelefonos().add(
							new Telefono(telefonoOficina.getText(),
									ComunicacionesKind.OFICINA));
				}

				if (mailPersonal.getText().length() > 0) {
					persona.getCorreos().add(
							new CorreoElectronico(mailPersonal.getText(),
									ComunicacionesKind.CASA));
				}
				if (mailTrabajo.getText().length() > 0) {
					persona.getCorreos().add(
							new CorreoElectronico(mailTrabajo.getText(),
									ComunicacionesKind.OFICINA));
				}
				persona.setFechaDeNacimiento(cumpleanos.getValue().getTime());
				persona.getMaterias().addAll(materias);

				Principal.service.savePersona(Principal.getUsuario()
						.getUsuarioId(), Principal.getAgenda().getId(),
						persona, new AsyncCallback<Persona>() {

							@Override
							public void onFailure(Throwable caught) {
								caught.printStackTrace();

							}

							@Override
							public void onSuccess(Persona result) {
								if (mainPanel.personasFamilia == null) {
									mainPanel.personasFamilia = new TreeSet<PersonaFamilia>();
								}
								mainPanel.personasEscuela
										.add((PersonaEscuela) result);
								mainPanel.reDraw();
								hide();
							}
						});
			}
		}

		@Override
		public void onChange(ChangeEvent event) {
			botonGuardar.setEnabled(isReady && materias.size() > 0);
		}
	}

	@Override
	public void onChange(ChangeEvent event) {
		isReady = nombre.getText().length() > 0;
		if (panelAmigos != null) {
			panelAmigos.onChange(null);
		}
		if (panelEscuela != null) {
			panelEscuela.onChange(null);
		}
		if (panelFamilia != null) {
			panelFamilia.onChange(null);

		}
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource().equals(botonAmigos)) {
			v.remove(panelEscuela);
			v.remove(panelFamilia);
			v.add(panelAmigos);
		} else if (event.getSource().equals(botonEscuela)) {
			v.remove(panelAmigos);
			v.remove(panelFamilia);
			v.add(panelEscuela);
		} else if (event.getSource().equals(botonFamilia)) {
			v.remove(panelAmigos);
			v.remove(panelEscuela);
			v.add(panelFamilia);
		}
	}
}