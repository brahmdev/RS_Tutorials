package com.devops.dev.webController;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@RequestMapping(value = "/salientFeature", method = RequestMethod.GET)
	public String salientFeature(Locale locale, Model model) {
		logger.info("Welcome on salientFeature Page! The client locale is {}.", locale);
		return "salientFeature";
	}
	
	@RequestMapping(value = "/foundersMessage", method = RequestMethod.GET)
	public String founderMessage(Locale locale, Model model) {
		logger.info("Welcome on foundersMessage Page! The client locale is {}.", locale);
		return "foundersMessage";
	}
	
	@RequestMapping(value = "/missionVision", method = RequestMethod.GET)
	public String missionVision(Locale locale, Model model) {
		logger.info("Welcome on missionVision Page! The client locale is {}.", locale);
		return "missionVision";
	}
	
	@RequestMapping(value = "/admission", method = RequestMethod.GET)
	public String admission(Locale locale, Model model) {
		logger.info("Welcome on admission Page! The client locale is {}.", locale);
		return "admission";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/adminDashBoard", method = RequestMethod.GET)
	public String adminDashBoard(Locale locale, Model model) {
		logger.info("Welcome on adminDashBoard Page! The client locale is {}.", locale);
		return "adminDashBoard";
	}
	
	@RequestMapping(value = "/boards", method = RequestMethod.GET)
	public String boards(Locale locale, Model model) {
		logger.info("Welcome on boards Page! The client locale is {}.", locale);
		return "boards";
	}
	
	@RequestMapping(value = "/classLevel", method = RequestMethod.GET)
	public String classLevel(Locale locale, Model model) {
		logger.info("Welcome on classLevel Page! The client locale is {}.", locale);
		return "classLevel";
	}
	
	@RequestMapping(value = "/classLevelType", method = RequestMethod.GET)
	public String classLevelType(Locale locale, Model model) {
		logger.info("Welcome on classLevelType Page! The client locale is {}.", locale);
		return "classLevelType";
	}
	
	@RequestMapping(value = "/subject", method = RequestMethod.GET)
	public String subject(Locale locale, Model model) {
		logger.info("Welcome on Subject Management Page! The client locale is {}.", locale);
		return "subject";
	}
	
	@RequestMapping(value = "/chapter", method = RequestMethod.GET)
	public String chapter(Locale locale, Model model) {
		logger.info("Welcome on chapter Management Page! The client locale is {}.", locale);
		return "chapter";
	}
	
	@RequestMapping(value = "/addStaff", method = RequestMethod.GET)
	public String addStaff(Locale locale, Model model) {
		logger.info("Welcome on chapter Management Page! The client locale is {}.", locale);
		return "addStaff";
	}
	
	@RequestMapping(value = "/addStudent", method = RequestMethod.GET)
	public String addStudent(Locale locale, Model model) {
		logger.info("Welcome on chapter Management Page! The client locale is {}.", locale);
		return "addStudent";
	}
	
	@RequestMapping(value = "/addGurdian", method = RequestMethod.GET)
	public String addGurdian(Locale locale, Model model) {
		logger.info("Welcome on chapter Management Page! The client locale is {}.", locale);
		return "addGurdian";
	}
}
