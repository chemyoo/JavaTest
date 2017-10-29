package com.chemyoo.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonObject;

public class ComputerNumber {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	/**从文件读取对象*/
	private static Object department =null;
	/**静态代码块*/
	static
	{
		try 
		{
			FileInputStream fileinput=new FileInputStream("D:/objectFile.obj");
			ObjectInputStream in = new ObjectInputStream(fileinput);
			department= in.readObject();
			in.close();
			fileinput.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		int [] number = new int [200];
		int size = number.length;
		for (int i =0;i<size;i++)
		{
			number[i]=i+1;
		}//初始化数组值
		int n=size;
		while(n>(size/2))
		{
			n--;
			int index = (int)(Math.random()*(n));
			int temp = number[index];//
			number[index]=number[n];
			number[n]=temp;
		}
		int leftnum[]=new int[size/2];
		int rightnum[]=new int[size/2];
		for(int i =0;i<size;i++)
		{
			if(i<size/2)
				leftnum[i]=number[i];
			else
				rightnum[i-(size/2)]=number[i];
		}
		arraySortDesc(rightnum);
		arraySortAsc(leftnum);
		System.out.println(Arrays.toString(rightnum));
		System.out.println(Arrays.toString(leftnum));
		int sum = 0;
		for(int j=0;j<rightnum.length;j++)
		{
			sum+=(Math.abs(rightnum[j]-leftnum[j]));
		}
		System.out.println(sum);
		//Clock clock=Clock.systemUTC();JDK 8中含有
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\objectFile.obj"));
	    out.writeObject("部门联系人");    //写入customer对象
	    out.close();
	    ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\objectFile.obj"));
        System.out.println(in.readObject());    //读取字面值常量
        System.out.println(department);    //读取字面值常量
        in.close();
        
        String city = java.net.URLEncoder.encode("福田区", "utf-8");
		
		//拼地址
        String apiUrl = String.format("http://www.sojson.com/tianqi/api/1018-59493.shtml");
		//String apiUrl = String.format("http://www.sojson.com/open/api/weather/json.shtml?city=%s",city);
		//开始请求
		URL url= new URL(apiUrl);
		URLConnection open = url.openConnection();
		InputStream input = open.getInputStream();
		//这里转换为String，带上包名，怕你们引错包
		String result = IOUtils.toString(input,"utf-8");
		//输出
		System.out.println(result);
		
		url= new URL("http://d1.weather.com.cn/sk_2d/101280603.html");
		
		open = url.openConnection();
		
		open.setRequestProperty("Content-type", "application/json");
		open.setRequestProperty("referer", "http://m.weather.com.cn/d/detail/101280603.shtml");
		input = open.getInputStream();
		//这里转换为String，带上包名，怕你们引错包
		 result = IOUtils.toString(input,"utf-8");
		//输出
		System.out.println(result);
		
		url= new URL("http://d1.weather.com.cn/typhoon/typhoon_list/list_2017.json?callback=getData&_=1501073477827");
		
		open = url.openConnection();
		
		open.setRequestProperty("Content-type", "application/json");
		open.setRequestProperty("referer", "http://typhoon.weather.com.cn/gis/typhoon_m.shtml");
		input = open.getInputStream();
		//这里转换为String，带上包名，怕你们引错包
		 result = IOUtils.toString(input,"utf-8");
		//输出
		System.out.println(result);
		
		url= new URL("http://d1.weather.com.cn/typhoon/typhoon_data/2017/1709.json");
		
		open = url.openConnection();
		open.setRequestProperty("Content-type", "application/json");
		open.setRequestProperty("referer", "http://typhoon.weather.com.cn/gis/typhoon_m.shtml");
		input = open.getInputStream();
		//这里转换为String，带上包名，怕你们引错包
		 result = IOUtils.toString(input,"utf-8");
		//输出
		System.out.println(result);
		
		url= new URL("http://d1.weather.com.cn/wap_40d/101240101.html");
		
		open = url.openConnection();
		open.setRequestProperty("Content-type", "application/json");
		open.setRequestProperty("referer", "http://m.weather.com.cn/d/town/today");
		input = open.getInputStream();
		//这里转换为String，带上包名，怕你们引错包
		 result = IOUtils.toString(input,"utf-8");
		//输出
		System.out.println(result);
		String array []= result.replace(";", ",").split("=");
		List<String> a = new ArrayList<String>();
		a.add(array[array.length-1]);
		System.err.println(a);
		JSONObject json = new JSONObject(array[array.length-1]);
		System.err.println(json.getJSONArray("jh").get(1));
	}
	/**
	 * 找到数组中最小值的下标
	 * @param 数组和要查找的数组长度
	 */
	private static int findMin_Value_Index(int array[],int length)
	{
		int min=array[length];
		int minindex=length;
		for(int i=length;i<array.length;i++)
		{
			if(min>array[i])
			{
				min=array[i];
				minindex=i;
			}
		}
		return minindex;
	}
	/**
	 * 找到最小的数，放到最前面，然后将要查找的数组长度减一达到排序目的
	 * 即总是将最大的数放到数组最后
	 * long array[]={1,3,2,101,90,89,11111,23,45,666,777,888,998,999,12,123};
	 * @author chemyoo
	 * @param score
	 */
	private static void arraySortAsc(int array[])
	{
		int t;
		int minindex=-1;
		for(int i=0;i<array.length-1;i++)
		{
			minindex=findMin_Value_Index(array,i);//从指定位置查找最小值得下标
			if(minindex!=i)
			{
				t=array[minindex];
				array[minindex]=array[i];
				array[i]=t;
			}
		}
	}
	private static int findMax_Value_Index(int array[],int length)
	{
		int max=array[length];
		int maxindex=length;
		for(int i=length;i<array.length;i++)
		{
			if(max<array[i])
			{
				max=array[i];
				maxindex=i;
			}
		}
		return maxindex;
	}
	//选择排序
	private static void arraySortDesc(int array[])
	{
		int t;
		int maxindex=-1;
		for(int i=0;i<array.length-1;i++)
		{
			maxindex=findMax_Value_Index(array,i);//从指定位置查找最大值得下标
			if(maxindex!=i)
			{
				t=array[maxindex];
				array[maxindex]=array[i];
				array[i]=t;
			}
		}
	}
}
