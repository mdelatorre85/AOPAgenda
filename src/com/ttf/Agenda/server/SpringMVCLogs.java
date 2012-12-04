package com.ttf.Agenda.server;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logs")
public class SpringMVCLogs {

	// DI via Spring
	String message;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getLogs(ModelMap model) {

		// model.addAttribute("logs", "Hola mundo");
		//
		// // return to jsp page, configured in mvc-dispatcher-servlet.xml, view
		// // resolver
		return "holaMundo";

	}

	public String getMessage() {
		return message;
	}
}
