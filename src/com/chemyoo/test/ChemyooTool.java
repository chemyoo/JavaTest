package com.chemyoo.test;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;


public class ChemyooTool {
	
	private static Logger log= Logger.getLogger(ChemyooTool.class);
	
	public void a()
	{
		log.info(" a method ");
	}
	public  void b()
	{
		log.info(" b method ");
	}
}
 class Control {
    public void invoke(int flag) {
        User user = new User();
        try {
            switch (flag) {
            case 0:
                user.use(ChemyooTool.class.getMethod("a", null));
                break;
            default:
                user.use(ChemyooTool.class.getMethod("b", null));
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
}
 
