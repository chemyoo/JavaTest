package com.chemyoo.publics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chemyoo.utils.ChemyooUtils;

/**
 * @author chemyoo
 * @since 2016/09/21
 *
 */
public class DataType {

	//private static Logger logger = Logger.getRootLogger();
	private static Logger logger = Logger.getLogger(DataType.class);
	private static final String DESC="desc";
	private static final String ASC="asc";
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int num=79;
		
		Object numtostr=((Object)num);
		//System.out.println((String)numtostr);
		
		Object obj=79;
		System.out.println(numtostr==obj);
		obj=90;
		System.out.println(numtostr==obj);
		System.out.println((Integer)obj);
		List<Map<String,String>> test = new ArrayList<Map<String,String>>();
		Map a =new HashMap();
		a.put("32314", "r542435");
		test.add(a);
		Map b=new HashMap();
		b.put("ttt", a);
		test.add(b);
//		test.toArray();
		removeList(test);
		System.out.println(test.size());
		
		Double DQSW=(Double)null;
		System.out.println(DQSW==null);
		System.out.println((String)null==null);
		long score[]={7,3,8,19,6,22,1,2,54,53,52,51,77,89,90,67,21,66,77,9,18,67,88,44};
		Object s[]={7,3,8,19,6,22,1,2,54,53,52,51,77,89,90,67,21,66,77,9,18,67,88,44};
		List<Object> list = new ArrayList<Object>();
		list=Arrays.asList(s);//数组转List
		System.err.println(ChemyooUtils.isNotEmpty(list));
		System.out.println(list.toString());//List转String
		System.out.println(Arrays.toString(list.toArray()));//List转数组，数组转String
//		logger.debug("最大值是:"+getMax_Value(score));
//		logger.debug("最小值是:"+getMin_Value(score));
		long start=System.nanoTime();
//		findSpecial(score);
//		logger.debug(Arrays.toString(score));
//		logger.debug("findSpecial主函数执行耗时："+(System.nanoTime()-start)+"nm");
		start=System.nanoTime();
		long arr[]= { 3,2,1 };
		sortArray(arr);
//		otherSort(arr);
		logger.debug(Arrays.toString(arr));
		long array[]= { 3,2,1};
		arraySort(array);
		logger.debug(Arrays.toString(array));
//		otherSort(score);
//		logger.debug(Arrays.toString(score));
//		logger.debug("OtherSort主函数执行耗时："+(System.nanoTime()-start)+"nm");
		
//		start=System.nanoTime();
//		sortArray(score);
//		logger.debug(Arrays.toString(score));
//		logger.debug("sortArray主函数执行耗时："+(System.nanoTime()-start)+"nm");
		
	}
	public static void removeList(List<Map<String, String>> test)
	{
		test.remove(1);
		logger.debug("移除一个Map内的对象");
	}
	/**
	 * 获取数组中最大的值
	 * @param array数组
	 * @return Max_Value
	 */
	private static long getMax_Value(long array[])
	{
		long Max_Value=-1L;//数组长度为零时，返回-1
		if(array.length!=0)
		{
			Max_Value=array[0];
		}
		for(int i=1;i<array.length;i++)
		{
			if(Max_Value<array[i])
				Max_Value=array[i];
		}
		return Max_Value;
	}
	/**
	 * 获取数组中最小的值
	 * @param array数组
	 * @return Min_Value
	 */
	private static long getMin_Value(long array[])
	{
		long Min_Value=-1L;//数组长度为零时，返回-1
		if(array.length!=0)
		{
			Min_Value=array[0];
		}
		for(int i=1;i<array.length;i++)
		{
			if(Min_Value>array[i])
				Min_Value=array[i];
		}
		return Min_Value;
	}
	/**
	 * 冒泡排序
	 * @param array数组
	 */
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
	}
	/**
	 * 找到最大最小值分别放在最后和最前
	 * @param array数组
	 */
	private static void findSpecial(long array[])
	{
		long times1 = System.nanoTime();
		long max=-1L;
		long min=-1L;
		if(array.length!=0)
		{
			 max=array[0];
			 min=array[0];
		}
		int maxindex=0;
		int minindex=0;
		for(int i=1;i<array.length;i++)
		{
			if(max<array[i])
			{
				max=array[i];
				maxindex=i;
			}
			else if(min>array[i])
			{
				min=array[i];
				minindex=i;
			}
		}
		long temp;
		if(maxindex!=array.length-1)
		{
			temp=array[maxindex];
			array[maxindex]=array[array.length-1];
			array[array.length-1]=temp;
		}
		if(minindex!=0)
		{
			temp=array[minindex];
			array[minindex]=array[0];
			array[0]=temp;
		}
		logger.debug("findSpecial内部运行用时:" + (System.nanoTime()-times1)+"nm");
	}
	/**
	 * 找到数组中最大值的下标
	 * @param 数组和要查找的数组长度
	 */
	private static int findMax_Value_Index(long array[],int length)
	{
		long max=array[0];
		int maxindex=0;
		for(int i=1;i<length;i++)
		{
			if(max<array[i])
			{
				max=array[i];
				maxindex=i;
			}
		}
		return maxindex;
	}
	/**
	 * 找到最大的数，放到最后，然后将要查找的数组长度减一达到排序目的
	 * 即总是将最大的数放到数组最后
	 * @author chemyoo
	 * @param score
	 */
	private static void otherSort(long array[])
	{  
		long start=System.nanoTime();
		long t;
		int maxindex=-1;
		for(int i=array.length-1;i>0;i--)
		{
			maxindex=findMax_Value_Index(array,i+1);//找到数组中指定长度中最大值的下标
			if(maxindex!=i)
			{
				t=array[maxindex];
				array[maxindex]=array[i];
				array[i]=t;
			}
		}
		logger.debug("OtherSort内部运行用时:" + (System.nanoTime()-start)+"nm");
	}
	/**
	 * 找到数组中最小值的下标
	 * @param 数组和要查找的数组长度
	 */
	private static int findMin_Value_Index(long array[],int length)
	{
		long min=array[length];
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
	private static void arraySort(long array[])
	{
		long start=System.nanoTime();
		long t;
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
		logger.debug("arraySort内部运行用时:" + (System.nanoTime()-start)+"nm");
	}
}
