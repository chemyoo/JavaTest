package com.chemyoo.test;

import com.chemyoo.callback.ProjectManager;

public class CallbackTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ProjectManager prjMgr = new ProjectManager("王响");
		prjMgr.arrange("今晚完成数据库设计...");
		prjMgr.doOtherWork();
	}

}
