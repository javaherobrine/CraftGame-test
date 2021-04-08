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
	public static <T> T[] except(T[]arr,int start,int end) {
		if(end<start) throw new IllegalArgumentException("end can't < start");
		int sub=end-start+1;
		int total=arr.length-sub;
		T[] result=(T[])Array.newInstance(arr[0].getClass(), total);
		for(int i=0;i<total;i++) {
			if(i<start) {
				result[i]=arr[i];
			}else {
				result[i]=arr[i+sub];
			}
		}
		return result;
	}
}
