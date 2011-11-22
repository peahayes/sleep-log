package org.sleepfactory.sleeplog.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import org.sleepfactory.sleeplog.service.SleepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping (value="/secure")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger (UserController.class);
	
	@Inject @Named ("sleepService")
	SleepService sleepService;

	@RequestMapping (value = "sleeper/home", method = RequestMethod.GET)
	public String adminHome (Locale locale, Model model) {
		
		logger.info ("Welcome home! the client locale is "+ locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance (DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute ("serverTime", formattedDate );
		
		return "secure/sleeper/home";
	}
	
	@RequestMapping (value = "home", method = RequestMethod.GET)
	public String userHome (Locale locale, Model model) {
		return "secure/home";
	}
}
