import io.github.javaherobrine.net.*;
import io.github.javaherobrine.net.event.*;
import io.github.javaherobrine.net.sync.*;
import java.io.*;
import java.net.*;
public class Test {
	public static void main(String[] args) throws IOException {
		Server s=new Server(new ServerSocket(8888));
		new Thread(()->{
			try {
				ClientSideSynchronizeImpl.ServertSideSynchronizeImpl ss=Server.thisServer.getImpl();
				System.out.println(ss);
				new Thread(ss).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
		Client c=new Client(new Socket("localhost",8888),false);
		c.shakeHands();
		c.sendEvent(new EventObject(new TestEvent()));
		/*
		c.close();
		s.close();
		*/
	}
}
