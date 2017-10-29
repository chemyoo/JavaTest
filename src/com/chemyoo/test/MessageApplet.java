package com.chemyoo.test;

import java.applet.Applet;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.RegexFileFilter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;

public class MessageApplet extends Applet {
	
	private String msg = "默认的内容！";
	
	String listen_port;
    String hdfs;

    private static JsonObject _instance;

    public static JsonObject getInstance() {
            return _instance;
    }
	public void paint(Graphics g)
	{
		g.drawString(msg, 20, 20);
		System.err.println(getJsonData());
		String jsonstr = ReadFile("abc.json");
		System.err.println(jsonstr);
		JSONObject jsonobj = JSONObject.fromObject(jsonstr);
		//System.err.println(jsonobj);
		Iterator iterator = jsonobj.keys();
		while(iterator.hasNext())
		{
			String key= (String) iterator.next(); 
			System.err.println(key+"="+jsonobj.get(key));
		}
		try 
		{
			String line_separator = System.getProperty("line.separator", "\n");
			writeFile("../src/def.json",jsonstr.replaceAll("[{]", "{"+line_separator)
					.replaceAll("[}]", line_separator+"}").replaceAll(",", ","+line_separator));//将内容以保存为json文件
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * 读取JSON文件的一种方式
	 * @return
	 */
	public Map<String,Object> getJsonData()
	{
		Map<String,Object> result = new HashMap<String,Object>();
		Gson gson = new Gson();
        FileInputStream configIn = null;
        try {
                configIn = new FileInputStream("abc.json");
                _instance = gson.fromJson(IOUtils.toString(configIn), JsonObject.class);
                //JsonObject json = (JsonObject) _instance;
                System.err.println(_instance.get("listen_port"));
                System.err.println( _instance.toString());
                Set<Entry<String, JsonElement>> json= _instance.entrySet();
                @SuppressWarnings("rawtypes")
				Iterator it = json.iterator();
                while (it.hasNext()) 
                {
	        		@SuppressWarnings("unchecked")
					Entry<String,JsonPrimitive> entey = (Entry<String, JsonPrimitive>) it.next();
	        		//Object value = _instance.get(key.getKey());
	        		//System.err.println(entey.getValue());
	        		result.put(entey.getKey(), entey.getValue());
	    		}
        } catch (JsonSyntaxException e) 
        {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
                IOUtils.closeQuietly(configIn);
        }
		return result;
	}
	public void setMsg(String msg) {
		this.msg = msg;
		repaint();
	}
	/**
	 * 读取JSON文件的一种方式
	 * @return
	 */
	public String ReadFile(String path){
	    File file = new File(path);
	    BufferedReader reader = null;
	    FileReader file_reader = null;
	    String laststr = "";
	    try {
	     //System.out.println("以行为单位读取文件内容，一次读一整行：");
	    file_reader = new FileReader(file);
	     reader = new BufferedReader(file_reader);
	     String tempString = null;
	     int line = 1;
	     //一次读入一行，直到读入null为文件结束
	     while ((tempString = reader.readLine()) != null) 
	     {
	      //显示行号
		     // System.out.println("line " + line + ": " + tempString);
		      laststr = laststr + tempString;
		      //line ++;
	     }
	     reader.close();
	    } catch (IOException e) 
	    {
	    	e.printStackTrace();
	    }
	    finally 
	    {
		     if (reader != null) 
		     {
			      try 
			      {
			    	  reader.close();
			      } 
			      catch (IOException e1)
			      {
			    	  e1.printStackTrace();
			      }
		     }
		     try 
		     {
				file_reader.close();
		     } catch (IOException e) 
		     {
				e.printStackTrace();
		     }
	    }
	    return laststr;
	}
	public void writeFile(String filePath, String sets) throws IOException {
	    FileWriter fw = new FileWriter(filePath);
	    PrintWriter out = new PrintWriter(fw);
	    out.write(sets);
	    out.println();
	    fw.close();
	    out.close();
	   }
}
