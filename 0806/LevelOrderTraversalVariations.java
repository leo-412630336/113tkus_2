import java.util.*;

public class LevelOrderTraversalVariations {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<List<Integer>> levelOrderByLevel(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                levelList.add(curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            res.add(levelList);
        }
        return res;
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> levelList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (leftToRight) levelList.addLast(curr.val);
                else levelList.addFirst(curr.val);

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            res.add(levelList);
            leftToRight = !leftToRight;
        }
        return res;
    }

    public static List<Integer> lastNodeOfEachLevel(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int lastVal = 0;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                lastVal = curr.val; 
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            res.add(lastVal);
        }
        return res;
    }

    public static List<List<Integer>> verticalOrderTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        TreeMap<Integer, List<Integer>> columnTable = new TreeMap<>();
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> p = queue.poll();
            TreeNode node = p.getKey();
            int col = p.getValue();

            columnTable.putIfAbsent(col, new ArrayList<>());
            columnTable.get(col).add(node.val);

            if (node.left != null) queue.offer(new Pair<>(node.left, col - 1));
            if (node.right != null) queue.offer(new Pair<>(node.right, col + 1));
        }

        for (List<Integer> colList : columnTable.values()) {
            res.add(colList);
        }
        return res;
    }

    static class Pair<K, V> {
        private K key;
        private V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    public static void main(String[] args) {
     
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("每層節點: " + levelOrderByLevel(root));
        System.out.println("之字形走訪: " + zigzagLevelOrder(root));
        System.out.println("每層最後節點: " + lastNodeOfEachLevel(root));
        System.out.println("垂直層序走訪: " + verticalOrderTraversal(root));
    }
}