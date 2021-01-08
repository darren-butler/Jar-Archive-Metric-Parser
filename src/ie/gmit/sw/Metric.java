package ie.gmit.sw;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Metric represents metadata for a single class. It is intended that an instance of JarMetric have a list of Metric objects. 
 * This list would represent metric data for every class in the jar.
 * @author Darren
 *
 */
public class Metric {
	private String className;
	private boolean isInterface;
	private Field[] fields;
	private Method[] methods;

	public Metric(String className, boolean isInterface, Field[] fields, Method[] methods) {
		this.className = className;
		this.isInterface = isInterface;
		this.fields = fields;
		this.methods = methods;
	}

	@Override
	public String toString() {
		String str = "\n" + className + "\nField Count: " + fields.length + "\tMethod Count: " + methods.length;
		return str;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public boolean isInterface() {
		return isInterface;
	}

	public void setInterface(boolean isInterface) {
		this.isInterface = isInterface;
	}

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}

	public Method[] getMethods() {
		return methods;
	}

	public void setMethods(Method[] methods) {
		this.methods = methods;
	}

}
