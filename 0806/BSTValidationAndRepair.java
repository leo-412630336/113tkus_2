import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    private static boolean isValidBSTHelper(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;
        if (min != null && node.val <= min) return false;
        if (max != null && node.val >= max) return false;
        return isValidBSTHelper(node.left, min, node.val) &&
               isValidBSTHelper(node.right, node.val, max);
    }

    static TreeNode first = null, second = null, prev = null;
    public static List<TreeNode> findWrongNodes(TreeNode root) {
        first = second = prev = null;
        inorderFindWrong(root);
        if (first != null && second != null)
            return Arrays.asList(first, second);
        return Collections.emptyList();
    }

    private static void inorderFindWrong(TreeNode root) {
        if (root == null) return;
        inorderFindWrong(root.left);
        if (prev != null && root.val < prev.val) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;
        inorderFindWrong(root.right);
    }

    public static void recoverTree(TreeNode root) {
        List<TreeNode> wrongNodes = findWrongNodes(root);
        if (wrongNodes.size() == 2) {
            int temp = wrongNodes.get(0).val;
            wrongNodes.get(0).val = wrongNodes.get(1).val;
            wrongNodes.get(1).val = temp;
        }
    }

    static class Result {
        boolean isBST;
        int size; 
        int min;
        int max;
        int maxBSTSize;
        Result(boolean isBST, int size, int min, int max, int maxBSTSize) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
            this.maxBSTSize = maxBSTSize;
        }
    }

    public static int minRemoveToMakeBST(TreeNode root) {
        int totalNodes = countNodes(root);
        Result res = largestBSTSubtree(root);
        return totalNodes - res.maxBSTSize;
    }

    private static int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private static Result largestBSTSubtree(TreeNode node) {
        if (node == null) return new Result(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        Result left = largestBSTSubtree(node.left);
        Result right = largestBSTSubtree(node.right);

        if (left.isBST && right.isBST && node.val > left.max && node.val < right.min) {
            int size = left.size + right.size + 1;
            return new Result(true, size,
                    Math.min(left.min, node.val),
                    Math.max(right.max, node.val),
                    size);
        } else {
            return new Result(false,
                    left.size + right.size + 1,
                    0, 0,
                    Math.max(left.maxBSTSize, right.maxBSTSize));
        }
    }

    public static void main(String[] args) {
     
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        System.out.println("是否為有效BST: " + isValidBST(root)); 

        List<TreeNode> wrongNodes = findWrongNodes(root);
        System.out.print("錯誤節點值: ");
        for (TreeNode n : wrongNodes) System.out.print(n.val + " ");
        System.out.println();

        recoverTree(root);

        System.out.println("修復後是否為有效BST: " + isValidBST(root)); 
        root.right.left.val = 5;
        System.out.println("最少移除節點數以成為BST: " + minRemoveToMakeBST(root));
    }
}