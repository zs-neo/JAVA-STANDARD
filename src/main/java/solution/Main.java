package solution;

public class Main {
  
  public boolean canPartitionKSubsets(int[] nums, int k) {
    sort(nums, 0, nums.length - 1);
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
    }
    if (sum % k > 0) return false;
    int partSum = sum / k;
    int[] ans = new int[k];
    if (nums[nums.length - 1] > partSum) return false;
    return search(nums, nums.length - 1, partSum, ans);
  }
  
  public boolean search(int[] nums, int cur, int target, int[] ans) {
    if (cur < 0) return true;
    int num = nums[cur--];
    for (int i = 0; i < ans.length; i++) {
      if (ans[i] + num <= target) {
        ans[i] += num;
        if (search(nums, cur, target, ans)) {
          return true;
        }
        ans[i] -= num;
      }
    }
    return false;
  }
  
  public void sort(int[] nums, int left, int right) {
    if (left > right) return;
    int i, j, temp, t;
    i = left;
    j = right;
    temp = nums[left];
    while (i < j) {
      while (i < j && nums[j] >= temp) j--;
      while (i < j && nums[i] <= temp) i++;
      if (i < j) {
        t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
      }
    }
    nums[left] = nums[i];
    nums[i] = temp;
    sort(nums, left, i - 1);
    sort(nums, i + 1, right);
  }
  
  
  public static void main(String[] args) {
    Main main = new Main();
    int[] a = {4, 3, 2, 3, 5, 2, 1};
    int[] b = {2, 2, 2, 2, 3, 4, 5};
    System.out.println(main.canPartitionKSubsets(a, 4));
    System.out.println(main.canPartitionKSubsets(b, 4));
  }
}
