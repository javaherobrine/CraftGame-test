   package io.github.javaherobrine;
import javax.script.*;
import java.util.*;
import java.util.stream.*;
import java.lang.reflect.*;
public class JavaScript {
	public static final ScriptEngine engine=new ScriptEngineManager().getEngineByName("javascript");
	public static Map parse(String json) throws ScriptException {
		return (Map)engine.eval("json("+json+")");
	}
	static {
		try {
			engine.eval("function json(json){return json}");
		} catch (ScriptException e) {}
	}
	public static String json(Object object) {
		return json(object,new StringBuilder());
	}
	public static String json(Object object,StringBuilder sb) {
		if(object instanceof CharSequence) {
			sb.append("\""+object.toString()+"\"");
		}else if(object instanceof Object[]) {
			processArray((Object[])object,sb);
		}else {
			sb.append("{\n");
			Stream.of(object.getClass().getFields()).filter(field->{
			return !Modifier.isFinal(field.getModifiers())&&(Modifier.isStatic(field.getModifiers())||field.canAccess(object)||Modifier.isTransient(field.getModifiers()));
			}).forEach(field->{
				try {
					sb.append("\""+field.getName()+"\""+":");
					Object thisFie=field.get(object);
					if(thisFie!=null) {
						if(thisFie instanceof Object[]) {	
							processArray((Object[])thisFie,sb);
						}else if(thisFie instanceof Number) {
							sb.append(thisFie.toString()+",\n");
						}else if(thisFie instanceof CharSequence) {
							sb.append("\""+thisFie.toString()+",\"");
						}else {
							sb.append(json(thisFie)+",\n");
						}
					}else {
						sb.append("undefined,\n");
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {}
			});
			sb.delete(sb.length(),sb.length());
			sb.append("}");
		}
		return sb.toString();
	}
	private static void processArray(Object[] array,StringBuilder sb) {
		sb.append("[\n");
		Stream.of((Object[])array).forEach(o->{
			sb.append(json(o));
			sb.append(",");
		});
		sb.delete(sb.length()-1, sb.length());
		sb.append("\n],");
	}
}