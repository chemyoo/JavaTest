package com.chemyoo.privates;

import java.util.GregorianCalendar;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.chemyoo.publics.Scheduled;

public class ExtendsTimeTask extends TimerTask{

	private static Logger logger = Logger.getLogger(ExtendsTimeTask.class);

	@Override
	public void run() {
		logger.debug("输出信息"+(new GregorianCalendar().getTime()));
		scheduledExecutionTime();
	}

}
