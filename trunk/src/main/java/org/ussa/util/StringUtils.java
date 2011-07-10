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

	public static String formatPostalCode(String code)
	{
		String result = null;
		if(code != null)
		{
			StringBuffer characters = new StringBuffer();
			for (char aChar : code.toCharArray())
			{
				if (Character.isLetterOrDigit(aChar))
				{
					characters.append(aChar);
				}
			}

			int length = characters.length();
			if(length > 0)
			{
				result = characters.toString().toUpperCase();

				// Canadian postal code
				if(length == 6)
				{
					result = result.substring(0, 3)+" "+result.substring(3, 6);
				}
				// nine digit US postal code
				if(length == 9)
				{
					result = result.substring(0, 5)+"-"+result.substring(5, 9);
				}
			}
		}
		return result;
	}
}
