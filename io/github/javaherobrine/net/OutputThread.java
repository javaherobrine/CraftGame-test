package io.github.javaherobrine.net;
import java.io.*;
@Deprecated
public class OutputThread extends Thread implements Closeable,AutoCloseable{
	TCPOutputStream out;
	byte[] data=null;
	boolean live=true;
	@Override
	public void close() throws IOException {
		out.close();
		live=false;
	}
	public OutputThread(TCPOutputStream out) {
		this.out=out;
	}
	@Override
	public void run() {
		while(live) {
			try {
				sleep(Long.MAX_VALUE);
				try {
					while(data==null);
					out.writeData(data);
					data=null;
				} catch (IOException e) {
				}
			} catch (InterruptedException e) {
			}
		}
	}
	public void write(byte[] bs) {
		data=bs;
		interrupt();
	}
}