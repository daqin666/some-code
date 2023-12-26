package com.example.somecode.alg;

public class SortAlg {

//	冒泡排序(第二循环梯队中，相邻的值比较，最大的值依次冒到最末尾)
	void bubbleSort(int arr[], int length) {
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < length-i-1; j++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
	
//	选择排序（第二梯队每次循序时，选出最小的数，依次放到左边）
	void selectionSort(int arr[], int length) {
		for(int i = 0; i < length; i++) {
			int index = i;
			for(int j = i+1; j < length; j++) {
				if(arr[j] < arr[index]) {
					index = j;
				}
			}
			if(index != i) {
				int temp = arr[index];
				arr[index] = arr[i];
				arr[i] = temp;
			}
		}
	}
	
//	插入排序
	void insertSort(int arr[], int length) {
		for (int i = 1; i < length; i++) {
			if (arr[i] < arr[i - 1]) {
				int temp = arr[i];
				int j;
				for (j = i - 1; j >= 0 && temp < arr[j]; j--) {
					arr[j + 1] = arr[j];
				}
				arr[j + 1] = temp;
			}
		}
	}
	
	
	
	
}



