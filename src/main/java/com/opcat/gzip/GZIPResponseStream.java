package com.opcat.gzip;

import java.io.*;
import java.util.zip.GZIPOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author OpCAT
 * @07-May-2013 com.opcat.gzip
 */
public class GZIPResponseStream extends ServletOutputStream {
	protected ByteArrayOutputStream baos;
	protected GZIPOutputStream gzipstream;
	protected boolean closed;
	protected HttpServletResponse response;
	protected ServletOutputStream output;

	public GZIPResponseStream(HttpServletResponse httpservletresponse)
			throws IOException {
		baos = null;
		gzipstream = null;
		closed = false;
		response = null;
		output = null;
		closed = false;
		response = httpservletresponse;
		output = httpservletresponse.getOutputStream();
		baos = new ByteArrayOutputStream();
		gzipstream = new GZIPOutputStream(baos);
	}

	public void close() throws IOException {
		if (closed) {
			throw new IOException("This output stream has already been closed");
		} else {
			gzipstream.finish();
			byte abyte0[] = baos.toByteArray();
			response.addHeader("Content-Length",
					Integer.toString(abyte0.length));
			response.addHeader("Content-Encoding", "gzip");
			output.write(abyte0);
			output.flush();
			output.close();
			closed = true;
			return;
		}
	}

	public void flush() throws IOException {
		if (closed) {
			throw new IOException("Cannot flush a closed output stream");
		} else {
			gzipstream.flush();
			return;
		}
	}

	public void write(int i) throws IOException {
		if (closed) {
			throw new IOException("Cannot write to a closed output stream");
		} else {
			gzipstream.write((byte) i);
			return;
		}
	}

	public void write(byte abyte0[]) throws IOException {
		write(abyte0, 0, abyte0.length);
	}

	public void write(byte abyte0[], int i, int j) throws IOException {
		//System.out.println("writing...");
		if (closed) {
			throw new IOException("Cannot write to a closed output stream");
		} else {
			gzipstream.write(abyte0, i, j);
			return;
		}
	}

	public boolean closed() {
		return closed;
	}

	public void reset() {
	}


}
