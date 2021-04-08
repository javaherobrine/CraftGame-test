import io.github.javaherobrine.net.*;
import io.github.javaherobrine.net.sync.*;
import java.io.*;
import java.net.*;
public class Test {
	public static void main(String[] args) throws IOException {
		Server s=new Server(new ServerSocket(8888));
		new Thread(()->{
			try {
				DefaultSynchronizeImpl ss=s.getImpl();
				new Thread(ss).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
		Client c=new Client(new Socket("localhost",8888));
		c.shakeHands();
		c.sendEvent(new TestEvent(c));
		/*
		c.close();
		s.close();
		*/
	}
}
