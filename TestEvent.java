import java.util.Map;
import io.github.javaherobrine.utils.*;
import io.github.javaherobrine.net.*;
import io.github.javaherobrine.net.event.*;
public class TestEvent extends OtherEvent{
	public TestEvent(Client c) {
		super(c);
	}
	{
		eid=Events.EVENTS_BEAN.nextEID();
		Events.EVENTS_BEAN.reg(getClass(),eid);
		content=new String[] {"²Ý2333"};
	}
	@Override 
	public void sendExec() {
		System.out.println("send:"+((Object[])content)[0]);		
	}
	public void recvExec() {
		System.out.println("event received");
		System.out.println("recv:"+((Object[])content)[0]);
	}
	@Override
	public Object initContent(Map map) {
		return ArrayUtils.cast(map.values().toArray(),String.class);
	}
}
