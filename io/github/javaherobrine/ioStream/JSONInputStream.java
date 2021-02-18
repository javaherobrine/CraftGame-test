package io.github.javaherobrine.ioStream;
import java.io.*;
import io.github.javaherobrine.*;
import javax.script.*;
public class JSONInputStream implements ObjectInput{
	private BufferedReader source;
	public JSONInputStream(Reader source) {
		this.source=new BufferedReader(source);
	}
	@Override
	public Object readObject() throws IOException{
		StringWriter sw=new StringWriter();
		String str=source.readLine();
		if(str!=null&&str.trim().isEmpty()) {
			return readObject();
		}
		if(str!=null&&str.indexOf('}')==str.length()-1) {
			try {
				return JavaScript.parse(str);
			} catch (ScriptException e) {
				throw new IOException(e);
			}
		}
		sw.write(str+"\n");
		while(!"}".equals(str=source.readLine())) {
			if(str!=null)
				sw.write(str);
			else
				break;
		}
		sw.write("}");
		try {
			return JavaScript.parse(sw.toString());
		} catch (ScriptException e) {
			throw new IOException(e);
		}
	}
	public void readFully(byte[] b) throws IOException {}
	public void readFully(byte[] b, int off, int len) throws IOException {}
	public int skipBytes(int n) throws IOException {
		return 0;
	}
	public boolean readBoolean() throws IOException {
		return false;
	}
	public byte readByte() {
		return 0;
	}
	public int readUnsignedByte() throws IOException {
		return 0;
	}
	public short readShort() throws IOException {
		return 0;
	}
	public int readUnsignedShort() throws IOException {
		return 0;
	}
	public char readChar() throws IOException {
		return 0;
	}
	public int readInt() throws IOException {
		return 0;
	}
	public long readLong() throws IOException {
		return 0;
	}
	public float readFloat() throws IOException {
		return 0;
	}
	public double readDouble() throws IOException {
		return 0;
	}
	public String readLine() throws IOException {
		return null;
	}
	public String readUTF() throws IOException {
		return null;
	}
	public int read() throws IOException {
		return 0;
	}
	public int read(byte[] b) throws IOException {
		return 0;
	}
	public int read(byte[] b, int off, int len) throws IOException {
		return 0;
	}
	public long skip(long n) throws IOException {
		return 0;
	}
	public int available() throws IOException {
		return 0;
	}
	public void close() throws IOException {
		source.close();
	}
}