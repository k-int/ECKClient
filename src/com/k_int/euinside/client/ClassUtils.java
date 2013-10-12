package com.k_int.euinside.client;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ClassUtils {

	static public String toString(Object instance) { 
		return(toString(instance, Object.class));
	}

	static public String toString(Object instance, Class<?> parentLimit) { 
		return(toString(instance, parentLimit, 0));
	}

	static public String toString(Object instance, Class<?> parentLimit, int indent) { 
		return(toString(instance.getClass(), instance, parentLimit, indent));
	}

	static private void indentString(StringBuffer buffer, int indent) {
		for (int i = 0; i < indent; i++) {
			buffer.append("\t");
		}
	}

	static private void outputString(StringBuffer buffer, String label, String value, int indent) {
		indentString(buffer, indent);
		buffer.append(label);
		buffer.append(": ");
		if (value != null) {
			buffer.append(value);
		}
		buffer.append("\n");
	}
	
	static private String toString(Class<?> classDetails, Object instance, Class<?> parentLimit, int indent) {
		StringBuffer buffer = new StringBuffer();
		outputString(buffer, "Class name", classDetails.getSimpleName(), indent);
		Class<?> superClass = classDetails.getSuperclass();
		if (!parentLimit.equals(superClass)) {
			buffer.append(toString(superClass, instance, parentLimit, indent + 1));
		}
		
		Field[] fields = classDetails.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			String methodName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1); 
			try {
				Method getMethod = classDetails.getMethod(methodName);
				try {
					Object value = getMethod.invoke(instance);
					if (value != null) {
						Class<?>[] interfaces = fields[i].getType().getInterfaces();
						boolean isList = false;
						if (interfaces != null) {
							for (int j = 0; (j < interfaces.length) && !isList; j++) {
								isList = interfaces[j].getName().equals("java.util.List") ||
										 interfaces[j].getName().equals("java.util.Collection");
							}
						}
						
						if (isList) {
							int count = 0;
							for (Object listItem : (List<?>)value) {
								outputString(buffer, fieldName + "[" + count + "]", null, indent);
								buffer.append(((ClassUtilsToString)listItem).toString(indent + 1));
								count++;
							}
						} else {
							outputString(buffer, fieldName, value.toString(), indent);
						}
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//					System.out.println("Error invoking get method");
				}
			} catch (NoSuchMethodException | SecurityException e) {
//				System.out.println("Unable to access method");
			}
		}
		return(buffer.toString());
	}
}
