package com.chemyoo.publics;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.chemyoo.privates.ExtendsTimeTask;

public class Scheduled {

	private static Logger logger = Logger.getLogger(Scheduled.class);
    private static SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    static{
    	logger.debug("父类编译");
    }
	/**
	 * @param args
	 */
	public Scheduled() {
		timerTask();
			logger.debug("父类");
	}
	public  void timerTask()
	{
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				logger.debug("当前时间："+sf.format(new GregorianCalendar().getTime()));
			}
		}, 0,1000*5);//立即执行，5秒执行一次,第二个参数new Date()或零
	}

}
