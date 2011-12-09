package org.sleepfactory.sleeplog.scale;

import org.sleepfactory.sleeplog.util.SleepUtils;

public enum Restfulness implements SleepAttributeEnum
{
	NOT_AT_ALL (1L), SLIGHTLY (2L), SOMEWHAT (3L), RESTED (4L), WELL_RESTED (5L);
	
	private Long value;
	
	private Restfulness (Long value)
	{
		this.value = value;
	}
	
	public Long valueOf()
	{
		return value;
	}
	
	public static Restfulness enumValueOf (Long value)
	{
		int intVal = value.intValue();
		
		switch (intVal)
		{
			case 1:
				return NOT_AT_ALL;
			case 2: 
				return SLIGHTLY;
			case 3:
				return SOMEWHAT;
			case 4:
				return RESTED;
			case 5:
				return WELL_RESTED;
			default:
				return null;
		}
	}
	
	public String qualitative()
	{
		return SleepUtils.qualitative (this);
	}

}
