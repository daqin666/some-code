package com.example.somecode.threads;

public class ExtendsThread extends Thread{
	
//	 int i = 0;

	    //重写run方法，run方法的方法体就是现场执行体
	    public void run() {
	        for (int i = 0; i < 100; i++) {
	            System.out.println("run === " + Thread.currentThread().getName() + "  " + i);
	        }
	    }

	    public static void main(String[] args) {

	        for (int i = 0; i < 100; i++) {
	            System.out.println("main === " + Thread.currentThread().getName() + "  : " + i);
	            if (i == 50) {
	                new ExtendsThread().start();
	                new ExtendsThread().start();
	            }
	        }
	    }

	
}
