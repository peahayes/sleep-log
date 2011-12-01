package org.sleepfactory.sleeplog.web;

import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import org.sleepfactory.sleeplog.SleepEntry;
import org.sleepfactory.sleeplog.service.SleepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping (value = "/secure/sleep")
public class SleepController {
	
	private static final Logger logger = LoggerFactory.getLogger (SleepController.class);
	
	private Long masterId = 1L;
	
	@Inject
	@Named ("sleepService")
	private SleepService sleepService;

	@ModelAttribute (value = "sleepEntry")
	public SleepEntry newRequest() 
	{
		SleepEntry newEntry = new SleepEntry (masterId++);
		newEntry.setDate (new Date());
		return newEntry;
	}

	@RequestMapping (value = { "/enterSleep", "sleep/enterSleep" }, method = RequestMethod.GET)
	public String displaySleepEntryForm (Locale locale, Model model) 
	{
		logger.debug ("Welcome to sleep entry page! the client locale is "+ locale.toString());
		model.addAttribute ("editMode", "add");
		return "secure/sleeper/enterSleep";
	}

	@RequestMapping (value = { "/enterSleep/{mode}", "sleep/enterSleep/{mode}"}, method = RequestMethod.POST)
	public String submitSleepEntry (@ModelAttribute ("sleepEntry") SleepEntry sleepEntry, 
									@ModelAttribute ("mode") String mode, BindingResult result, Model model) 
	{
		if ("update".equals (mode))
			sleepService.update (sleepEntry);
		else
			sleepService.add (sleepEntry);

		model.addAttribute ("sleepEntry", sleepEntry);
		model.addAttribute ("editMode", "add");

		return "secure/sleeper/home";
	}
	
	@RequestMapping (value = { "/updateSleep/{id}", "sleep/updateSleep/{id}" }, method = RequestMethod.GET)
	public String displaySleepUpdateForm (@ModelAttribute ("id") String idStr, Model model) 
	{
		Long id = Long.valueOf (idStr);
		SleepEntry entry = sleepService.getSleepEntryById (id);
		
		model.addAttribute ("sleepEntry", entry);
		model.addAttribute ("editMode", "update");
		
		return "secure/sleeper/enterSleep";
	}

	@RequestMapping (value = { "/viewEntries", "add/viewEntries", "update/viewEntries", "sleep/viewEntries" }, method = RequestMethod.GET)
	public String viewEntries (Locale locale, Model model)
	{
		model.addAttribute ("sleepLog", sleepService.getSleepLog());
		return "secure/sleeper/viewEntries";
	}

}
