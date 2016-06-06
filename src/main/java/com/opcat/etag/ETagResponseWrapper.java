package com.opcat.etag;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 * Created by Administrator on 2016/6/6.
 */
public class ETagResponseWrapper extends HttpServletResponseWrapper{
    private ServletOutputStream servletOutputStream;
    private PrintWriter printWriter;

    public ETagResponseWrapper(HttpServletResponse response, OutputStream outputStream) throws UnsupportedEncodingException {
        super(response);
        this.servletOutputStream = new ETagResponseStream(outputStream);
        this.printWriter = new PrintWriter(new OutputStreamWriter(servletOutputStream, "UTF-8"));
    }
    @Override
    public PrintWriter getWriter() throws IOException {
        return printWriter;
    }
    @Override
    public void flushBuffer() throws IOException{
            servletOutputStream.flush();
            printWriter.flush();
    }
    public void finishResponse() throws IOException {
        flushBuffer();
        servletOutputStream.close();
        printWriter.close();
    }
    @Override
    public ServletOutputStream getOutputStream(){
        return servletOutputStream;
    }
}
