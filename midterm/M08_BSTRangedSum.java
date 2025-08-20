import java.util.*;

public class M08_BSTRangedSum {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    private static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            TreeNode cur = q.poll();
            if (i < arr.length && arr[i] != -1) {
                cur.left = new TreeNode(arr[i]);
                q.offer(cur.left);
            }
            i++;
            if (i < arr.length && arr[i] != -1) {
                cur.right = new TreeNode(arr[i]);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    private static int rangeSum(TreeNode root, int L, int R) {
        if (root == null) return 0;
        if (root.val < L) return rangeSum(root.right, L, R);
        if (root.val > R) return rangeSum(root.left, L, R);
        return root.val + rangeSum(root.left, L, R) + rangeSum(root.right, L, R);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int L = sc.nextInt();
        int R = sc.nextInt();
        TreeNode root = buildTree(arr);
        int sum = rangeSum(root, L, R);
        System.out.println("Sum: " + sum);
    }
}

/*
時間複雜度：O(n) 最壞；平均 O(log n + k)（k = 區間內節點數）
空間複雜度：O(h) 遞迴堆疊，h = 樹高
*/