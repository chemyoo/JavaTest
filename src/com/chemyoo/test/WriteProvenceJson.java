package com.chemyoo.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;

public class WriteProvenceJson {
	
//	 Object object = new Object();
//	 
//	 public  void  doWait()
//	 {
//		 synchronized (object) {
//			 try {
//				 long start = Calendar.getInstance().getTimeInMillis();
//				 object.wait();
//				 System.err.println((Calendar.getInstance().getTimeInMillis()-start)+"ms");
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	 }
//	 
//	 public void  endWait()
//	 {
//		 synchronized (object) {
//			 object.notify();
//		 }
//	 }

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String apiUrl = String.format("https://raw.githack.com/chemyoo/cssstore/master/area.json");
		final Object object = new Object();
		//final CountDownLatch cdl = new CountDownLatch(1);
		//final WriteProvenceJson p = new WriteProvenceJson();
		URL url;
		try {
			url = new URL(apiUrl);
				new Thread(){
					@Override
					public void run() {
						try {
							//long start = Calendar.getInstance().getTimeInMillis();
							//cdl.await();
							//System.err.println((Calendar.getInstance().getTimeInMillis()-start)+"ms");
							//p.doWait();
							synchronized(object){
								Date date = Calendar.getInstance().getTime();
								System.err.println("开始时间："+date);
								long start = date.getTime();
								object.wait();
								date = Calendar.getInstance().getTime();
								System.err.println("结束时间："+date);
								System.err.println((date.getTime()-start)+"ms");
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}.start();
			HttpURLConnection httpConnection = (HttpURLConnection)  url.openConnection();
	         int responseCode = httpConnection.getResponseCode();
	         if (responseCode == HttpURLConnection.HTTP_OK) {
	        	 InputStream input = httpConnection.getInputStream();
	        	 System.err.println("执行开始！");
	 			//这里转换为String，带上包名，怕你们引错包
	 			String result = IOUtils.toString(input,"utf-8");
	 			//JSONObject json = JSONObject.fromObject(result);
	 			JSONArray json = JSONArray.fromObject(result);
	 			JSONObject provence = null;
	 			JSONArray array = new JSONArray();
	 			String name = null;
	 			String code = "";
	 			String parent_code = null;
	 			Iterator<JSONObject> it = json.iterator();
	 			while(it.hasNext())
	 			{
	 				provence = it.next();
	 				name = provence.getString("name");
	 				System.err.println(name);
	 				if(!"市辖区".equals(name.trim()) && !"市辖县".equals(name.trim()))
					{
	 					if(code.equals(provence.getString("parent_code")))
	 					{
	 						provence.remove("parent_code");
	 						//provence.putOpt(key, value)
	 						provence.put("parent_code", parent_code);
	 					}
	 					array.put(provence);
					}
	 				else
	 				{
	 					code = provence.getString("code");
		 				parent_code = provence.getString("parent_code");
	 				}
	 			}
	 			
//	 			Iterator<String> it = json.keys();
//	 			String key = null;
//	 			while(it.hasNext())
//	 			{
//	 				key = it.next();
//	 				System.err.println(key+":"+json.get(key));
//	 				if(key.endsWith("0000"))
//	 				{
//	 					provence = new JSONObject();
//	 					provence.accumulate("code", key.substring(0, 2)).accumulate("name", json.get(key)).accumulate("parent_code", "");
//	 					array.put(provence);
//	 				}
//	 				else if(key.endsWith("00"))
//	 				{
//	 					provence = new JSONObject();
//	 					provence.accumulate("code", key.substring(0, 4)).accumulate("name", json.get(key)).accumulate("parent_code", key.substring(0, 2));
//	 					array.put(provence);
//	 				}
//	 				else
//	 				{
//	 					provence = new JSONObject();
//	 					provence.accumulate("code", key).accumulate("name", json.get(key)).accumulate("parent_code", key.substring(0, 4));
//	 					array.put(provence);
//	 				}
//	 			}
//	 			//加上这句话:System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");  
//	            //表示，使用以前版本(1.6)的sort来排序  
//	 			List<JSONObject> list = JSONArray.toList(array,JSONObject.class);
//	 			Collections.sort(list, new Comparator<JSONObject>() {
//					@Override
//					public int compare(JSONObject o1, JSONObject o2) {
//						String o1_id = o1.getString("code");
//						String o2_id = o2.getString("code");
//						return o1_id.compareTo(o2_id);
//					}
//				});
	 			
	 			File filePath = new File("D:\\provences.json");
	 			
	 			if(!filePath.exists())
	 				filePath.createNewFile();
	 			FileWriter fw = new FileWriter(filePath);
	 		    PrintWriter out = new PrintWriter(fw);
	 		    out.write(array.toString().replace("},", "},\n"));
	 		    out.println();
	 		   IOUtils.closeQuietly(input);
	 		   IOUtils.closeQuietly(fw);
	 		   IOUtils.closeQuietly(out);
	 		  httpConnection.disconnect();
	 		 
	 		   System.err.println("执行完成！");
	 		  // cdl.countDown();
	 		  //p.endWait();
	 		  synchronized(object){
	 			  object.notify();
	 		  }
	         }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
