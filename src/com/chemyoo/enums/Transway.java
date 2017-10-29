package com.chemyoo.enums;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map.Entry;

public enum Transway  {
	/**Car*/
	Car,Bus,Train,Flight,Boat,Motor,WordYu(3);
	
	Transway(){};
	
	private  int id;
	
	Transway(int id)
	{
		this.id=id;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(Transway trans : values())
		{
			System.err.println(trans.toString());
			System.err.println(trans.ordinal());
			System.err.println(trans.compareTo(Flight));
		}
		 EnumSet<Transway> transSet = EnumSet.allOf(Transway.class);
	        for (Transway trans : transSet) {
	            System.out.println(trans);
	        }
	        
	        EnumMap<Transway, String> weekMap = new EnumMap<Transway, String>(Transway.class);
	        weekMap.put(Transway.Car, "汽车");
	        weekMap.put(Transway.Flight, "飞机");
	        for (Iterator<Entry<Transway, String>> iter = weekMap.entrySet().iterator(); iter.hasNext();) {
	            Entry<Transway, String> entry = iter.next();
	            System.out.println(entry.getKey().name() + ":" + entry.getValue());
	        }
	        System.setProperty("log4j.dir", "自定义属性");
	        String log4jdir = System.getProperty("log4j.dir");
	        System.err.println(log4jdir);
	}
	

}
