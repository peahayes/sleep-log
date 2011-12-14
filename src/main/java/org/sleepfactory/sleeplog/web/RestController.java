package org.sleepfactory.sleeplog.web;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.sleepfactory.sleeplog.service.SleepService;
import org.sleepfactory.sleeplog.service.SleepService.Score;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping ("/secure/rest")
public class RestController {

	@Inject
	@Named ("sleepService")
	private SleepService sleepService;

	private static final Logger log = LoggerFactory.getLogger (RestController.class);

	@RequestMapping (value = "/getEnergyLevels", method = RequestMethod.GET)
	@ResponseBody
	public List<Score> getEnergyLevels (HttpServletRequest request)
		throws InterruptedException
	{
		log.info ("Entering getEnergyLevels");
		return sleepService.getEnergyLevels();
	}

	@RequestMapping (value = "/getRestfulnessScores", method = RequestMethod.GET)
	@ResponseBody
	public List<Score> getRestfulnessScores (HttpServletRequest request)
		throws InterruptedException
	{
		log.info ("Entering getRestfulnessScores");
		return sleepService.getRestfulnessScores();
	}

}
