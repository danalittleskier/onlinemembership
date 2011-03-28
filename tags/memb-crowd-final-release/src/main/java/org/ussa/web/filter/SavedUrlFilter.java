package org.ussa.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Workaround for Crowd / Acegi integration issue where authentication fails when first entering membership causing the defaultTargetURL of 
 * the authenticationProcessingFilter to be fired rather than the requested URL even though alwaysUseDefaultTargetUrl is not set.
 * 
 * See org.ussa.web.servlet.WebRedirectServlet for additional info.
 */
public class SavedUrlFilter implements Filter {
	public static final String SESSION_ACTIVATED = "sessionActivated";
	public static final String SAVED_URI_KEY = "savedURI";
	public static final String SAVED_QUERY_STRING_KEY = "savedQueryString";
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();
		Boolean sessionActivated = (Boolean)session.getAttribute(SESSION_ACTIVATED);
		
		if (sessionActivated == null || sessionActivated.equals(Boolean.FALSE)){
			session.setAttribute(SAVED_URI_KEY, httpRequest.getRequestURI());
			session.setAttribute(SAVED_QUERY_STRING_KEY, httpRequest.getQueryString());
			session.setAttribute(SESSION_ACTIVATED, Boolean.TRUE);
		}

		chain.doFilter(request, response);
	}		

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}
}
