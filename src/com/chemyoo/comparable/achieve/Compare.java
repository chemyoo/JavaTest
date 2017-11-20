package com.chemyoo.comparable.achieve;

public class Compare implements Comparable<Compare> {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Compare jijo = new Compare();
		System.err.println(jijo.compareTo(new Compare()));
		System.gc();
	}


	@Override
	public int compareTo(Compare o) {
		int a = this.hashCode();
		int b = o.hashCode();
		System.err.println("this " + a);
		System.err.println("o     " + b);
		return  o.hashCode() - this.hashCode();
	}
}
