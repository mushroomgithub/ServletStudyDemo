package cn.lnu.string.demo;

public class StringBuilderDemo {

	/**
	 * JDK1.5以后，出现了StringBuilder，它和StringBuffer用户一样
	 * StringBuffer是线程同步的
	 * StringBuilder是线程不同步的
	 * 单线程下，一般建议选择StringBuilder，因为速度块。
	 * 至于StringBuffer为什么慢？
	 * 是因为StringBuffer多线程下，为了保证安全性所有方法都加了同步，这导致在单线程下，每次操作都需要判断锁，执行同步，降低了效率
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
