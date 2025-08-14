public class AVLDeleteExercise {

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

    // 計算平衡因子
    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // 更新高度
    private void updateHeight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
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

    // 左右旋
    private Node rotateLeftRight(Node node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    // 右左旋
    private Node rotateRightLeft(Node node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    // 插入（為了測試刪除）
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
            return rotateLeftRight(node);
        }
        // RL
        if (balance < -1 && key < node.right.key) {
            return rotateRightLeft(node);
        }

        return node;
    }

    // 刪除操作
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node node, int key) {
        if (node == null) return null;

        // 1. 標準 BST 刪除
        if (key < node.key) {
            node.left = deleteRec(node.left, key);
        } else if (key > node.key) {
            node.right = deleteRec(node.right, key);
        } else {
            // 找到要刪除的節點
            if (node.left == null && node.right == null) {
                // 情況 1: 葉子節點
                return null;
            } else if (node.left == null) {
                // 情況 2: 只有右子節點
                return node.right;
            } else if (node.right == null) {
                // 情況 2: 只有左子節點
                return node.left;
            } else {
                // 情況 3: 兩個子節點 -> 找後繼節點
                Node successor = getMinValueNode(node.right);
                node.key = successor.key;
                node.right = deleteRec(node.right, successor.key);
            }
        }

        // 2. 更新高度
        updateHeight(node);

        // 3. 檢查平衡並旋轉
        int balance = getBalance(node);

        // LL
        if (balance > 1 && getBalance(node.left) >= 0) return rotateRight(node);
        // LR
        if (balance > 1 && getBalance(node.left) < 0) return rotateLeftRight(node);
        // RR
        if (balance < -1 && getBalance(node.right) <= 0) return rotateLeft(node);
        // RL
        if (balance < -1 && getBalance(node.right) > 0) return rotateRightLeft(node);

        return node;
    }

    // 找最小值節點（用於找後繼）
    private Node getMinValueNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    // 前序遍歷（方便測試）
    public void preOrder() {
        preOrderRec(root);
        System.out.println();
    }

    private void preOrderRec(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrderRec(node.left);
            preOrderRec(node.right);
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLDeleteExercise tree = new AVLDeleteExercise();

        int[] keys = {10, 20, 30, 40, 50, 25};
        for (int key : keys) tree.insert(key);

        System.out.print("初始樹 (前序): ");
        tree.preOrder();

        // 刪除葉子節點
        System.out.println("\n刪除葉子節點 25:");
        tree.delete(25);
        tree.preOrder();

        // 刪除只有一個子節點的節點
        System.out.println("\n刪除只有一個子節點的節點 50:");
        tree.delete(50);
        tree.preOrder();

        // 刪除有兩個子節點的節點
        System.out.println("\n刪除有兩個子節點的節點 20:");
        tree.delete(20);
        tree.preOrder();
    }
}