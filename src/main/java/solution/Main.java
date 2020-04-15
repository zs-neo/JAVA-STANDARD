package solution;

import standard.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Main {
  public String dfs(TreeNode root, String s) {
    if (root == null) {
      s += "null,";
      return s;
    }
    s += root.val+",";
    s = dfs(root.left, s);
    s = dfs(root.right, s);
    return s;
  }
  
  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    return dfs(root, "");
  }
  
  public TreeNode rDfs(TreeNode root, List<String> nums) {
    if (nums.size() == 0) {
      return root;
    }
    if (root == null) {
      return root;
    }
    String s = nums.get(0);
    nums.remove(0);
    if (s == "null") return null;
    TreeNode r = new TreeNode(Integer.parseInt(s));
    r.left = rDfs(root.left, nums);
    r.right = rDfs(root.left, nums);
    return r;
  }
  
  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    String[] s = data.split(",");
    if (s.length == 0) {
      return null;
    }
    List nums = new ArrayList();
    for (int i = 1; i < s.length; i++) {
      nums.add(s[i]);
    }
    return rDfs(new TreeNode(Integer.parseInt(s[0])), nums);
  }
  
  /**
   *     1
   *    / \
   *   2   3
   *      / \
   *     4   5
   * @param args
   */
  public static void main(String[] args) {
    TreeNode a = new TreeNode(1);
    TreeNode b = new TreeNode(2);
    TreeNode c = new TreeNode(3);
    TreeNode d = new TreeNode(4);
    TreeNode e = new TreeNode(5);
    a.left = b;a.right = c;
    c.left = d;c.right = e;
    Main main = new Main();
    System.out.println(main.serialize(a));
  }
  
}
