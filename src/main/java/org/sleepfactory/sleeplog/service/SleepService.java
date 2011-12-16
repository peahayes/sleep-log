package org.sleepfactory.sleeplog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sleepfactory.sleeplog.SleepEntry;
import org.sleepfactory.sleeplog.SleepLog;
import org.sleepfactory.sleeplog.scale.Activity;
import org.sleepfactory.sleeplog.scale.EnergyLevel;
import org.sleepfactory.sleeplog.scale.Restfulness;
import org.sleepfactory.sleeplog.scale.SleepQuality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service ("sleepService")
public class SleepService {
	
	private SleepLog log = new SleepLog();
	private Long masterId = 1L;

	private static final Logger logger = LoggerFactory.getLogger (SleepService.class);

	public Set<SleepEntry> getEntries()
	{
		return log.getEntries();
	}

	public void add (SleepEntry sleepEntry) 
	{	
		sleepEntry.setId (masterId++);
		log.add (sleepEntry);
	}

	public SleepLog getSleepLog() 
	{
		return log;
	}
	
	public SleepEntry getSleepEntryById (Long id)
	{
		if (id == null)
			return null;
		
		for (SleepEntry entry : getEntries())
		{
			if (id.equals (entry.getId()))
				return entry;
		}
			
		return null;
	}

	public void update (SleepEntry sleepEntry) 
	{
		SleepEntry oldEntry = getSleepEntryById (sleepEntry.getId());
		
		log.getEntries().remove (oldEntry);
		log.getEntries().add (sleepEntry);
	}

	public void removeEntry (Long id) 
	{
		SleepEntry entry = getSleepEntryById (id);
		log.getEntries().remove (entry);
	}

	public List<Score> getEnergyLevels() 
		throws InterruptedException
	{
		List<Score> levels = new ArrayList<Score>();
		
		levels.add (new Score (EnergyLevel.EXTREMELY_FATIGUED.valueOf(), EnergyLevel.EXTREMELY_FATIGUED.qualitative()));
		levels.add (new Score (EnergyLevel.MODERATELY_FATIGUED.valueOf(), EnergyLevel.MODERATELY_FATIGUED.qualitative()));
		levels.add (new Score (EnergyLevel.MILDLY_FATIGUED.valueOf(), EnergyLevel.MILDLY_FATIGUED.qualitative()));
		levels.add (new Score (EnergyLevel.SOMEWHAT_ENERGETIC.valueOf(), EnergyLevel.SOMEWHAT_ENERGETIC.qualitative()));
		levels.add (new Score (EnergyLevel.VERY_ENERGETIC.valueOf(), EnergyLevel.VERY_ENERGETIC.qualitative()));
		
		logger.info ("Sleeping for 800 milliseconds");
		Thread.sleep (800);
		logger.info ("Done sleeping for 800 milliseconds");
		
		return levels;
	}
	
	public List<Score> getRestfulnessScores() 
		throws InterruptedException
	{
		List<Score> levels = new ArrayList<Score>();
		
		levels.add (new Score (Restfulness.NOT_AT_ALL.valueOf(), Restfulness.NOT_AT_ALL.qualitative()));
		levels.add (new Score (Restfulness.SLIGHTLY.valueOf(), Restfulness.SLIGHTLY.qualitative()));
		levels.add (new Score (Restfulness.SOMEWHAT.valueOf(), Restfulness.SOMEWHAT.qualitative()));
		levels.add (new Score (Restfulness.RESTED.valueOf(), Restfulness.RESTED.qualitative()));
		levels.add (new Score (Restfulness.WELL_RESTED.valueOf(), Restfulness.WELL_RESTED.qualitative()));
		
		logger.info ("Sleeping for 800 milliseconds");
		Thread.sleep (800);
		logger.info ("Done sleeping for 800 milliseconds");
		
		return levels;
	}

	public Map<String, String> getRestedScores() 
	{
		Map<String, String> levels = new HashMap<String, String>();
		
		levels.put (String.valueOf (SleepQuality.VERY_POOR.valueOf()), SleepQuality.VERY_POOR.qualitative());
		levels.put (String.valueOf (SleepQuality.POOR.valueOf()), SleepQuality.POOR.qualitative());
		levels.put (String.valueOf (SleepQuality.FAIR.valueOf()), SleepQuality.FAIR.qualitative());
		levels.put (String.valueOf (SleepQuality.GOOD.valueOf()), SleepQuality.GOOD.qualitative());
		levels.put (String.valueOf (SleepQuality.EXCELLENT.valueOf()), SleepQuality.EXCELLENT.qualitative());
		
		return levels;
	}

	public Map<String, String> getActivities() 
	{
		Map<String, String> levels = new HashMap<String, String>();
		
		levels.put (String.valueOf (Activity.BIKING.valueOf()), Activity.BIKING.qualitative());
		levels.put (String.valueOf (Activity.HIKING.valueOf()), Activity.HIKING.qualitative());
		levels.put (String.valueOf (Activity.WALKING.valueOf()), Activity.WALKING.qualitative());
		levels.put (String.valueOf (Activity.WEIGHTS.valueOf()), Activity.WEIGHTS.qualitative());
		levels.put (String.valueOf (Activity.WORKOUT.valueOf()), Activity.WORKOUT.qualitative());
		
		return levels;
	}

	public class Score 
	{
		public String description;
		public String value;
		
		public Score (Long val, String desc)
		{
			this.description = desc;
			this.value = String.valueOf (val);
		}
	}
	
}
