package com.ttf.Agenda.shared;

import java.io.Serializable;

/**
 * Una materia de una PersonaEscuela
 * 
 * @author miguelangeldelatorre
 * 
 */
public class Materia implements Serializable, Comparable<Materia> {

	private static final long serialVersionUID = 7846033472278872737L;
	private String nombreDeLaMateria;
	/**
	 * El semestre tiene el siguiente formato aaaai donde aaaa son lo dígitos
	 * del año y i es el número del semestre en ese año. 0 = Enero - Junio 1 =
	 * Verano 2 = Agosto - Noviembre
	 */
	private int semestre;

	public Materia() {
	}


	public String getNombreDeLaMateria() {
		return nombreDeLaMateria;
	}

	public void setNombreDeLaMateria(String nombreDeLaMateria) {
		this.nombreDeLaMateria = nombreDeLaMateria;
	}

	@Override
	public int compareTo(Materia o) {
		return nombreDeLaMateria.compareTo(o.nombreDeLaMateria);
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

}
