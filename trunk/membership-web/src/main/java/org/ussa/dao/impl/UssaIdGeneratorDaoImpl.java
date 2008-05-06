package org.ussa.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.ussa.dao.UssaIdGeneratorDao;
import org.ussa.model.ParameterTable;
import org.hibernate.LockMode;

public class UssaIdGeneratorDaoImpl extends HibernateDaoSupport implements UssaIdGeneratorDao
{
	@Transactional (propagation = Propagation.REQUIRES_NEW)
	public Long getNextUssaId()
	{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		ParameterTable ussaParam = (ParameterTable) hibernateTemplate.load(ParameterTable.class, ParameterTable.USSAID, LockMode.UPGRADE);
		Long ussaId = Long.valueOf(ussaParam.getParameterData());
		ussaParam.setParameterData(String.valueOf(ussaId + 1));
		return appendSingleRandomDigit(ussaId);
	}

	/**
	 * This function was derived from the pascal code below. Remember that in pascal array indexes are 1 based not 0 based, and pascal is NOT case sensitive.
	 * It is a bit confusing to follow, but all it does is take the original ussaId and add a random digit to the end of it.
	 * It it were my decision I might reccomend using a random number generator instead of this weird algorithm.
	 * @param ussaId is the original ussaId before a random digit is appended
	 * @return the new ussaId
	 */
	private Long appendSingleRandomDigit(Long ussaId)
	{
		String ussaIdStr = String.valueOf(ussaId);

		int total = 0;
		for(int i=0; i<ussaIdStr.length(); i++)
		{
			if((i+1)%2 == 0)
			{
				int num = ussaIdStr.charAt(i)*2;
				if(num > 9)
				{
					int x = 0;
					String numStr = String.valueOf(num);
					for(int j=0; j<numStr.length(); j++)
					{
						x += Integer.parseInt(String.valueOf(numStr.charAt(j)));
					}
					total += x;
				}
				else
				{
					total += num;
				}
			}
			else
			{
				total += Integer.parseInt(String.valueOf(ussaIdStr.charAt(i)));
			}
		}

		String totalStr = String.valueOf(total);
		int lastdigitInTotal = Integer.parseInt(String.valueOf(totalStr.charAt(totalStr.length()-1)));

		String randomDigit;
		if(lastdigitInTotal == 0)
		{
			randomDigit = String.valueOf(lastdigitInTotal);
		}
		else
		{
			randomDigit = String.valueOf(10 - lastdigitInTotal);
		}

		return new Long(ussaIdStr + randomDigit);
	}

	/*
	THIS IS THE OLD PASCAL FUNCTION THAT WAS USED TO DERIVE appendSingleRandomDigit() above

	function TfrmMain.AddCheckDigit(UssaId: Integer): Integer;
	var
	 total, num, i, j, x:   Integer;
	 strId:                 String;

	begin
		total := 0;
		strId := IntToStr(UssaId);
		for i := 1 to Length(strId) Do
		begin
			if i Mod 2 = 0 Then
			begin
				num := StrToInt(strId[i]) * 2;
				if num > 9 Then
				begin
					x := 0;
					for j := 1 to Length(IntToStr(num)) Do
						x := x + StrToInt(IntToStr(num)[j]);
					total := total + x;
				end
				else total := total + num;
			end
			else total := total + StrToInt(StrId[i]);
		end;
		num := StrToInt(IntToStr(Total)[Length(IntToStr(Total))]);
		if num = 0
		then result := 0
		else result := 10 - num;
	end;
	*/
}
