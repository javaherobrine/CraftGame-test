import java.util.Map;
import io.github.javaherobrine.net.Client;
import io.github.javaherobrine.net.event.*;
public class TestEvent extends OtherEvent{
	{
		eid=Events.EVENTS_BEAN.nextEID();
		Events.EVENTS_BEAN.reg(getClass(),eid);
		content=new String[] {"��"};
	}
	@Override
	public void sendExec(Client c) {
		System.out.println("send:"+((Object[])content)[0]);		
	}
	@Override
	public void recvExec() {
		System.out.println("recv:"+((Object[])content)[0]);
	}
	@Override
	public Object initContent(Map map) {
		return map.get("0");
	}
}
