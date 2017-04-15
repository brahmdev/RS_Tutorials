package com.devops.dev.webController;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPage(Locale locale, Model model) {
		logger.info("Welcome /! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "dashBoard";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "dashBoard";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public String calendar(Locale locale, Model model) {
		logger.info("Welcome Calendar! The client locale is {}.", locale);

		return "calendar";
	}

	@RequestMapping(value = "/country", method = RequestMethod.GET)
	public String country(Locale locale, Model model) {
		logger.info("Welcome Country! The client locale is {}.", locale);

		return "country";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contactUs(Locale locale, Model model) {
		logger.info("Welcome on Contact Us Page! The client locale is {}.", locale);
		
		return "contact";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Locale locale, Model model) {
		logger.info("Welcome on Contact Us Page! The client locale is {}.", locale);
		
		return "403";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Welcome on Contact Us Page! The client locale is {}.", locale);
		
		return "login";
	}
	
	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String courses(Locale locale, Model model) {
		logger.info("Welcome on courses Page! The client locale is {}.", locale);
		
		return "courses";
	}
}
