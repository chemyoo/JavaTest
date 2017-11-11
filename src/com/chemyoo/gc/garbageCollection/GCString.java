package com.chemyoo.gc.garbageCollection;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GCString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//表A
		List<Map<String,Object>> A = new ArrayList<Map<String,Object>>();
		//表B
		List<Map<String,Object>> B = new ArrayList<Map<String,Object>>();
		//模拟笛卡尔乘积表
		List<Map<String,Object>> AB = new ArrayList<Map<String,Object>>();
		//初始化A,B数据
		initList(A,"A");
		initList(B,"B");
		
		//模拟笛卡尔积
		int a_size = A.size();
		int b_size = B.size();
		Map<String,Object> row = null;
		for(int a = 0; a < a_size; a++)
		{
			for(int b = 0; b < b_size; b++)
			{
				//if(B.get(b).get(key) == A.get(a).get(key)){//此处表示过滤条件，加上过滤条件就不是笛卡尔积了，但是这里可以模拟Where条件
				//使用TreeMap使得Map中的Key值按一定的顺序排序
				row = new TreeMap<String, Object>();
				row.putAll(A.get(a));
				if(row.containsKey("ID")&&B.get(b).containsKey("ID"))
				{
					Object value = B.get(b).get("ID");
					B.get(b).remove("ID");
					B.get(b).put("ID_1", value);
				}
				row.putAll(B.get(b));
				AB.add(row);
				//}
			}
		}
		System.err.println(AB);
	}
	
	 static void initList(List<Map<String,Object>>list,String listName)
	 {
		 	Map<String,Object> row = null;
		 	int limit = ("A".equalsIgnoreCase(listName) ? 4 : 3);
			int num = 0;
			for(int i =1; i < limit; i++)
			{
				row = new Hashtable<String, Object>();
				num = (i-1)*3;
				row.put("ID",i);
				if(i >= 2 && "A".equalsIgnoreCase(listName))
				{
					num = (i-1)*3-1;
					row.put(listName+"1",listName+(num+1));
				}
				else if(i == 1 && "B".equalsIgnoreCase(listName))
				{
					row.put(listName+i,"A1");
				}
				else
				{
					row.put(listName+"1",listName+(num+1));
				}
				row.put(listName+"2",listName+(num+2));
				row.put(listName+"3",listName+(num+3));
				list.add(row);
			}
	 }
}
