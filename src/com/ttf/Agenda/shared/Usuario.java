package com.ttf.Agenda.shared;

import java.io.Serializable;

import javax.persistence.Id;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 7175826545423107097L;
	@Id
	private Long usuarioId;
	private String nombreDeUsuario;
	private String contrasenaDeUsuario;
	
	public Usuario() {
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}

	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}

	public String getContrasenaDeUsuario() {
		return contrasenaDeUsuario;
	}

	public void setContrasenaDeUsuario(String contrasenaDeUsuario) {
		this.contrasenaDeUsuario = contrasenaDeUsuario;
	}

}
