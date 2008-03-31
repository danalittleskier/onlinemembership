package org.ussa.validation;

import java.util.List;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.ValidatorAction;
import org.springframework.validation.Errors;
import org.ussa.beans.AccountBean;
import org.ussa.beans.LineItemBean;
import org.ussa.model.Inventory;

public class FieldChecks extends org.springmodules.validation.commons.FieldChecks
{
	public static boolean validateMembershipRequired(Object bean, ValidatorAction va, Field field, Errors errors)
	{
		AccountBean accountBean = (AccountBean) bean;
		List<LineItemBean> memberships = accountBean.getCartBean().getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP);
		boolean containsMembership = memberships.size() >= 1;

		if (!containsMembership)
		{
			FieldChecks.rejectValue(errors, field, va);
			return false;
		} else
		{
			return true;
		}

	}
}
