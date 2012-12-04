package com.ttf.Agenda.shared;

import java.io.Serializable;
import java.util.Date;
import java.util.TreeSet;

import javax.persistence.Embedded;
import javax.persistence.Id;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;

public abstract class Persona implements Serializable, Comparable<Persona> {

	private static final long serialVersionUID = 8376350632309289379L;
	/**
	 * Cada contacto tiene un nombre, dirección, teléfono de casa y celular,
	 * correo electrónico y fecha de nacimiento. Existen diversos tipos de
	 * contactos: amigos, escuela y familia. Para los amigos, se guarda también
	 * el twitter y el facebook. Para los contactos en la escuela, se agrega la
	 * o las materias en las que se tiene el contacto. Estas materias deberán
	 * guardar el nombre y el semestre de la materia. Para los de la familia, se
	 * guarda el parentesco.
	 */

	@Id
	private Long id;
	@Parent
	private Key<Agenda> agenda;

	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;

	@Embedded
	private Direccion direccionDomicilio;
	@Embedded
	private TreeSet<Telefono> telefonos;
	@Embedded
	private TreeSet<CorreoElectronico> correos;

	private Long fechaDeNacimiento = null;

	public String toString() {
		return id + " " + getNombreCompleto();
	}

	public Persona() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public Direccion getDireccionDomicilio() {
		return direccionDomicilio;
	}

	public void setDireccionDomicilio(Direccion direccionDomicilio) {
		this.direccionDomicilio = direccionDomicilio;
	}

	public TreeSet<Telefono> getTelefonos() {
		if (telefonos == null) {
			telefonos = new TreeSet<Telefono>();
		}
		return telefonos;
	}

	public void setTelefonos(TreeSet<Telefono> telefonos) {
		this.telefonos = telefonos;
	}

	public TreeSet<CorreoElectronico> getCorreos() {
		if (correos == null) {
			correos = new TreeSet<CorreoElectronico>();
		}
		return correos;
	}

	public void setCorreos(TreeSet<CorreoElectronico> correos) {
		this.correos = correos;
	}

	public Long getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Long fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Key<Agenda> getAgenda() {
		return agenda;
	}

	public void setAgenda(Key<Agenda> agenda) {
		this.agenda = agenda;
	}

	public int getEdad() {
		Date date = new Date();
		return (int) Math.floor((date.getTime() - fechaDeNacimiento)
				/ (1000l * 60l * 60l * 24l * 365l));
	}

	/**
	 * Una persona es igual a otra su tiene el mismo nombre y fecha de
	 * nacimineto.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Persona) {
			Persona p = (Persona) obj;

			if (getNombreCompleto().equals(p.getNombreCompleto())
					&& fechaDeNacimiento == p.getFechaDeNacimiento()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String getNombreCompleto() {
		StringBuilder sb = new StringBuilder(nombre);
		sb.append(" ");
		sb.append(apellidoPaterno);
		sb.append(" ");
		sb.append(apellidoMaterno);
		return sb.toString();
	}

	@Override
	public int compareTo(Persona o) {
		return getNombreCompleto().compareTo(o.getNombreCompleto());
	}

	public void setAllAtributesNullButName() {
		correos = null;
		direccionDomicilio = null;
		fechaDeNacimiento = null;
		telefonos = null;
	}

}
