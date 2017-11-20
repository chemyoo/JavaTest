package com.chemyoo.test;


import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;

import com.chemyoo.pub.SecurityCode;
import com.chemyoo.utils.ChemyooUtils;
import com.chemyoo.utils.ChemyooUtils.TimeMonitor;
import com.chemyoo.utils.LocalMac;


public class Test
{
	private static Logger logger = Logger.getLogger(Test.class);
	
	volatile static List<String> keys = Collections.synchronizedList(new LinkedList<String>());
	
	public static void main(String[] args)
	{
		long arr[]= { 1,2,3,54,4,543,6,6,45,43,253,5432,24,535432,432,5325,34,54,5643,453,45,3543,65436,
				6456,57767,567,764,7657,755,756,75,7,654,76,7,4,7654,65,47,65,7,5,7,47,75,7,57,7 };
		sortArray(arr);
		Calendar cal = Calendar.getInstance();
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		logger.debug(hour);
		logger.debug(arr.toString());
		logger.debug(Arrays.toString(arr));
		double v[]=new double[4];
		logger.debug(Arrays.toString(v));
		Map<Object,Object> a=new HashMap<Object,Object>();
		a.put(1, 1);
		a.put(2, 1);
		a.put(3, 1L);
		long nn = (Long) a.get(3);
		logger.debug(a.size());
		getDisk("C");
		logger.debug(getDisk("D"));
		logger.debug(getRam());
		
		String code="444jjkjjfha8";
		
		logger.debug("密码长度:"+code.length());
//		logger.debug("加密算法:"+SecurityCode.encrypt(code));
//		logger.debug("加密算法:"+SecurityCode.encrypt(code));
		//logger.debug("加密后长度:"+SecurityCode.encrypt(code).length());
		Object ljx=909090;
		Object ljxsb=909090;
		logger.debug((ljx.equals(ljxsb)));
		StringBuffer sb=new StringBuffer("1,");
		logger.debug(sb.toString().substring(0, sb.toString().length()-1));	
		//122z61z30n15Q7z122E61i30z15l7o122z61s30I15z7w122v61z30u
		//122z61z30n15Q7z122E61i30z15l7o122z61s30I15z7w122v61z30u
		logger.info("message"+null);
		logger.info(0<<5);
		logger.info(7>>5);
		logger.info(Character.codePointAt(new char[]{'0'}, 0));
		logger.debug("加密算法:"+SecurityCode.encrypt(code));
		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -7);//7天前
		logger.debug(cal.getTime());
		String x = "0000253";
		System.err.println(x.substring(7, x.length()));
		class a{
			@Override
			public String toString()
			{
				return "toString";
			}
		};
		class b extends a{
			@Override
			public String toString()
			{
				return "toString";
			}
		};
		
		Object obj = new a();
		Object b = new b();
		try {
			System.err.println(ChemyooUtils.toString(obj));
			System.err.println(ChemyooUtils.toString(b));
			System.err.println(ChemyooUtils.toObject(obj,b).getClass());
			System.err.println(ObjectUtils.toString(4242));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		final CountDownLatch cdl = new CountDownLatch(10);
		for(int i=0;i<10;i++)
		{
			Thread th = new Thread() {
				public void run()
				{
					String key = ChemyooUtils.keyGenerator(false);
					if(keys.contains(key))
						System.err.println("the key is exsits!");
					else 
						keys.add(key);
					cdl.countDown();
				}
			};
			//th.setDaemon(false);
			th.start();
			
//			try {
//				th.join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
//		while(Thread.activeCount()>1)  //保证前面的线程都执行完
//		{
//            Thread.yield();
//		}
		try 
		{
			cdl.await();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		System.err.println(keys.size());
		System.err.println(keys);
		System.err.println(keys.get(0).length());
		System.out.println("本机Mac地址："+LocalMac.Mac.getInstanse());
		String separator = System.getProperty("line.separator");
		String aaa="use                 [khsl] "+separator+"GO"+separator+"fsajdkfljlk;a";
		aaa = aaa.replaceAll("^(USE|use)[ ]++\\[.*\\]", "");
		System.err.println(aaa);
		TimeMonitor timeMon1 = new ChemyooUtils.TimeMonitor();
		timeMon1.timeSatrt("A");
		TimeMonitor timeMon2 = new ChemyooUtils.TimeMonitor();
		timeMon2.timeSatrt("B");
			timeMon1.timeEnd();
			timeMon2.timeEnd();
	}
	private static void sortArray(long array[])
	{
		long start=System.nanoTime();
		long temp=0L;
		for(int i=0;i<array.length;i++)
			for(int j=i+1;j<array.length;j++)
			{
				if(array[i]>array[j])
				{
					temp=array[j];
					array[j]=array[i];
					array[i]=temp;
				}
			}
		logger.debug("sortArray内部运行用时:" + (System.nanoTime()-start)+"nm");
		Map a=new HashMap();
		Map b=new HashMap();
		Map c=new HashMap();
		a.put(1, 1);
		b.put(2,2);
		c.put(3,3);
		List<Map>list = new ArrayList<Map>();
		list.add(a);list.add(b);
		for(Map m:list)
		{
			list.remove(m);
		}
		logger.debug("sortArray内部运行用时:" + (System.nanoTime()-start)+"nm");
	}
	public static String getDisk(String disk){
		File win = new File(disk+":/");
		long total=(long)win.getTotalSpace();
        long free=(long)win.getFreeSpace();
        String str=disk+": 盘总空间 "+(total/1024/1024/1024)+"GB，剩余空间  "+(free/1024F/1024F/1024F)+"GB"; 	
        return str;
	}
	/**获取物理内存*/
	public static String getRam(){
		OperatingSystemMXBean osmxb = ManagementFactory.getOperatingSystemMXBean();
				
		// 总的物理内存
		osmxb.getVersion();
		//long totalPhysicalMemory = osmxb.getTotalPhysicalMemorySize();
		
		// 剩余的物理内存
		//long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
		  
//		Double compare=(Double)(1-freePhysicalMemorySize*1.0/totalPhysicalMemory)*100;
//		String str="物理总内存："+(totalPhysicalMemory/1024/1024)+"MB，已使用"+
//				((totalPhysicalMemory-freePhysicalMemorySize)/1024/1024)+"MB，内存已使用:"+
//				compare.intValue()+"%";
		return "硬盘版本号："+osmxb.getVersion();
	}
}