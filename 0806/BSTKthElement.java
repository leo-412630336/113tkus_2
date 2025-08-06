import java.util.*;

public class BSTKthElement {

    static class TreeNode {
        int val;
        TreeNode left, right;
        int size; 
        TreeNode(int val) {
            this.val = val;
            this.size = 1;
        }
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
            node.size = 1 + size(node.left) + size(node.right);
            return node;
        }

        public void delete(int val) {
            root = deleteRec(root, val);
        }

        private TreeNode deleteRec(TreeNode node, int val) {
            if (node == null) return null;
            if (val < node.val) {
                node.left = deleteRec(node.left, val);
            } else if (val > node.val) {
                node.right = deleteRec(node.right, val);
            } else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;
                TreeNode minNode = findMin(node.right);
                node.val = minNode.val;
                node.right = deleteRec(node.right, minNode.val);
            }
            node.size = 1 + size(node.left) + size(node.right);
            return node;
        }

        private TreeNode findMin(TreeNode node) {
            while (node.left != null) node = node.left;
            return node;
        }

        private int size(TreeNode node) {
            return node == null ? 0 : node.size;
        }

        public int kthSmallest(int k) {
            if (k <= 0 || k > size(root)) throw new IllegalArgumentException("k超出範圍");
            return kthSmallestRec(root, k);
        }

        private int kthSmallestRec(TreeNode node, int k) {
            int leftSize = size(node.left);
            if (k <= leftSize) return kthSmallestRec(node.left, k);
            else if (k == leftSize + 1) return node.val;
            else return kthSmallestRec(node.right, k - leftSize - 1);
        }

        public int kthLargest(int k) {
            int n = size(root);
            if (k <= 0 || k > n) throw new IllegalArgumentException("k超出範圍");
            return kthSmallest(n - k + 1);
        }

        public List<Integer> rangeKthSmallest(int k, int j) {
            if (k <= 0 || j > size(root) || k > j)
                throw new IllegalArgumentException("k或j超出範圍或k>j");
            List<Integer> result = new ArrayList<>();
            rangeKthSmallestRec(root, k, j, new int[]{0}, result);
            return result;
        }

        private void rangeKthSmallestRec(TreeNode node, int k, int j, int[] count, List<Integer> result) {
            if (node == null) return;
            rangeKthSmallestRec(node.left, k, j, count, result);
            count[0]++;
            if (count[0] >= k && count[0] <= j) result.add(node.val);
            if (count[0] > j) return;
            rangeKthSmallestRec(node.right, k, j, count, result);
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] vals = {20, 10, 30, 5, 15, 25, 35};
        for (int v : vals) bst.insert(v);

        System.out.println("第3小元素: " + bst.kthSmallest(3)); 
        System.out.println("第2大元素: " + bst.kthLargest(2));  

        System.out.println("第2小到第5小元素: " + bst.rangeKthSmallest(2, 5)); 

        System.out.println("插入17");
        bst.insert(17);

        System.out.println("刪除10");
        bst.delete(10);

        System.out.println("刪除後第3小元素: " + bst.kthSmallest(3)); 

        System.out.println("刪除後第2大元素: " + bst.kthLargest(2));  
    }
}