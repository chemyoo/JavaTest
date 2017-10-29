package com.chemyoo.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class User
 {
     public void use(Method method)
     {
    	 ChemyooTool tool = new ChemyooTool();
         try {
             method.invoke(tool, null);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
  
     public static void main(String[] args){
         Control control = new Control();
         control.invoke(0);
         Integer a [] ={0,1,2,3,4,5,6,11};
         Integer [] b = {};
         List<Integer> list = Arrays.asList(a);
         List<Integer> list2 = new ArrayList<Integer>();
         list2.addAll(Arrays.asList(b));
         
         list2.removeAll(list);
         list.removeAll(list2);
         list.addAll(new ArrayList<Integer>());
         list2.addAll(list);
         System.out.println(list2);
//			List<String> list = criteria.getStcds();
//			if(!(list instanceof Arrays) && !(list instanceof ArrayList))
//			{
//				String val = list.get(0);
//				list = new ArrayList<String>();
//				list.add(val);
//			}
//			list.removeAll(stcdlist);//取得差集
//			list.addAll(stcdlist);//求得无重复并集
//			//list.retainAll(stcdlist);//求交集

         System.out.println(list2 instanceof ArrayList);
         System.out.println(list instanceof ArrayList);
         System.out.println(list instanceof Arrays);
         System.out.println(list2 instanceof Arrays);
         System.out.println(list.getClass());
         System.out.println(list2.getClass());
         
         MessageApplet applet = new MessageApplet();
         applet.setMsg("我去马勒戈壁");
         
         try
         {
	         URL url = new URL("http://www.hik-online.com/shebuswz");
	         URLConnection URLconnection = url.openConnection();
	         HttpURLConnection httpConnection = (HttpURLConnection) URLconnection;
	         int responseCode = httpConnection.getResponseCode();
	         if (responseCode == HttpURLConnection.HTTP_OK) {
	             System.err.println("成功");   
	             InputStream in = httpConnection.getInputStream();
	             InputStreamReader isr = new InputStreamReader(in);
	             BufferedReader bufr = new BufferedReader(isr);
	             String str;
	             while ((str = bufr.readLine()) != null) {
	                 //System.out.println(str);
	            	 if(str.contains("var redirectUrl") ||str.contains("http://"))
	                 {
	            		 str = str.replace("var redirectUrl", "").replace("=", "").trim().replace("\"", "'").replace("'","");
	            		 int index = str.indexOf(";");
	                	 if(index>-1&&index==str.length()-1)
	                		 str = str.substring(0, str.indexOf(";"));
	                	 break;
	                 }
	             }
	             System.out.println(str);
	             bufr.close();
         } else {
             System.err.println("失败");
         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
        String stcds = "09900001,10000003,10000004,12345678,31080001,36070002,38070001,39070001,39070002," +
        		"39070003,39070004,43070001,44070002,45070001,45070002,90210000,90220000,99000002";
        String stcd [] = stcds.split(",");
        String dir = "D:/";
        Date date = null ;
        for(String cd : stcd)
        {
			FileOutputStream fos =null;
			ObjectOutputStream out = null;
			try
			{
				File file = new File(dir+"/availabletimes");
				if(!file.exists())
				{
						file.mkdir();
				}
				file = new File(dir+"/availabletimes/available"+cd+".obj");
				if(!file.exists())
				{
						file.createNewFile();
				}
				fos = new FileOutputStream(file);
				out = new ObjectOutputStream(fos);
				GregorianCalendar grego = new GregorianCalendar();
				grego.add(GregorianCalendar.MONTH,-1);//1个月前的时间
				date = grego.getTime();
				out.writeObject(date);    //写入Date对象
//				operation.setCzjg(0);
//				operation.setStcd(stcd);
//				operation.setCzlx(0x20);
//				operation.setReq6(7);
//				Integer num = sendCmdService.saveCmdAndPushMessage(operation,null);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
			finally
			{
				 try {
					 if(out !=null)
					 {
						 out.flush();out.close();
					 }
					 if(fos !=null)
					 {
						 fos.flush();fos.close();
					 }
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
        }
     }
 }
