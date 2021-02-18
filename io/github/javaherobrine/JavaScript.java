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
		return json(object,new StringBuilder(),object.getClass());
	}
	public static String json(Object object,StringBuilder sb,Class clazz) {
		if(object instanceof CharSequence) {
			sb.append("\""+object.toString()+"\"");
		}else if(object instanceof Object[]) {
			sb.append("[\n");
			Stream.of((Object[])object).forEach(o->{
				if(o instanceof Object[]) {
					sb.append(json(o));
					sb.append(",");
				}else {
					sb.append(json(o));
					sb.append(",");
				}
			});
			sb.delete(sb.length()-2, sb.length()-1);
			sb.append("\n]");
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
							Stream.of((Object[])thisFie).forEach(o->{
								json(o);
							});
						}else if(thisFie instanceof Number) {
							sb.append(thisFie.toString()+",\n");
						}else if(thisFie instanceof CharSequence) {
							sb.append("\""+thisFie.toString()+",\"");
						}else {
							sb.append("\""+json(thisFie)+",\n");
						}
					}else {
						sb.append("undefined,\n");
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {}
			});
		}
		sb.delete(sb.length()-2,sb.length()-2);
		sb.append("}");
		return sb.toString();
	}
}