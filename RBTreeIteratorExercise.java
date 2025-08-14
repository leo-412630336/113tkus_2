package 0814;

import java.util.*;

public class RBTreeIteratorExercise {

    enum Color { RED, BLACK }

    static class Node {
        int key;
        Color color;
        Node left, right, parent;

        Node(int key) {
            this.key = key;
            this.color = Color.RED;
        }
    }

    static class RBTree {
        Node root;
        // ...紅黑樹插入、刪除等方法...
    }

    // ===== 紅黑樹迭代器 =====
    static class RBTreeIterator implements Iterator<Integer> {
        private Stack<Node> stack = new Stack<>();
        private boolean reverse;
        private Integer lower, upper;

        public RBTreeIterator(Node root, boolean reverse) {
            this(root, reverse, null, null);
        }

        public RBTreeIterator(Node root, boolean reverse, Integer lower, Integer upper) {
            this.reverse = reverse;
            this.lower = lower;
            this.upper = upper;
            pushAll(root);
        }

        private void pushAll(Node node) {
            while (node != null) {
                if (reverse) {
                    // 反向遍歷：先右
                    if (upper != null && node.key > upper) {
                        node = node.left;
                    } else {
                        stack.push(node);
                        node = node.right;
                    }
                } else {
                    // 順序遍歷：先左
                    if (lower != null && node.key < lower) {
                        node = node.right;
                    } else {
                        stack.push(node);
                        node = node.left;
                    }
                }
            }
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                Node node = stack.peek();
                if ((lower != null && node.key < lower) ||
                    (upper != null && node.key > upper)) {
                    stack.pop();
                    if (reverse) pushAll(node.left);
                    else pushAll(node.right);
                } else {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node node = stack.pop();
            if (reverse) pushAll(node.left);
            else pushAll(node.right);
            return node.key;
        }
    }

    // ===== 測試 =====
    public static void main(String[] args) {
        RBTree tree = new RBTree();
        // 假設已插入資料
        // tree.insert(...);

        // 中序遍歷
        RBTreeIterator inorder = new RBTreeIterator(tree.root, false);
        System.out.print("中序: ");
        while (inorder.hasNext()) System.out.print(inorder.next() + " ");
        System.out.println();

        // 反向遍歷
        RBTreeIterator reverse = new RBTreeIterator(tree.root, true);
        System.out.print("反向: ");
        while (reverse.hasNext()) System.out.print(reverse.next() + " ");
        System.out.println();

        // 範圍遍歷 [10, 30]
        RBTreeIterator range = new RBTreeIterator(tree.root, false, 10, 30);
        System.out.print("範圍[10,30]: ");
        while (range.hasNext()) System.out.print(range.next() + " ");
        System.out.println();
    }
}
