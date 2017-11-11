package com.chemyoo.callback;

import java.util.concurrent.TimeUnit;


public class Programmer{
	
	//记下是XX项目经理,而不是其他项目经理
	Notice notice;
	/**程序员接受任务*/
	public void receiveTask(String task,Notice notice)
	{
		this.notice = notice;
		try {
			//程序员开始执行任务
			this.excuteTask(task);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**执行任务*/
	private void excuteTask(String task) throws InterruptedException
	{
		System.err.println("执行项目经理：安排的任务-->"+task);
		//任务执行中
		TimeUnit.SECONDS.sleep(1);
		//任务完成
		this.finished(task);
	}
	/**任务完成*/
	public void finished(String task)
	{
		//获取通知项目经理的方法,并发出通知
		notice.noticeMe("你好，你安排的任务"+task+"已经完成！");
	}
	
}
