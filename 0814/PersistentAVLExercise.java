import java.util.*;

public class PersistentAVLExercise {

    // 不可變節點
    static class Node {
        final int key;
        final int height;
        final Node left;
        final Node right;

        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = Math.max(height(left), height(right)) + 1;
        }
    }

    private final List<Node> versions = new ArrayList<>();

    public PersistentAVLExercise() {
        versions.add(null); // 版本0 = 空樹
    }

    public void insert(int versionIndex, int key) {
        Node oldRoot = versions.get(versionIndex);
        Node newRoot = insertImmutable(oldRoot, key);
        versions.add(newRoot);
    }

    private Node insertImmutable(Node node, int key) {
        if (node == null) {
            return new Node(key, null, null);
        }
        if (key < node.key) {
            return balance(new Node(node.key, insertImmutable(node.left, key), node.right));
        } else if (key > node.key) {
            return balance(new Node(node.key, node.left, insertImmutable(node.right, key)));
        } else {
            return node; // 不允許重複
        }
    }

    // AVL 平衡維護
    private Node balance(Node node) {
        int bf = balanceFactor(node);
        if (bf > 1) { // 左高
            if (balanceFactor(node.left) < 0) {
                return rotateLeftRight(node);
            } else {
                return rotateRight(node);
            }
        } else if (bf < -1) { // 右高
            if (balanceFactor(node.right) > 0) {
                return rotateRightLeft(node);
            } else {
                return rotateLeft(node);
            }
        }
        return node;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node t2 = x.right;
        return new Node(x.key, x.left, new Node(y.key, t2, y.right));
    }

    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node t2 = x.left;
        return new Node(x.key, new Node(y.key, y.left, t2), x.right);
    }

    private Node rotateLeftRight(Node node) {
        return rotateRight(new Node(node.key, rotateLeft(node.left), node.right));
    }

    private Node rotateRightLeft(Node node) {
        return rotateLeft(new Node(node.key, node.left, rotateRight(node.right)));
    }

    private int balanceFactor(Node node) {
        return height(node.left) - height(node.right);
    }

    private static int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // 查詢某版本
    public boolean search(int versionIndex, int key) {
        Node root = versions.get(versionIndex);
        while (root != null) {
            if (key < root.key) root = root.left;
            else if (key > root.key) root = root.right;
            else return true;
        }
        return false;
    }

    public int getVersionCount() {
        return versions.size();
    }

    // 測試
    public static void main(String[] args) {
        PersistentAVLExercise tree = new PersistentAVLExercise();
        tree.insert(0, 10); // 版本1
        tree.insert(1, 20); // 版本2
        tree.insert(2, 5);  // 版本3

        System.out.println("版本數量: " + tree.getVersionCount());
        System.out.println("版本1 有 5? " + tree.search(1, 5)); // false
        System.out.println("版本3 有 5? " + tree.search(3, 5)); // true
    }
}
