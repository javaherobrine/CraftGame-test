package io.github.javaherobrine.utils;
import java.lang.reflect.*;
public final class ArrayUtils {
	private ArrayUtils() {}
	public static <T> T[] cast(Object[] objects,Class<T> castTo) throws ClassCastException{
		T[] objectss=(T[])Array.newInstance(castTo, objects.length);
		for(int i=0;i<objectss.length;i++) {
			objectss[i]=(T)objects[i];
		}
		return objectss;
	}
}
