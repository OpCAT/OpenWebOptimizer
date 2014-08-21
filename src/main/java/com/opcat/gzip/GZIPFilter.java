package com.opcat.gzip;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author OpCAT
 * @07-May-2013 com.opcat.gzip
 */
public class GZIPFilter implements Filter {

	public GZIPFilter() {
	}

	public void doFilter(ServletRequest servletrequest,
			ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		if (servletrequest instanceof HttpServletRequest) {
			HttpServletRequest httpservletrequest = (HttpServletRequest) servletrequest;
			HttpServletResponse httpservletresponse = (HttpServletResponse) servletresponse;
			String s = httpservletrequest.getHeader("accept-encoding");
			if (s != null && s.indexOf("gzip") != -1) {
				//System.out.println("GZIP supported, compressing.");
				GZIPResponseWrapper gzipresponsewrapper = new GZIPResponseWrapper(
						httpservletresponse);
				filterchain.doFilter(servletrequest, gzipresponsewrapper);
				gzipresponsewrapper.finishResponse();
				return;
			}
			filterchain.doFilter(servletrequest, servletresponse);
		}
	}

	public void init(FilterConfig filterconfig) {
	}

	public void destroy() {
	}
}
