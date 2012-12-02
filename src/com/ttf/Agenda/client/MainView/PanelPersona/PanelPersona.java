package com.ttf.Agenda.client.MainView.PanelPersona;

import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.ttf.Agenda.client.MainView.MainPanel;
import com.ttf.Agenda.shared.CorreoElectronico;
import com.ttf.Agenda.shared.Persona;
import com.ttf.Agenda.shared.Telefono;

public abstract class PanelPersona extends DecoratorPanel {

	HTML html;
	MainPanel mainPanel;
	VerticalPanel v;
	Persona persona;

	public PanelPersona(MainPanel mainPanel, Persona p) {
		super();
		this.mainPanel = mainPanel;
		this.persona = p;

		v = new VerticalPanel();
		v.setPixelSize(600, 640);

		StringBuilder sb = new StringBuilder();
		sb.append("<H1>");
		sb.append(p.getNombreCompleto());
		sb.append("</H1>");

		if (p.getCorreos().size() > 0) {
			sb.append("<H2>Correo Electrónico</H2>");
			for (CorreoElectronico email : p.getCorreos()) {
				sb.append("<p><b>");
				switch (email.getTipoDeCorreo()) {
				case CorreoElectronico.CASA:
					sb.append("Personal</b> ");
					break;
				case CorreoElectronico.OFICINA:
					sb.append("Trabajo</b> ");
					break;
				case CorreoElectronico.CELULAR:
					sb.append("Móvil</b> ");
					break;
				case CorreoElectronico.ASISTENTE:
					sb.append("Asistente</b> ");
					break;
				default:
					sb.append("eMail</b> ");
					break;
				}
				sb.append(email.getDireccionDeCorreo());
				sb.append("</p>");
			}
		}

		if (p.getDireccionDomicilio() != null) {
			sb.append("<H2>Correo Electrónico</H2>");
			if (p.getDireccionDomicilio().getCalle().length() > 0) {
				sb.append("<p><b>Calle</b> ");
				sb.append(p.getDireccionDomicilio().getCalle());
				sb.append("</p>");
			}
			if (p.getDireccionDomicilio().getNumero().length() > 0) {
				sb.append("<p><b>Número</b> ");
				sb.append(p.getDireccionDomicilio().getNumero());
				sb.append("</p>");
			}
			if (p.getDireccionDomicilio().getColonia().length() > 0) {
				sb.append("<p><b>Colonia</b> ");
				sb.append(p.getDireccionDomicilio().getColonia());
				sb.append("</p>");
			}
			if (p.getDireccionDomicilio().getMunicipio().length() > 0) {
				sb.append("<p><b>Municipio</b> ");
				sb.append(p.getDireccionDomicilio().getMunicipio());
				sb.append("</p>");
			}
			if (p.getDireccionDomicilio().getEstado().length() > 0) {
				sb.append("<p><b>Esatdo</b> ");
				sb.append(p.getDireccionDomicilio().getEstado());
				sb.append("</p>");
			}
			if (p.getDireccionDomicilio().getPais().length() > 0) {
				sb.append("<p><b>Pais</b> ");
				sb.append(p.getDireccionDomicilio().getPais());
				sb.append("</p>");
			}
			if (p.getDireccionDomicilio().getCodigoPostal().length() > 0) {
				sb.append("<p><b>CP</b> ");
				sb.append(p.getDireccionDomicilio().getCodigoPostal());
				sb.append("</p>");
			}

		}

		if (p.getTelefonos().size() > 0) {
			sb.append("<H2>Teléfonos</H2>");
			for (Telefono email : p.getTelefonos()) {
				sb.append("<p><b>");
				switch (email.getTipoDeTelefono()) {
				case CorreoElectronico.CASA:
					sb.append("CAsa</b> ");
					break;
				case CorreoElectronico.OFICINA:
					sb.append("Oficina</b> ");
					break;
				case CorreoElectronico.CELULAR:
					sb.append("Celular</b> ");
					break;
				case CorreoElectronico.ASISTENTE:
					sb.append("Asistente</b> ");
					break;
				default:
					sb.append("Teléfono</b> ");
					break;
				}
				sb.append(email.getNumeroTelefonico());
				sb.append("</p>");
				if (email.getExtension() != null
						&& email.getExtension().length() > 0) {
					sb.append("<p><b>Extensión</b> ");
					sb.append(email.getExtension());
					sb.append("</p>");
				}
				if (email.getClaveDeLargaDistancia() != null
						&& email.getClaveDeLargaDistancia().length() > 0) {
					sb.append("<p><b>Clave de Larga Distancia</b> ");
					sb.append(email.getClaveDeLargaDistancia());
					sb.append("</p>");
				}
			}
		}

		if (p.getFechaDeNacimiento() != null) {
			sb.append("<H2>Edad</H2>");
			sb.append("<p>");
			sb.append(p.getEdad());
			sb.append("</p>");
		}

		html = new HTML(sb.toString());
		v.add(html);
		setWidget(v);
	}
}
