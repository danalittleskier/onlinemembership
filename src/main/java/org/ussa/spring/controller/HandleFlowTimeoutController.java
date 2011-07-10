package org.ussa.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class HandleFlowTimeoutController extends AbstractController {
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<String> errorMessages = new ArrayList<String>();
		errorMessages.add(getApplicationContext().getMessage("error.flow.timeout", null, request.getLocale()));
		request.getSession().setAttribute("errorMessages", errorMessages);
		
		return new ModelAndView("registrationErrorNoFlow");
	}
}
