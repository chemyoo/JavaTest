package com.chemyoo.callback;


public class Callback {
	
	public void doCallBack(int res)
	{
		System.err.println("getRes获取结果："+res);
	}
	
	public void sendResquest(int a , int b)
	{
		CallbackService service = new CallbackImpl();
		service.doCallBack(a, b, this);
		//将任务分给执行工作的类
		new Worker().doWork(a, b, this.new sendResquest());
		//this.new sendResquest().doCallBack(a, b, this);
//		System.err.println("回调通知-->"+System.nanoTime());
	}
	
	public class sendResquest implements Servicer
	{
		@Override
		public void callMe(int res) {
			System.err.println("callMe获取结果："+res);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//同步回调
		Callback callback = new Callback();
		callback.sendResquest(27 , 23);
		//callback.new sendResquest().doCallBack(6, 9, callback);
//		System.err.println("请求结束-->"+System.nanoTime());
		
	}

}
