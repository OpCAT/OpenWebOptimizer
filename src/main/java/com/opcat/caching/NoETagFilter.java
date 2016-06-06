package com.opcat.caching;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.opcat.util.HTTPCacheHeader;

/**
 * Filter responsible for disabling ETag header from the HTTP response.
 * 
 * @author OpCAT
 * @07-May-2013 com.opcat.caching
 */
public class NoETagFilter implements Filter {

	/**
	 * Place this filter into service.
	 * 
	 * @param filterConfig
	 *            the filter configuration object used by a servlet container to
	 *            pass information to a filter during initialization
	 * @throws ServletException
	 *             to inform the container to not place the filter into service
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * Disables ETag HTTP header.
	 * 
	 * @param servletRequest
	 *            provides data including parameter name and values, attributes,
	 *            and an input stream
	 * @param servletResponse
	 *            assists a servlet in sending a response to the client
	 * @param filterChain
	 *            gives a view into the invocation chain of a filtered request
	 */
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		filterChain.doFilter(servletRequest, new HttpServletResponseWrapper(
				(HttpServletResponse) servletResponse) {
			public void setHeader(String name, String value) {
				if (!HTTPCacheHeader.ETAG.getName().equalsIgnoreCase(name)) {
					super.setHeader(name, value);
				}
			}
		});
	}

	/**
	 * Take this filter out of service.
	 */
	public void destroy() {
	}
}
