package com.opcat.etag;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/6/6.
 */
public class ETagResponseStream extends ServletOutputStream{
    private OutputStream outputStream;
    private boolean closed = false;

    public ETagResponseStream(OutputStream outputStream) {
        super();
        this.outputStream = outputStream;
    }
    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
    }
    @Override
    public void write(byte b[]) throws IOException {
        write(b, 0, b.length);
    }
    @Override
    public void flush() throws IOException {
        if(!closed){
            outputStream.flush();
        }
    }

    @Override
    public void close() throws IOException {
        if (!closed) {
            outputStream.close();
            closed = true;
        }
    }
    public boolean closed(){
        return closed;
    }
}
