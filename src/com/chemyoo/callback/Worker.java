package com.chemyoo.callback;

public class Worker {

	public void doWork(int a , int b ,Servicer service)
	{
		service.callMe(a+b);
	}
	
}
