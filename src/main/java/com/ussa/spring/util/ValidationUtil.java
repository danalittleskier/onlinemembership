package com.ussa.spring.util;

import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
//import org.apache.struts.validator.validwhen.ValidWhenLexer;
//import org.apache.struts.validator.validwhen.ValidWhenParser;
import org.springframework.validation.Errors;
import org.springmodules.validation.commons.*;

/**
 * ValidationUtil Helper class for performing custom validations that
 * aren't already included in the commons validator.
 */
public class ValidationUtil
{
    private final static Log log = LogFactory.getLog(ValidationUtil.class);

    protected static void rejectValue(Errors errors, Field field,
            ValidatorAction va)
    {
        String fieldCode = field.getKey();
        String errorCode = MessageUtils.getMessageKey(va, field);
        Object[] args = MessageUtils.getArgs(va, field);

        if (log.isDebugEnabled())
        {
            log.debug("Rejecting value [field='" + fieldCode + "', errorCode='"
                    + errorCode + "']");
        }

        errors.rejectValue(fieldCode, errorCode, args, errorCode);
    }

    /**
     * Returns true if <code>obj</code> is null or a String.
     */
    private static boolean isString(Object obj)
    {
        return (obj == null) ? true : String.class.isInstance(obj);
    }

    /**
     * Checks if the field matches the boolean expression specified in
     * <code>test</code> parameter.
     *
     * @param bean
     *            The bean validation is being performed on.
     *
     * @param va
     *            The <code>ValidatorAction</code> that is currently being
     *            performed.
     *
     * @param field
     *            The <code>Field</code> object associated with the current
     *            field being validated.
     *
     * @param errors
     *            The <code>Errors</code> object to add errors to if
     *            any validation errors occur.
     *
     * @param request
     *            Current request object.
     *
     * @return <code>true</code> if meets stated requirements,
     *         <code>false</code> otherwise.
     *
     * This routine has been adapted from code at the following sites:
     *      http://opensource2.atlassian.com/projects/spring/browse/MOD-38
     *      http://svn.apache.org/viewcvs.cgi/struts/action/trunk/src/java/org/apache/struts/validator/validwhen/ValidWhen.java?view=markup
     */
    /*   public static boolean validateValidWhen(Object bean, ValidatorAction va,
            Field field, Errors errors, Validator validator,
            HttpServletRequest request)
    {

        Object form = validator
                .getParameterValue(org.apache.commons.validator.Validator.BEAN_PARAM);
        String value = null;
        boolean valid = false;
        int index = -1;

        if (field.isIndexed())
        {
            String key = field.getKey();

            final int leftBracket = key.indexOf("[");
            final int rightBracket = key.indexOf("]");

            if ((leftBracket > -1) && (rightBracket > -1))
            {
                index = Integer.parseInt(key.substring(leftBracket + 1,
                        rightBracket));
            }
        }

        if (isString(bean))
        {
            value = (String) bean;
        } else
        {
            value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        }

        String test = null;

        try
        {
            test = field.getVarValue("test");
        } catch (IllegalArgumentException ex)
        {
            String logErrorMsg = "ValidWhenLexer Error for field ' "
                    + field.getKey() + "' NULL test - " + ex;
            log.error(logErrorMsg);

            return false;
        }

        // Create the Lexer
        ValidWhenLexer lexer = null;

        try
        {
            lexer = new ValidWhenLexer(new StringReader(test));
        } catch (Exception ex)
        {
            String logErrorMsg = "ValidWhenLexer Error for field ' "
                    + field.getKey() + "' - " + ex;

            log.error(logErrorMsg);
            return false;
        }
        // Create the Parser
        ValidWhenParser parser = null;

        try
        {
            parser = new ValidWhenParser(lexer);
        } catch (Exception ex)
        {
            String logErrorMsg = "ValidWhenParser Error for field ' "
                    + field.getKey() + "' - " + ex;

            log.error(logErrorMsg);

            return false;
        }

        parser.setForm(form);
        parser.setIndex(index);
        parser.setValue(value);

        try
        {
            parser.expression();
            valid = parser.getResult();
        } catch (Exception ex)
        {
            ex.printStackTrace();
            String logErrorMsg = "ValidWhen Error for field ' "
                    + field.getKey() + "' - " + ex;
            log.error(logErrorMsg);
            rejectValue(errors, field, va);
            return false;
        }

        if (!valid)
        {
            rejectValue(errors, field, va);
            return false;
        }
        return true;
    }*/
}
