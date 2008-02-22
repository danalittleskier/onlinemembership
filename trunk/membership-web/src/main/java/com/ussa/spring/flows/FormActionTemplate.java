package com.ussa.spring.flows;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CharacterEditor;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.core.collection.AttributeMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;


public class FormActionTemplate extends FormAction
{

    private static final String VALIDATOR = "validator";
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
    }

    public Event bindAndValidate(RequestContext ctx) throws Exception
    {
        AttributeMap propMap = ctx.getAttributes();
        if (propMap.contains("validator"))
        {
            String validatorName = (String)propMap.get("validator");
            if (!validators.containsKey(validatorName))
            {
                throw new Exception("Invalid validator name");
            }
            Validator validatorInstance = (Validator)validators.get(validatorName);
            setValidator(validatorInstance);
        }
        return super.bindAndValidate(ctx);
    }
}
