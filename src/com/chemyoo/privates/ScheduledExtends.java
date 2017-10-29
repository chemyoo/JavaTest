package com.chemyoo.privates;

import org.apache.log4j.Logger;

import com.chemyoo.publics.Scheduled;

public class ScheduledExtends  extends Scheduled{

	private static Logger logger = Logger.getLogger(Scheduled.class);
	
	 static{
	    	logger.debug("子类编译");
	    }
	public ScheduledExtends()
	{
		logger.debug("Do something what u want to do!");
		//timerTask();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new ScheduledExtends();
		ExtendsTimeTask task=new ExtendsTimeTask();
		task.run();
		System.err.println(85943825);
	}

}
