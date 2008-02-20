package com.ussa.spring.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CharacterEditor;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.ussa.spring.util.CustomSqlDateEditor;
import com.ussa.spring.util.CustomSqlTimestampEditor;

public abstract class AbstractSimpleFormController extends SimpleFormController
{
    private static Log log = LogFactory.getLog(AbstractSimpleFormController.class);

    protected final void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception
    {
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
        binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Character.class, new CharacterEditor(true));
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
//        binder.registerCustomEditor(java.sql.Date.class, new CustomSqlDateEditor(new SimpleDateFormat(
//                getText("date.format")), true));
//        binder.registerCustomEditor(java.sql.Timestamp.class, new CustomSqlTimestampEditor(new SimpleDateFormat(
//                getText("dateTime.format")), true));
//        binder.registerCustomEditor(Date.class,
//                new CustomDateEditor(new SimpleDateFormat(getText("date.format")), true));
        initCustomBinder(request, binder);
    }

    protected void initCustomBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception
    {
        // Nothing - override in subclass if required
    }

    /**
     * Convenience method for getting a i18n key's value. Calling getMessageSourceAccessor() is used because the
     * RequestContext variable is not set in unit tests b/c there's no DispatchServlet Request.
     *
     * @param msgKey
     * @return
     */
    public String getText(String msgKey)
    {
        return StringUtils.trim(getMessageSourceAccessor().getMessage(msgKey));
    }

    /**
     * Convenient method for getting a i18n key's value with a single string argument.
     *
     * @param msgKey
     * @param arg
     * @return
     */
    public String getText(String msgKey, String arg)
    {
        return StringUtils.trim(getText(msgKey, new Object[] { arg }));
    }

    /**
     * Convenience method for getting a i18n key's value with arguments.
     *
     * @param msgKey
     * @param args
     * @return
     */
    public String getText(String msgKey, Object[] args)
    {
        return StringUtils.trim(getMessageSourceAccessor().getMessage(msgKey, args));
    }

    /**
     * Custom method invoked after bind and validate, to ensure that we do not commit with a modified command object
     * after validation errors occur. We need to do this or Hibernate will persist the command object if it loaded it,
     * even though there are validation errors.
     *
     */

    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws Exception
    {
        if (errors.getAllErrors().size() > 0) // there were binding/validation errors
        {
            request.setAttribute("validationError", Boolean.TRUE);
            log.debug("Set validation error attribute to TRUE in onBindAndValidate");
        }
    }

}
