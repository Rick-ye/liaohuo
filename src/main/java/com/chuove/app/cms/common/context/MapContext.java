package com.chuove.app.cms.common.context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class  MapContext<T>implements Context<String, T>{
	private Map<String, T> context = new HashMap<String, T>();
	
	public Map<String, T> toMap()
	{
	  return this.context;
	}
	
	public T get(String name)
	{
	  int i = name.indexOf(".");
	  if (i >= 0)
	  {
	    String n = name.substring(0, i);
	    T obj = this.context.get(n);
	    if ((obj != null) && ((obj instanceof Context)))
	    {
	      Context<String, T> mc = (Context)obj;
	      return (T)mc.get(name.substring(i + 1));
	    }
	  }
	  return (T)this.context.get(name);
	}
	
	public void set(String name, T value)
	{
	  this.context.put(name, value);
	}
	
	public Float getFloat(String name)
	{
	  Object obj = get(name);
	  if (obj == null) {
	    return null;
	  }
	  if ((obj instanceof Float)) {
	    return (Float)obj;
	  }
	  if (((obj instanceof String)) && (!obj.equals(""))) {
	    return Float.valueOf((String)obj);
	  }
	  return null;
	}
	
	public String getString(String name)
	{
	  Object obj = get(name);
	  if (obj == null) {
	    return null;
	  }
	  return obj.toString();
	}
	
	public Integer getInt(String name)
	{
	  Object obj = get(name);
	  if (obj == null) {
	    return null;
	  }
	  if ((obj instanceof Integer)) {
	    return (Integer)obj;
	  }
	  if (((obj instanceof String)) && (!obj.equals(""))) {
	    return Integer.valueOf((String)obj);
	  }
	  return null;
	}
	
	public Double getDouble(String name)
	{
	  Object obj = get(name);
	  if (obj == null) {
	    return null;
	  }
	  if ((obj instanceof Double)) {
	    return (Double)obj;
	  }
	  if (((obj instanceof String)) && (!obj.equals(""))) {
	    return Double.valueOf((String)obj);
	  }
	  return null;
	}
	
	public Long getLong(String name)
	{
	  Object obj = get(name);
	  if (obj == null) {
	    return null;
	  }
	  if ((obj instanceof Long)) {
	    return (Long)obj;
	  }
	  if (((obj instanceof String)) && (!obj.equals(""))) {
	    return Long.valueOf((String)obj);
	  }
	  return null;
	}
	
		public Date getDate(String name)
	{
	  Object obj = get(name);
	  if (obj == null) {
	    return null;
	  }
	  if ((obj instanceof Long)) {
	    return new Date(((Long)obj).longValue());
	  }
	  if ((obj instanceof Date)) {
	    return (Date)obj;
	  }
	  return null;
	}
	
	public Boolean getBoolean(String name)
	{
	  Object obj = get(name);
	  if (obj == null) {
	    return null;
	  }
	  if ((obj instanceof Boolean)) {
	    return (Boolean)obj;
	  }
	  if ((obj instanceof String)) {
	    return Boolean.valueOf((String)obj);
	  }
	  return null;
	}
	
	public void clear()
	{
	  this.context.clear();
	}
	}
