package com.ttf.Agenda.server.util;

import java.util.Stack;

import com.googlecode.objectify.ObjectifyService;

/**
 * Se usa para registrar las clases a ser usadas en Objectify
 * 
 * @author miguelangeldelatorre
 * 
 */
public class RegisterUtil {

	@SuppressWarnings("rawtypes")
	private static Stack<Class> registradas = new Stack<Class>();

	/**
	 * Registra una clase en Objectify, en caso de que la clase ya halla sido
	 * registrada ignora la petición
	 * 
	 * @param classy
	 *            La clase a ser recistrada
	 */
	public static void registerClassInObjectify(
			@SuppressWarnings("rawtypes") Class classy) {
		if (!registradas.contains(classy)) {
			try {
				ObjectifyService.register(classy);
				registradas.push(classy);
			} catch (java.lang.IllegalArgumentException ex) {
				// do nothing !
			}
		}
	}

}
