package com.example.somecode.alg;

public class Monkey {

	static int f(int totalNum, int distance, int num){
		if(num < 2) return 0;
		if(num == 2){
			if(distance > 1) return 0;
			if(totalNum < 2) return 0;
			return 1;
		}
		if(totalNum <= distance) return 0;
		if(num > totalNum) return totalNum-distance;
		
		while(distance>0){
			if(totalNum <= distance) return 0;
			
			if(totalNum%num <= 2) totalNum -= totalNum%num;
			int n = (totalNum-1)/num;
			int u = totalNum - n * num;
			int dw = n * 2 + 1; 
			
			int m = (u-2+dw-1)/dw;
			if(distance<m) m = distance;
			
			totalNum -= m * dw;
			distance -= m;			
		}
		
		return totalNum;
	}
	
	static void test(int totalNum, int distance, int num){
		System.out.println(String.format("totalNum=%d distance=%d num=%d --> %d", 
				totalNum, distance, num, f(totalNum,distance,num)));
	}
	
	public static void main(String[] args){
		test(100,50,50);
		test(30,3,10);
		test(30,6,10);
		test(3000,1000,1000);
		test(505,497,1000);
		test(987654321,32345678,10000000);
		test(30,1,2);
		test(30,10,2);
		test(30,40,10);
		test(30,28,100);
		test(100,50,20);
	}
	
	
}
