package com.chemyoo.thread;

public class ThreadTest {
	
	public static void main(String args[])
	{
		MyThread t = new MyThread();
		//Thread tt = new Thread(t);
		//tt.setDaemon(true);//设置为后台线程
		t.setDaemon(false);
		t.start();
	}
}

class MyThread extends Thread
{
	public void run()
	{
		for(;;)
		{
			System.err.println("线程正在运行...");
		}
	}
}
