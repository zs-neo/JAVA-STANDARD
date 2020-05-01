package solution;

public class Main {
	public boolean binary(int[][] matrix, int h, int r, int left, int right, int target) {
		if (left > right) {
			return false;
		}
		int mid = (left + right) / 2;
		int temp = matrix[mid / r][mid % r];
		if (temp == target) {
			return true;
		}
		if (temp > target) {
			return binary(matrix, h, r, left, mid - 1, target);
		}
		if (temp < target) {
			return binary(matrix, h, r, mid + 1, right, target);
		}
		return false;
	}
	
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix.length == 0) return false;
		int h = matrix.length;
		int r = matrix[0].length;
		return binary(matrix, h, r, 0, h * r - 1, target);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		int[][] a = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
	}
}