package com.chemyoo.callback;

public class CallbackImpl implements CallbackService {

	@Override
	public void doCallBack(int a, int b , Callback call) {
			call.doCallBack(a+b);
	}

}
