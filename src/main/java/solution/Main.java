package solution;

import java.util.*;

import java.util.Arrays;

public class Main {
	
	Map a = new HashMap();
	private static final int CHILD = 2;
	
	public static void sort(int[] arr){
		for(int i=arr.length/2-1;i>=0;i--){
			adjustHeap(arr,i,arr.length);
		}
	}
	
	public static void adjustHeap(int[] arr,int i,int length){
		int temp = arr[i];
		for(int k=CHILD*i+1;k<length;k=k*CHILD+1){
			if(k+1<length && arr[k]<arr[k+1]){
				k++;
			}
			if(arr[k] > temp){
				arr[i] = arr[k];
				i = k;
			}else{
				break;
			}
		}
		arr[i] = temp;
	}
	
}