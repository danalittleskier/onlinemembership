package org.ussa.util;

public class StringUtils extends org.apache.commons.lang.StringUtils
{
	public static String formatPhone(String phone)
	{
		String result = null;
		if(phone != null)
		{
			StringBuffer numbers = new StringBuffer();
			for (char aChar : phone.toCharArray())
			{
				if (Character.isDigit(aChar))
				{
					numbers.append(aChar);
				}
			}
			int length = numbers.length();
			if(length >= 7)
			{
				result = numbers.substring(length-7, length-4)+"-"+numbers.substring(length-4, length);
			}
			if(length >= 10)
			{
				result = numbers.substring(0, length-10)+"("+numbers.substring(length-10, length-7)+") "+result;
			}
		}
		return result;
	}
}
