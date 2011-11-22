package org.sleepfactory.sleeplog.web;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import org.sleepfactory.sleeplog.SleepEntry;
import org.sleepfactory.sleeplog.service.SleepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping (value = "/")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger (LoginController.class);
	
	@Inject
	@Named ("sleepService")
	private SleepService sleepService;

	@ModelAttribute (value = "user")
	public SleepEntry newRequest() {
		return new SleepEntry();
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String displayLogin (Locale locale) {
		logger.debug("Welcome login page! the client locale is "+ locale.toString());
		return "public/login";
	}

	@RequestMapping (value = "/login", method = RequestMethod.POST)
	public String login (@ModelAttribute ("user") SleepEntry user, BindingResult result) {
		return "redirect:" + sleepService.getHomePageURLForUser();
	}

}
