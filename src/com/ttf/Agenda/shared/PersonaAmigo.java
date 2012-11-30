package com.ttf.Agenda.shared;

/**
 * un contacto de la libreta de amigos
 * 
 * @author miguelangeldelatorre
 * 
 */
public class PersonaAmigo extends Persona {

	private static final long serialVersionUID = -2753373041038844323L;

	// Para los amigos, se guarda tambiénel twitter y el facebook.

	private String facebook;
	private String twitter;

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	@Override
	public void setAllAtributesNullButName() {
		super.setAllAtributesNullButName();
		twitter = null;
		facebook = null;
	}

}
