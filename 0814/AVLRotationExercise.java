public class AVLRotationExercise {

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

    // 取得節點高度
    private static int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // 更新節點高度
    private static void updateHeight(Node node) {
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    // 左旋轉 (Right-Right Case)
    public static Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // 旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // 右旋轉 (Left-Left Case)
    public static Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // 旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // 左右旋轉 (Left-Right Case)
    public static Node rotateLeftRight(Node node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    // 右左旋轉 (Right-Left Case)
    public static Node rotateRightLeft(Node node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    // 前序遍歷 (用來檢查結果)
    public static void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // 建立測試樹 (手動)
    private static Node makeRRCase() {
        Node root = new Node(10);
        root.right = new Node(20);
        root.right.right = new Node(30);
        updateHeight(root.right);
        updateHeight(root);
        return root;
    }

    private static Node makeLLCase() {
        Node root = new Node(30);
        root.left = new Node(20);
        root.left.left = new Node(10);
        updateHeight(root.left);
        updateHeight(root);
        return root;
    }

    private static Node makeLRCase() {
        Node root = new Node(30);
        root.left = new Node(10);
        root.left.right = new Node(20);
        updateHeight(root.left);
        updateHeight(root);
        return root;
    }

    private static Node makeRLCase() {
        Node root = new Node(10);
        root.right = new Node(30);
        root.right.left = new Node(20);
        updateHeight(root.right);
        updateHeight(root);
        return root;
    }

    public static void main(String[] args) {
        // 測試 RR → 左旋
        Node rr = makeRRCase();
        System.out.println("RR Case Before Rotation:");
        preOrder(rr);
        rr = rotateLeft(rr);
        System.out.println("\nAfter Left Rotation:");
        preOrder(rr);

        // 測試 LL → 右旋
        Node ll = makeLLCase();
        System.out.println("\n\nLL Case Before Rotation:");
        preOrder(ll);
        ll = rotateRight(ll);
        System.out.println("\nAfter Right Rotation:");
        preOrder(ll);

        // 測試 LR → 左右旋
        Node lr = makeLRCase();
        System.out.println("\n\nLR Case Before Rotation:");
        preOrder(lr);
        lr = rotateLeftRight(lr);
        System.out.println("\nAfter Left-Right Rotation:");
        preOrder(lr);

        // 測試 RL → 右左旋
        Node rl = makeRLCase();
        System.out.println("\n\nRL Case Before Rotation:");
        preOrder(rl);
        rl = rotateRightLeft(rl);
        System.out.println("\nAfter Right-Left Rotation:");
        preOrder(rl);
    }
}