package test;

public class Utils {
	
	public static void printList(int[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	
	public static void printMatrix(int[][] a){
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[j].length;j++){
				System.out.print(a[j]+" ");
			}
			System.out.println();
		}
	}
	
}
