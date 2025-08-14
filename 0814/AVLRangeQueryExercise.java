import java.util.ArrayList;
import java.util.List;

public class AVLRangeQueryExercise {

    // 節點類別
    static class Node {
        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            height = 1;
        }
    }

    private Node root;

    // 取得節點高度
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // 更新高度
    private void updateHeight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    // 計算平衡因子
    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // 左旋
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // 右旋
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // 插入（AVL 平衡）
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        if (node == null) return new Node(key);

        if (key < node.key) node.left = insertRec(node.left, key);
        else if (key > node.key) node.right = insertRec(node.right, key);
        else return node; // 不允許重複

        updateHeight(node);

        int balance = getBalance(node);

        // LL
        if (balance > 1 && key < node.left.key) return rotateRight(node);
        // RR
        if (balance < -1 && key > node.right.key) return rotateLeft(node);
        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // 範圍查詢
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryRec(root, min, max, result);
        return result;
    }

    private void rangeQueryRec(Node node, int min, int max, List<Integer> result) {
        if (node == null) return;

        // 剪枝：如果 min 比當前節點小，才去左子樹
        if (min < node.key) {
            rangeQueryRec(node.left, min, max, result);
        }

        // 當前節點在範圍內
        if (node.key >= min && node.key <= max) {
            result.add(node.key);
        }

        // 剪枝：如果 max 比當前節點大，才去右子樹
        if (max > node.key) {
            rangeQueryRec(node.right, min, max, result);
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLRangeQueryExercise tree = new AVLRangeQueryExercise();

        int[] keys = {20, 10, 30, 5, 15, 25, 40, 3, 7, 13, 17};
        for (int key : keys) tree.insert(key);

        System.out.println("查詢範圍 [10, 25]： " + tree.rangeQuery(10, 25));
        System.out.println("查詢範圍 [5, 15]： " + tree.rangeQuery(5, 15));
        System.out.println("查詢範圍 [0, 50]： " + tree.rangeQuery(0, 50));
    }
}
