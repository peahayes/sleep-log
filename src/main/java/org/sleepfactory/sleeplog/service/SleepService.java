package org.sleepfactory.sleeplog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.sleepfactory.sleeplog.SleepEntry;
import org.sleepfactory.sleeplog.SleepLog;
import org.sleepfactory.sleeplog.scale.EnergyLevel;
import org.springframework.stereotype.Service;

@Service ("sleepService")
public class SleepService {
	
	private SleepLog log = new SleepLog();
	
	private Long masterId = 1L;

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

	public List<Energy> getEnergyLevels() 
	{
		List<Energy> levels = new ArrayList<Energy>();
		
		levels.add (new Energy (EnergyLevel.EXTREMELY_FATIGUED.valueOf(), EnergyLevel.EXTREMELY_FATIGUED.qualitative()));
		levels.add (new Energy (EnergyLevel.MODERATELY_FATIGUED.valueOf(), EnergyLevel.MODERATELY_FATIGUED.qualitative()));
		levels.add (new Energy (EnergyLevel.MILDLY_FATIGUED.valueOf(), EnergyLevel.MILDLY_FATIGUED.qualitative()));
		levels.add (new Energy (EnergyLevel.SOMEWHAT_ENERGETIC.valueOf(), EnergyLevel.SOMEWHAT_ENERGETIC.qualitative()));
		levels.add (new Energy (EnergyLevel.VERY_ENERGETIC.valueOf(), EnergyLevel.VERY_ENERGETIC.qualitative()));
		
		return levels;
	}
	
	public class Energy 
	{
		public String description;
		public String value;
		
		public Energy (Long val, String desc)
		{
			this.description = desc;
			this.value = String.valueOf (val);
		}
	}

}
