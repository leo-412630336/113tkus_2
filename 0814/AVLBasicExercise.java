public class AVLBasicExercise {

    // 節點類別
    static class Node {
        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            height = 1; // 新節點高度為 1
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

    // 右旋轉
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // 旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // 左旋轉
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // 旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // 插入節點 (自動平衡)
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        // 1. 標準 BST 插入
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insertRec(node.left, key);
        else if (key > node.key) node.right = insertRec(node.right, key);
        else return node; // 重複值不插入

        // 2. 更新高度
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 3. 計算平衡因子並調整
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

    // 搜尋節點
    public boolean search(int key) {
        return searchRec(root, key);
    }

    private boolean searchRec(Node node, int key) {
        if (node == null) return false;
        if (key == node.key) return true;
        return key < node.key ? searchRec(node.left, key) : searchRec(node.right, key);
    }

    // 計算整棵樹高度
    public int getHeight() {
        return height(root);
    }

    // 檢查是否為有效的 AVL 樹
    public boolean isValidAVL() {
        return isAVL(root);
    }

    private boolean isAVL(Node node) {
        if (node == null) return true;
        int balance = getBalance(node);
        if (balance < -1 || balance > 1) return false;
        return isAVL(node.left) && isAVL(node.right);
    }

    // 測試
    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();

        int[] keys = {10, 20, 30, 40, 50, 25};
        for (int key : keys) {
            tree.insert(key);
            System.out.println("插入 " + key + " 後，高度：" + tree.getHeight() + "，有效AVL？ " + tree.isValidAVL());
        }

        System.out.println("搜尋 25: " + tree.search(25));
        System.out.println("搜尋 99: " + tree.search(99));
    }
}