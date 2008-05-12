package org.ussa.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.ussa.web.filter.SavedUrlFilter;

/**
 * Per MEMWEB-218 this servlet exists to redirect the url in a way that the Acegi AuthenticationProcessingFilter is supposed to
 * when alwaysUseDefaultTargetUrl is not provided or is false.  It is very likely that since the processing filter is a Crowd
 * implementation that the defaultTargetUrl is ignored or overwritten.  Since the source for crowd is not available, this can 
 * neither be confirmed nor denied and is the reason for this servlet / filter work around.  See org.ussa.web.filter.SessionMonitorFilter.
 */
public class SavedUrlServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (req.getSession().getAttribute(SavedUrlFilter.SAVED_URI_KEY) != null) {
			// redirect to *original* URL
			String uri = (String)req.getSession().getAttribute(SavedUrlFilter.SAVED_URI_KEY);
			String queryParams = (String)req.getSession().getAttribute(SavedUrlFilter.SAVED_QUERY_STRING_KEY);
			
			StringBuffer url = new StringBuffer(uri);
			if (StringUtils.isNotBlank(queryParams)) {
				url.append("?").append(queryParams);
			}
			
			req.getSession().setAttribute(SavedUrlFilter.SAVED_URI_KEY, null);
			req.getSession().setAttribute(SavedUrlFilter.SAVED_QUERY_STRING_KEY, null);
			
			resp.sendRedirect(url.toString());
		} else {
			// Forward on subsequent requests
			req.getRequestDispatcher(req.getRequestURL().toString()).forward(req, resp);
		}
		
	}
}
