package com.opcat.etag;

import com.opcat.util.HTTPCacheHeader;
import com.opcat.util.MD5Util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/6/6.
 */
public class ETagFilter implements Filter{
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ETagResponseWrapper eTagResponseWrapper = new ETagResponseWrapper(response, outputStream);
        filterChain.doFilter(request, eTagResponseWrapper);
        eTagResponseWrapper.finishResponse();
        byte[] bytes = outputStream.toByteArray();
        String token = '"' + MD5Util.getMd5Str(bytes) + '"';
        String previousToken = request.getHeader(HTTPCacheHeader.IF_NONE_MATCH.getName());
        if (previousToken != null && previousToken.equals(token)){
            response.setHeader(HTTPCacheHeader.ETAG.getName(), token);
            response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
            response.setHeader(HTTPCacheHeader.LAST_MODIFIDE.getName(), request.getHeader(HTTPCacheHeader.IF_MODIFIED_SINCE.getName()));
        }else{
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MILLISECOND, 0);
            Date lastModified = cal.getTime();
            response.setDateHeader(HTTPCacheHeader.LAST_MODIFIDE.getName(), lastModified.getTime());
            response.setContentLength(bytes.length);
            ServletOutputStream sos = response.getOutputStream();
            sos.write(bytes);
            sos.flush();
            sos.close();
        }
    }

    public void destroy() {

    }
}
