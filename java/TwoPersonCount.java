package com.count;

/**
 	两个线程抢一个数
 */
public class TwoPersonCount implements Runnable {

	public static void main(String[] args) {

		Thread thread1 = new Thread(new TwoPersonCount(30));
		Thread thread2 = new Thread(new TwoPersonCount(70));

		thread1.start();
		thread2.start();

	}

	private int num = 0;

	public TwoPersonCount(int num) {
		this.num = num;
	}

	public void run() {
		for (int i = 0; i < num; i++) {
			count();
		}
	}

	public void count() {
		synchronized (TwoPersonCount.class) {
			Count count = Count.getInstance();
			count.setNum(count.getNum() + 1);
			System.out.println(Thread.currentThread().getName() + "-------------" + count.getNum());
		}
	}
}

class Count {
	private static Count instance;
	private int num = 0;

	private Count() {

	}

	public static synchronized Count getInstance() {
		if (instance == null) {
			instance = new Count();
		}
		return instance;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}