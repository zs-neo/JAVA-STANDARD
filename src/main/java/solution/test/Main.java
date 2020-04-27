package solution.test;

// 本题为考试单行多行输入输出规范示例，无需提交，不计分。

import java.util.*;

public class Main {
	
	static int[] rowa = new int[100];
	static int[] rowb = new int[100];
	
	
	public static boolean check(int[] row, int a, int b) {
		for (int i = a + 1; i <= b; i++) {
			if (row[i] < row[i - 1]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 3
	 * 1 3 2
	 * 3 2 1
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int in;
		for (int i = 0; i < n; i++) {
			in = sc.nextInt();
			rowa[i] = in;
		}
		for (int i = 0; i < n; i++) {
			in = sc.nextInt();
			rowb[i] = in;
		}
		int[] dp = new int[100];
		dp[0] = 0;
		dp[1] = 0;
		if (rowa[0] <= rowa[1]) {
			dp[2] = 0;
		} else if (rowb[0] >= rowb[1]) {
			dp[2] = 1;
		} else {
			dp[2] = -1;
		}
		int[] temp = rowa;
		for (int i = 2; i < n; i++) {
			if (check(rowa, 0, i)) {
				dp[i] = dp[i - 1];
			} else {
				temp = rowa;
				temp[i - 1] = rowb[i];
				temp[i] = rowb[i - 1];
				if (check(temp, 0, i)) {
					if (dp[i] != 0) {
						dp[i] = Math.min(dp[i], dp[i - 1] + 1);
					}
				} else {
					dp[i] = -1;
				}
			}
		}
		System.out.println(dp[n - 1]);
	}
	
}
