package com.chemyoo.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.chemyoo.utils.ChemyooUtils;


public class RandomFileDemo {
	static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	public static void main(String args[]) throws Exception
	{
		Employee e1 = new Employee("lllll",23);
		Employee e2 = new Employee("lisi",24);
		//Employee e4 = new Employee("zhouliu",27);
		Employee e3 = new Employee("wangwu",25);
		File f = new File("D:\\test");
		if(!f.exists())
			f.mkdir();
		RandomAccessFile ra=new RandomAccessFile("D:\\test\\employee.txt","rw");
		ra.write(e1.name.getBytes());
		ra.writeInt(e1.age);
		ra.write(e2.name.getBytes());
		ra.writeInt(e2.age);
//		ra.write(e4.name.getBytes());
//		ra.writeInt(e4.age);
		ra.write(e3.name.getBytes());
		ra.writeInt(e3.age);
		ra.close();
		RandomAccessFile raf=new RandomAccessFile("D:\\test\\employee.txt","r");
		int len=8;
		raf.skipBytes(12); // 跳过第一个员工的信息,其姓名 8 字节,年龄 1 字节
		System.out.println("第二个员工信息:");
		String str="";
		for(int i=0;i<len;i++)
			str=str+(char)raf.readByte();
		System.out.println("name:"+str);
		System.out.println("age:"+raf.readInt());
		System.out.println("第一个员工的信息:");
		raf.seek(0);  // 将文件指针移动到文件开始位置
		str="";
		for(int i=0;i<len;i++)
			str=str+(char)raf.readByte();
		System.out.println("name:"+str);
		System.out.println("age:"+raf.readInt());
		System.out.println("第三个员工的信息:");
		raf.skipBytes(12); // 跳过第二个员工信息
		str="" ;
		for(int i=0;i<len;i++)
			str=str+(char)raf.readByte();
		System.out.println("name:"+str.trim());
		System.out.println("age:"+raf.readInt());
		raf.close();
		Sender sender = new Sender();  // 产生两个线程对象
		Receiver receiver = new Receiver();
		PipedOutputStream out = sender.getOutputStream(); // 写入
		PipedInputStream in = receiver.getInputStream(); // 读出
		out.connect(in);  // 将输出发送到输入
		sender.start(); // 启动线程
		receiver.start();
		//Thread.sleep(10*1000);
		while(Thread.activeCount()>1)  //保证前面的线程都执行完
		{
            Thread.yield();
		}
		receiver.close();
		Stack<Object> st = new Stack<Object>();
		StackDemo.pushStack(st, 213);
		StackDemo.pushStack(st, "文仲");
		StackDemo.pushStack(st, 'A');
		StackDemo.pushStack(st, 3.695);
		StackDemo.popStack(st);
		StackDemo.popStack(st);
		StackDemo.popStack(st);
		StackDemo.popStack(st);
		System.out.println("是否空栈-->"+st.empty());
		new PropertiesDemo();
		//System.out.println(ChemyooUtils.isNotEmpty(new Employee()));
		Test t = new Test();
		System.out.println(ChemyooUtils.isEmpty(t));
		GregorianCalendar grego = new GregorianCalendar();
		grego.add(GregorianCalendar.MINUTE, -60*24);
		System.err.println(ChemyooUtils.getDiffDays(new Date(), grego.getTime()));
		Pattern pattern = Pattern.compile("^[0-2]??[0-9][:]{1}[0-5][0-9]$");
		 Matcher matcher = pattern.matcher("gsfdas:");
		 // 字符串是否与正则表达式相匹配
		 System.err.println(matcher.matches());
		 //"09:69"
		 //ChemyooUtils.powerOff(1);
		 Date d =ChemyooUtils.dateAdd(new Date(), Calendar.MINUTE , 0);
		 System.err.println(ChemyooUtils.formatDate("yyyy.MM.dd - HH:mm:ss", d));
		 System.err.println(2^-3962);
		 DocumentBuilder db = dbf.newDocumentBuilder();
		 InputStream is = new FileInputStream("src/com/chemyoo/resouce/HeadTitle.xml");
		 Document document = db.parse(is);
		 NodeList nodes = document.getElementsByTagName("title");
		 for(int i=0,size = nodes.getLength();i<size;i++)
		 {
			 System.err.println(nodes.item(i).getTextContent());
		 }
		 Map<String, String> map = System.getenv();
	        for(Iterator<String> itr = map.keySet().iterator();itr.hasNext();){
	            String key = itr.next();
	            System.out.println(key + "=" + map.get(key));
	        } 
	        System.err.println(System.getenv("Path"));
	        
	        File[] roots = File.listRoots();  
	        FileSystemView fsv = FileSystemView.getFileSystemView();  
	        for (int i = 0; i < roots.length; i++) {  
	          System.out.println(roots[i]); 
	          System.out.println(fsv.getSystemDisplayName(roots[i]));
	        }  
	        
	}
}

