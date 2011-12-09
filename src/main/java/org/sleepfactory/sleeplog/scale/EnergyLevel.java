package org.sleepfactory.sleeplog.scale;

import org.sleepfactory.sleeplog.util.SleepUtils;

public enum EnergyLevel implements FiveScaleEnum 
{
	EXTREMELY_FATIGUED (1L), MODERATELY_FATIGUED (2L), MILDLY_FATIGUED (3L), 
	SOMEWHAT_ENERGETIC (4L), VERY_ENERGETIC (5L);
	
	private Long value;
	
	private EnergyLevel (Long value)
	{
		this.value = value;
	}
	
	public Long valueOf()
	{
		return value;
	}
	
	public static EnergyLevel enumValueOf (Long value)
	{
		int intVal = value.intValue();
		
		switch (intVal)
		{
			case 1:
				return EXTREMELY_FATIGUED;
			case 2: 
				return MODERATELY_FATIGUED;
			case 3:
				return MILDLY_FATIGUED;
			case 4:
				return SOMEWHAT_ENERGETIC;
			case 5:
				return VERY_ENERGETIC;
			default:
				return null;
		}
	}
	
	public String qualitative()
	{
		return SleepUtils.qualitative (this);
	}


}
