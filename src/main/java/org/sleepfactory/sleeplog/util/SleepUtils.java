package org.sleepfactory.sleeplog.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.sleepfactory.sleeplog.scale.SleepAttributeEnum;

public class SleepUtils {

	public static String qualitative (SleepAttributeEnum value)
	{
		String[] parts = String.valueOf (value).split ("_");
		for (int i = 0; i < parts.length; i++) firstLetterCaps (parts, i);
		List<String> list = Arrays.asList (parts);
		return StringUtils.join (list, ' ');
	}

	private static void firstLetterCaps (String parts[], int i) 
	{
		parts[i] = parts[i].toLowerCase();
		parts[i] = StringUtils.capitalize (parts[i]);
	}

}
