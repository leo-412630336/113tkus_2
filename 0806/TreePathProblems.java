import java.util.*;

public class TreePathProblems {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> path = new ArrayList<>();
        dfsPaths(root, path, res);
        return res;
    }

    private static void dfsPaths(TreeNode node, List<Integer> path, List<List<Integer>> res) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));
        } else {
            dfsPaths(node.left, path, res);
            dfsPaths(node.right, path, res);
        }
        path.remove(path.size() - 1);
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        if (root.left == null && root.right == null) return root.val;
        int leftMax = maxRootToLeafSum(root.left);
        int rightMax = maxRootToLeafSum(root.right);
        return root.val + Math.max(leftMax, rightMax);
    }


    static int maxPathSumGlobal = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxPathSumGlobal = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxPathSumGlobal;
    }

    private static int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));  
        int right = Math.max(0, maxPathDown(node.right));
        maxPathSumGlobal = Math.max(maxPathSumGlobal, left + node.val + right);
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
 
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println("所有根到葉路徑: " + allRootToLeafPaths(root));
        System.out.println("是否存在和為 7 的路徑: " + hasPathSum(root, 7));  
        System.out.println("最大根到葉路徑和: " + maxRootToLeafSum(root));
        System.out.println("最大路徑和（任意兩節點）: " + maxPathSum(root));    
    }
}