package com.chemyoo.test;

import com.chemyoo.callback.ProjectManager;

public class CallbackTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final ProjectManager prjMgr = new ProjectManager("王响");
		new Thread(){
			@Override
			public void run() {
				prjMgr.arrange("今晚完成数据库设计...");
			}
		}.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		prjMgr.doOtherWork();
		System.out.println("I'm working");
	}

}
