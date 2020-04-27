package solution;

// 本题为考试单行多行输入输出规范示例，无需提交，不计分。

import java.text.Format;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		double[][] a = new double[100000][2];
		double[][] b = new double[100000][2];
		int x, y;
		double res = 100000000,t=0;
		for (int i = 0; i < n; i++) {
			int opt = sc.nextInt();
			for (int k = 0; k < opt; k++) {
				a[k][0] = sc.nextInt();
				a[k][1] = sc.nextInt();
			}
			for (int k = opt; k < 2 * opt; k++) {
				x = sc.nextInt();
				y = sc.nextInt();
				for (int j = 0; j < opt; j++) {
					t = (a[j][0]-x)*(a[j][0]-x) + (a[j][1]-y)*(a[j][1]-y);
					if(t<res){
						res = Math.sqrt(t);
					}
				}
			}
			String ans = res+"";
			if(ans.length()<5){
				ans+="000000";
			}
			System.out.println(Double.parseDouble(ans.substring(0,5)));
		}
		
	}
}