class Test
{
	private Integer a =1;
}
class Employee
{
	String name;
	int age;
	final static int LENGTH = 8;
	Employee(){}
	public Employee(String name, int age) 
	{
		if(name.length()>LENGTH)
			name = name.substring(0, 8);
		else
		{
			while(name.length()<LENGTH)
				name = name + " ";//\u0000
		}
			
		this.name = name;
		this.age = age;
	}
	
}

//管道流，输出
class Sender extends Thread
{
	private PipedOutputStream out=new PipedOutputStream();
	public PipedOutputStream getOutputStream()
	{
		return out;
	}
	public void run()
	{
		 String s=new String("管道流，发送流!");
		 try
		 {
			 for(int i=0;i<10;i++)
			 {
				out.write(s.getBytes()); // 写入（发送），写入内存中
				Thread.sleep(1000);
			 }
			 out.close();
		 }
		 catch(IOException e)
		 {
			 System.err.println(e.getMessage());
		 } catch (InterruptedException e) {
			e.printStackTrace();
		}
	 }
}
//管道流，输入
class Receiver extends Thread
{
	private PipedInputStream in=new PipedInputStream();
	public PipedInputStream getInputStream()
	{
		return in;
	}
	public void close()
	{
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void run()
	{
		String s=null;
		byte [] buf = new byte[1024];
		try
		{
			 for(int i=0;i<10;i++)
			 {
				int len =in.read(buf);  // 读出数据
				s = new String(buf,0,len);
				System.out.println("收到了以下信息："+s);
			 }
			in.close();
		}
		catch(IOException e)
		{ 
			System.err.println(e.getMessage());
		}
	}
}
/**
 * 后入先出原则
 * @author chemyoo
 *
 */
class StackDemo
{
	public static void pushStack(Stack<Object> s,Object o)
	{
		System.err.println("入栈<--"+o);
		s.push(o);
		System.err.println("Stack<--"+s);
	}
	public static void popStack(Stack<Object> s)
	
	{
		Object o = s.pop();
		System.err.println("出栈-->"+o);
		System.err.println("Stack-->"+s);
	}
}
/**
 * Properties 
 * @author chemyoo
 * @Properties 属性（Properties）是Hashtable的一个子类。它用来保持值的列表，在其中关
 * 键字和值都是字符串（String）。Properties类被许多其它的Java类所使用。例如，
 * 当获得系统环境值时，System.getProperties( )返回对象的类型。
 */
class PropertiesDemo
{
	PropertiesDemo()
	{
		Properties capitals = new Properties();
		Set<Object> states  =null;
		capitals.put("中国", "北京");
		capitals.put("日本", "东京");
		capitals.put("俄罗斯", "莫斯科");
		capitals.put("法国", "巴黎");
		capitals.put("英国", "伦敦");
		states = capitals.keySet();
		Iterator<Object> it = states.iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();
			System.err.println(key+"首都："+capitals.getProperty(key));
		}
		String str = capitals.getProperty("美国", "没有发现");
		System.out.println("美国的首都：" + str );
		try {
			FileOutputStream out = new FileOutputStream("D:\\count.txt");
			capitals.store(out, "PropertiesFile use it .");
			out.flush();out.close();
			capitals.clear();
			states.clear();
			capitals.load(new FileInputStream("D:\\count.txt"));
			it = null ;
			//states = capitals.keySet();
			it = states.iterator();
			while (it.hasNext())
			{
				String key = (String) it.next();
				System.err.println(key+"首都："+capitals.getProperty(key));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}