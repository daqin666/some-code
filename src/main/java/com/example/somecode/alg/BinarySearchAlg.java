package com.example.somecode.alg;

public class BinarySearchAlg {

    public static void main(String[] args) {
//      int[] arr = {00, 01, 02, 03, 04, 05, 06, 07, 08};
        int[] arr = {10, 14, 16, 16, 16, 16, 16, 88, 100};
        int index1 = upperBinarySearch(arr, 16); // 6
        System.out.println(index1);
        int index2 = lowerBinarySearch(arr, 16); // 2
        System.out.println(index2);
    }


    /**
     * @param arr 已排序的数组
     * @param data  要找的数据
     * @return  索引，如果元素不存在，直接返回-1
     * int[] arr = {10,14,16,25,28,30,35,88,100};
     */
    public  static int binarySearch(int[] arr,int data) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] < data) {
                low = mid + 1;
            } else if (arr[mid] > data) {
                high = mid - 1;
            } else if (arr[mid] == data)
                return mid;
        }
        return -1;
    }

    public static int upperBinarySearch(int[] arr, int data) {
        int low = 0;
//        int height = arr.length;
        int height = arr.length - 1;
        int item = -1;
        while (low <= height) {
            int mid = (low + height) / 2;
            if (data == arr[mid]) {
                item = mid;
                low = mid + 1;
            } else if (data < arr[mid]) {
//                height = mid;
                height = mid - 1;
            } else if (data > arr[mid]) {
                low = mid + 1;
            }
        }
        return item;

    }

    public  static int lowerBinarySearch(int[] arr,int data) {
        int low = 0;
//        int height = arr.length;
        int height = arr.length - 1;
        int item = -1;
        while (low <= height) {
            int mid = (low + height) / 2;
            if (data == arr[mid]) {
                item = mid;
//                height = mid;
                height = mid - 1;
            } else if (data < arr[mid]) {
//                height = mid;
                height = mid - 1;
            } else if (data > arr[mid]) {
                low = mid + 1;
            }
        }
        return item;

    }
}
