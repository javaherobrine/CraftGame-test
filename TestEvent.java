import java.util.Map;
import io.github.javaherobrine.net.Client;
import io.github.javaherobrine.net.event.*;
public class TestEvent extends OtherEvent{
	{
		eid=Events.EVENTS_BEAN.nextEID();
		Events.EVENTS_BEAN.reg(getClass(),eid);
		content=new String[] {"²Ý2333"};
	}
	@Override
	public void sendExec(Client c) {
		System.out.println("send:"+((Object[])content)[0]);		
	}
	@Override
	public void recvExec() {
		System.out.println("event received");
		System.out.println("recv:"+((Object[])content)[0]);
	}
	@Override
	public Object initContent(Map map) {
		return map.get("0");
	}
}
