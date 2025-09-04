// 檔案：RBDeleteFixupExercise.java
import java.util.*;

public class RBDeleteFixupExercise {

    enum Color { RED, BLACK }

    static class Node {
        int key;
        Color color;
        Node left, right, parent;

        Node(int key) {
            this.key = key;
            this.color = Color.RED; // 預設新節點為紅色
        }
    }

    static class RBTree {
        private Node root;

        // 左旋
        private void rotateLeft(Node x) {
            Node y = x.right;
            x.right = y.left;
            if (y.left != null) y.left.parent = x;

            y.parent = x.parent;
            if (x.parent == null) root = y;
            else if (x == x.parent.left) x.parent.left = y;
            else x.parent.right = y;

            y.left = x;
            x.parent = y;
        }

        // 右旋
        private void rotateRight(Node y) {
            Node x = y.left;
            y.left = x.right;
            if (x.right != null) x.right.parent = y;

            x.parent = y.parent;
            if (y.parent == null) root = x;
            else if (y == y.parent.left) y.parent.left = x;
            else y.parent.right = x;

            x.right = y;
            y.parent = x;
        }

        // 標準 BST 查找最小值
        private Node minimum(Node node) {
            while (node.left != null) node = node.left;
            return node;
        }

        // 刪除節點
        public void delete(int key) {
            Node z = root;
            while (z != null && z.key != key) {
                if (key < z.key) z = z.left;
                else z = z.right;
            }
            if (z == null) return; // 不存在

            Node y = z; // 實際刪除的節點
            Color yOriginalColor = y.color;
            Node x;

            if (z.left == null) {
                x = z.right;
                transplant(z, z.right);
            } else if (z.right == null) {
                x = z.left;
                transplant(z, z.left);
            } else {
                y = minimum(z.right);
                yOriginalColor = y.color;
                x = y.right;
                if (y.parent == z) {
                    if (x != null) x.parent = y;
                } else {
                    transplant(y, y.right);
                    y.right = z.right;
                    y.right.parent = y;
                }
                transplant(z, y);
                y.left = z.left;
                y.left.parent = y;
                y.color = z.color;
            }

            if (yOriginalColor == Color.BLACK) {
                deleteFixup(x, y.parent); // 修復黑色節點刪除違規
            }
        }

        // 節點替換
        private void transplant(Node u, Node v) {
            if (u.parent == null) root = v;
            else if (u == u.parent.left) u.parent.left = v;
            else u.parent.right = v;
            if (v != null) v.parent = u.parent;
        }

        // 刪除修復
        private void deleteFixup(Node x, Node parent) {
            while ((x != root) && (x == null || x.color == Color.BLACK)) {
                if (x == parent.left) {
                    Node w = parent.right; // 兄弟節點
                    if (w != null && w.color == Color.RED) { // Case 1: 兄弟為紅
                        w.color = Color.BLACK;
                        parent.color = Color.RED;
                        rotateLeft(parent);
                        w = parent.right;
                    }
                    if ((w.left == null || w.left.color == Color.BLACK) &&
                        (w.right == null || w.right.color == Color.BLACK)) { // Case 2: 兄弟黑且子都黑
                        if (w != null) w.color = Color.RED;
                        x = parent;
                        parent = x.parent;
                    } else {
                        if (w.right == null || w.right.color == Color.BLACK) { // Case 3: 兄弟黑，左紅右黑
                            if (w.left != null) w.left.color = Color.BLACK;
                            w.color = Color.RED;
                            rotateRight(w);
                            w = parent.right;
                        }
                        // Case 4: 兄弟黑，右子紅
                        if (w != null) w.color = parent.color;
                        parent.color = Color.BLACK;
                        if (w.right != null) w.right.color = Color.BLACK;
                        rotateLeft(parent);
                        x = root;
                    }
                } else { // 對稱情況
                    Node w = parent.left;
                    if (w != null && w.color == Color.RED) { // Case 1
                        w.color = Color.BLACK;
                        parent.color = Color.RED;
                        rotateRight(parent);
                        w = parent.left;
                    }
                    if ((w.left == null || w.left.color == Color.BLACK) &&
                        (w.right == null || w.right.color == Color.BLACK)) { // Case 2
                        if (w != null) w.color = Color.RED;
                        x = parent;
                        parent = x.parent;
                    } else {
                        if (w.left == null || w.left.color == Color.BLACK) { // Case 3
                            if (w.right != null) w.right.color = Color.BLACK;
                            w.color = Color.RED;
                            rotateLeft(w);
                            w = parent.left;
                        }
                        // Case 4
                        if (w != null) w.color = parent.color;
                        parent.color = Color.BLACK;
                        if (w.left != null) w.left.color = Color.BLACK;
                        rotateRight(parent);
                        x = root;
                    }
                }
            }
            if (x != null) x.color = Color.BLACK;
        }

        // 中序列印 (key:color)
        public void inorderPrint() {
            inorderPrint(root);
            System.out.println();
        }

        private void inorderPrint(Node node) {
            if (node == null) return;
            inorderPrint(node.left);
            System.out.print(node.key + ":" + (node.color == Color.RED ? "R" : "B") + " ");
            inorderPrint(node.right);
        }
    }

    // ===== 測試 =====
    public static void main(String[] args) {
        RBTree tree = new RBTree();
        int[] keys = {20, 10, 30, 5, 15, 25, 35};
        for (int key : keys) tree.insert(key);

        System.out.print("初始樹: ");
        tree.inorderPrint();

        int[] deleteKeys = {5, 15, 30};
        for (int key : deleteKeys) {
            System.out.println("刪除 " + key);
            tree.delete(key);
            tree.inorderPrint();
        }
    }
}