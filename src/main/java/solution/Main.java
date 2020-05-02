package solution;

public class Main {
	
	private boolean check(String s, String target) {
		int[] mark = new int[s.length()];
		int count = 0;
		for (int i = 0; i < target.length(); i++) {
			for (int j = 0; j < s.length(); j++) {
				if (mark[j] == 0 && s.charAt(j) == target.charAt(i)) {
					mark[j] = 1;
					count++;
					break;
				}
			}
		}
		return count == target.length();
	}
	
	public String minWindow(String s, String t) {
		if(s.equals(t))return s;
		if(s.length()<t.length())return "";
		int left = 0;
		int right = s.length();
		boolean flag = true;
		int i = 0;
		while (left < right) {
			if (!flag) break;
			flag = false;
			i++;
			while (check(s.substring(left+1, right), t)) {
				left++;
				flag = true;
			}
			while (check(s.substring(left, right - 1), t)) {
				right--;
				flag = true;
			}
			if (i == 1 && (!flag)) {
				if(check(s,t))return s;
				else return "";
			}
		}
		return s.substring(left, right);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		System.out.println(main.minWindow("a", "b"));
		System.out.println(main.minWindow("ADOBECODEBANC", "ABC"));
		System.out.println(main.minWindow("abc", "ac"));
	}
	
}