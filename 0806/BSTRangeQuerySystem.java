import java.util.*;

public class BSTRangeQuerySystem {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static class BST {
        TreeNode root;

        public void insert(int val) {
            root = insertRec(root, val);
        }

        private TreeNode insertRec(TreeNode node, int val) {
            if (node == null) return new TreeNode(val);
            if (val < node.val) node.left = insertRec(node.left, val);
            else node.right = insertRec(node.right, val);
            return node;
        }

        public List<Integer> rangeQuery(int min, int max) {
            List<Integer> result = new ArrayList<>();
            rangeQueryRec(root, min, max, result);
            return result;
        }
        private void rangeQueryRec(TreeNode node, int min, int max, List<Integer> result) {
            if (node == null) return;
            if (node.val > min) rangeQueryRec(node.left, min, max, result);
            if (node.val >= min && node.val <= max) result.add(node.val);
            if (node.val < max) rangeQueryRec(node.right, min, max, result);
        }

        public int rangeCount(int min, int max) {
            return rangeCountRec(root, min, max);
        }
        private int rangeCountRec(TreeNode node, int min, int max) {
            if (node == null) return 0;
            if (node.val < min) return rangeCountRec(node.right, min, max);
            if (node.val > max) return rangeCountRec(node.left, min, max);
            return 1 + rangeCountRec(node.left, min, max) + rangeCountRec(node.right, min, max);
        }

        public int rangeSum(int min, int max) {
            return rangeSumRec(root, min, max);
        }
        private int rangeSumRec(TreeNode node, int min, int max) {
            if (node == null) return 0;
            if (node.val < min) return rangeSumRec(node.right, min, max);
            if (node.val > max) return rangeSumRec(node.left, min, max);
            return node.val + rangeSumRec(node.left, min, max) + rangeSumRec(node.right, min, max);
        }

        public int closestValue(int target) {
            return closestValueRec(root, target, root.val);
        }
        private int closestValueRec(TreeNode node, int target, int closest) {
            if (node == null) return closest;
            if (Math.abs(node.val - target) < Math.abs(closest - target)) {
                closest = node.val;
            }
            if (target < node.val) {
                return closestValueRec(node.left, target, closest);
            } else if (target > node.val) {
                return closestValueRec(node.right, target, closest);
            } else {
                return node.val;
            }
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] values = {20, 10, 5, 15, 30, 25, 35};
        for (int v : values) bst.insert(v);

        int min = 10, max = 30;
        System.out.println("範圍查詢 [" + min + ", " + max + "]: " + bst.rangeQuery(min, max));
        System.out.println("範圍計數 [" + min + ", " + max + "]: " + bst.rangeCount(min, max));
        System.out.println("範圍總和 [" + min + ", " + max + "]: " + bst.rangeSum(min, max));

        int target = 28;
        System.out.println("最接近 " + target + " 的節點值: " + bst.closestValue(target));
    }
}