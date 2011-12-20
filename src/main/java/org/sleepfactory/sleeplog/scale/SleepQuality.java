package org.sleepfactory.sleeplog.scale;

import org.sleepfactory.sleeplog.util.SleepUtils;


public enum SleepQuality implements SleepAttributeEnum
{
	VERY_POOR (1L), POOR (2L), FAIR (3L), GOOD (4L), EXCELLENT (5L);
	
	private Long value;
	
	private SleepQuality (Long value)
	{
		this.value = value;
	}
	
	public Long valueOf()
	{
		return value;
	}
	
	public static SleepQuality enumValueOf (Long value)
	{
		int intVal = value.intValue();
		
		switch (intVal)
		{
			case 1:
				return VERY_POOR;
			case 2: 
				return POOR;
			case 3:
				return FAIR;
			case 4:
				return GOOD;
			case 5:
				return EXCELLENT;
			default:
				return null;
		}
	}
	
	public String qualitative()
	{
		return SleepUtils.qualitative (this);
	}

}