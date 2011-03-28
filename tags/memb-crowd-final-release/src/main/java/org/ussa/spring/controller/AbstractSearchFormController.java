package org.ussa.spring.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractSearchFormController extends AbstractSimpleFormController
{
    public final ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception
    {
        ModelAndView result = null;
        Map<String, Object> model = this.referenceData(request);
        model.put(getCommandName(), command);
        String viewName = performSearch(request, response, command, errors, model);
        if (errors.getErrorCount() > 0)
        {
            result = showForm(request, response, errors, null);
        } else
        {
            result = new ModelAndView(viewName, model);
        }
        return result;
    }

    public abstract String performSearch(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors, Map model) throws Exception;
}
