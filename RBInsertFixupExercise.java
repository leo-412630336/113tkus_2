// 檔案：RBInsertFixupExercise.java

public class RBInsertFixupExercise {

    enum Color { RED, BLACK }

    static class Node {
        int key;
        Color color;
        Node left, right, parent;

        Node(int key) {
            this.key = key;
            this.color = Color.RED; // 插入預設為紅色
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

        // 插入節點
        public void insert(int key) {
            Node newNode = new Node(key);
            if (root == null) {
                root = newNode;
                root.color = Color.BLACK;
                return;
            }

            Node current = root, parent = null;
            while (current != null) {
                parent = current;
                if (key < current.key) current = current.left;
                else current = current.right;
            }

            newNode.parent = parent;
            if (key < parent.key) parent.left = newNode;
            else parent.right = newNode;

            insertFixup(newNode);
        }

        // 插入後修復
        private void insertFixup(Node z) {
            while (z.parent != null && z.parent.color == Color.RED) {
                if (z.parent == z.parent.parent.left) {
                    Node y = z.parent.parent.right; // 叔叔
                    if (y != null && y.color == Color.RED) { // Case 1
                        z.parent.color = Color.BLACK;
                        y.color = Color.BLACK;
                        z.parent.parent.color = Color.RED;
                        z = z.parent.parent;
                    } else {
                        if (z == z.parent.right) { // Case 2
                            z = z.parent;
                            rotateLeft(z);
                        }
                        // Case 3
                        z.parent.color = Color.BLACK;
                        z.parent.parent.color = Color.RED;
                        rotateRight(z.parent.parent);
                    }
                } else { // 對稱情況
                    Node y = z.parent.parent.left;
                    if (y != null && y.color == Color.RED) { // Case 1
                        z.parent.color = Color.BLACK;
                        y.color = Color.BLACK;
                        z.parent.parent.color = Color.RED;
                        z = z.parent.parent;
                    } else {
                        if (z == z.parent.left) { // Case 2
                            z = z.parent;
                            rotateRight(z);
                        }
                        // Case 3
                        z.parent.color = Color.BLACK;
                        z.parent.parent.color = Color.RED;
                        rotateLeft(z.parent.parent);
                    }
                }
            }
            root.color = Color.BLACK;
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
        int[] keys = {10, 20, 30, 15, 25, 5, 1};

        for (int key : keys) {
            tree.insert(key);
            System.out.print("插入 " + key + " -> ");
            tree.inorderPrint();
        }
    }
}