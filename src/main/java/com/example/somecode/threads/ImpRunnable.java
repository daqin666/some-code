package com.example.somecode.threads;

public class ImpRunnable implements Runnable {
	
	 private int i;
     public void run(){
         for(i = 0;i <100;i++){
             System.out.println(Thread.currentThread().getName()+" "+i);
         }
     }
     
     public static void main(String[] args) {
         for(int i = 0;i < 100;i++){
             System.out.println(Thread.currentThread().getName()+" "+i);
             if(i==20){
            	 ImpRunnable ir = new ImpRunnable();
//                 new Thread(ir).start();
                 new Thread(ir,"新线程1").start();
                 new Thread(ir,"新线程2").start();
             }
         }

     }

}
