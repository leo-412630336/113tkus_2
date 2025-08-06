import java.util.*;

public class BinaryTreeBasicOperations {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static class SumCount {
        int sum = 0;
        int count = 0;
    }
    public static SumCount sumAndCount(TreeNode root) {
        SumCount sc = new SumCount();
        helperSumCount(root, sc);
        return sc;
    }
    private static void helperSumCount(TreeNode node, SumCount sc) {
        if (node == null) return;
        sc.sum += node.val;
        sc.count++;
        helperSumCount(node.left, sc);
        helperSumCount(node.right, sc);
    }

    public static int findMax(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(findMax(root.left), findMax(root.right)));
    }
    public static int findMin(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(findMin(root.left), findMin(root.right)));
    }

    public static int treeWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            maxWidth = Math.max(maxWidth, size);
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return maxWidth;
    }

    public static boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean encounteredNull = false;

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                encounteredNull = true;
            } else {
                if (encounteredNull) return false; 
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
      
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);

        SumCount sc = sumAndCount(root);
        System.out.println("節點總和: " + sc.sum);
        System.out.println("節點平均值: " + ((double) sc.sum / sc.count));
        System.out.println("最大值: " + findMax(root));
        System.out.println("最小值: " + findMin(root));
        System.out.println("樹寬度: " + treeWidth(root));
        System.out.println("是否為完全二元樹: " + isCompleteTree(root));
    }
}
