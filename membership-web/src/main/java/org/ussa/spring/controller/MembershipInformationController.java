package org.ussa.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class MembershipInformationController extends AbstractController
{
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        System.out.println("Hello from Membership Information Controller");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        if (exception != null)
        {
            throw exception;
        }
        return new ModelAndView("MembershipInformation");
    }

}
