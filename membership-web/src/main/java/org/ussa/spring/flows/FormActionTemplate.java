package org.ussa.spring.flows;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CharacterEditor;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.DataBinder;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.core.collection.AttributeMap;
import org.springmodules.validation.commons.ConfigurableBeanValidator;
import org.ussa.spring.util.CustomSqlDateEditor;


public class FormActionTemplate extends FormAction
{
    private final Log log = LogFactory.getLog(FormActionTemplate.class);

    private Map validators;

    public void setValidators(Map validators)
    {
        this.validators = validators;
    }

    protected void initBinder(RequestContext request, DataBinder binder)
    {
        binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(true));
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
        binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Character.class, new CharacterEditor(true));
        binder.registerCustomEditor(Date.class, new CustomSqlDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true));
    }

	public Event bindAndValidate(RequestContext ctx) throws Exception
	{
		ConfigurableBeanValidator validatorInstance = (ConfigurableBeanValidator) getValidator();
		validatorInstance.setFormName(ctx.getCurrentState().getId());

		AttributeMap propMap = ctx.getAttributes();
		if (propMap.contains("validator"))
		{
			String validatorName = (String)propMap.get("validator");
			validatorInstance.setFormName(validatorName);
		}
		else
		{
			validatorInstance.setFormName(ctx.getCurrentState().getId());
		}

		return super.bindAndValidate(ctx);
	}
}
