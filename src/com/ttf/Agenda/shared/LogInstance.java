package com.ttf.Agenda.shared;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.persistence.Id;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.ttf.Agenda.server.HelpMeLog;
import com.ttf.Agenda.server.PMF;
import com.ttf.Agenda.server.util.RegisterUtil;

public class LogInstance implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final long segundo = 1000l;
	private static final long minuto = segundo * 60l;
	private static final long hora = minuto * 60l;
	private static final long dia = hora * 24l;

	private static final long enero = dia * 31l;
	private static final long febrero = dia * 28l;
	private static final long febrerobiciesto = dia * 29l;
	private static final long marzo = dia * 31l;
	private static final long abril = dia * 30l;
	private static final long mayo = dia * 31l;
	private static final long junio = dia * 30l;
	private static final long julio = dia * 31l;
	private static final long agosto = dia * 31l;
	private static final long septiembre = dia * 30l;
	private static final long octubre = dia * 31l;
	private static final long noviembre = dia * 30l;
	private static final long diciembre = dia * 31l;

	private static final long ano = dia * 365l;
	private static final long anobiciesto = dia * 366l;

	@Id
	private Long id;
	private String log = null;
	private String timetext;
	private long time;

	public LogInstance() {
	}

	public LogInstance(String log) {
		this.log = log;
		Date date = new Date();
		time = date.getTime();
		long aux = time, ac = 1970l;
		while (aux > ano) {
			if ((ac + 1) % 4 == 0) {
				aux -= anobiciesto;
			} else {
				aux -= ano;
			}
			ac++;
		}

		timetext = ac + "/";
		long anio = ac;
		ac = 1;

		if (aux > enero) {
			aux -= enero;
			ac++;
			if (aux > febrero) {
				if ((anio % 4) != 0) {
					aux -= febrero;
					ac++;
				} else {
					if (aux > febrerobiciesto) {
						aux -= febrerobiciesto;
						ac++;
					}
				}
				if (aux > marzo) {
					aux -= marzo;
					ac++;
					if (aux > abril) {
						aux -= abril;
						ac++;
						if (aux > mayo) {
							aux -= mayo;
							ac++;
							if (aux > junio) {
								aux -= junio;
								ac++;
								if (aux > julio) {
									aux -= julio;
									ac++;
									if (aux > agosto) {
										aux -= agosto;
										ac++;
										if (aux > septiembre) {
											aux -= septiembre;
											ac++;
											if (aux > octubre) {
												aux -= octubre;
												ac++;
												if (aux > noviembre) {
													aux -= noviembre;
													ac++;
													if (aux > diciembre) {
														aux -= diciembre;
														ac++;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		timetext += ac;
		ac = (aux - (aux % dia)) / dia;
		ac++;
		timetext += "/";
		timetext += ac;
		timetext += " ";
		aux = aux % dia;
		ac = 0;

		ac = (aux - (aux % hora)) / hora;
		timetext += ac;
		timetext += ":";
		aux = aux % hora;

		ac = 0;
		ac = (aux - (aux % minuto)) / minuto;
		timetext += ac;
		timetext += ":";
		aux = aux % minuto;

		ac = 0;
		ac = (aux - (aux % segundo)) / segundo;
		timetext += ac;
		timetext += ".";
		aux = aux % segundo;

		timetext += aux;
		timetext += " GMT";
	}

	public static void log(String log, PersistenceManager pm) {
		pm.makePersistent(new LogInstance(log));
	}

	public static void log(String log) {
		log(log, PMF.get().getPersistenceManager());
	}

	public static void deleteAll() {
		RegisterUtil.registerClassInObjectify(HelpMeLog.class);
		Objectify ofy = ObjectifyService.begin();
		ofy.delete(ofy.query(HelpMeLog.class).fetchKeys());
	}

	public String getLog() {
		return log;
	}

	public String getTimetext() {
		return timetext;
	}

	@Override
	public String toString() {
		return log;
	}
}