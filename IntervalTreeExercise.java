import java.util.*;

public class IntervalTreeExercise {

    enum Color { RED, BLACK }

    static class Interval {
        int start, end;
        Interval(int s, int e) { start = s; end = e; }
        public String toString() { return "[" + start + "," + end + "]"; }
    }

    static class Node {
        Interval interval;
        int max; // 子樹最大端點
        Color color;
        Node left, right, parent;

        Node(Interval interval) {
            this.interval = interval;
            this.max = interval.end;
            this.color = Color.RED;
        }
    }

    static class IntervalTree {
        private Node root;

        // 插入區間
        public void insert(Interval interval) {
            Node z = new Node(interval);
            Node y = null, x = root;
            while (x != null) {
                y = x;
                x.max = Math.max(x.max, z.max); // 更新max
                if (z.interval.start < x.interval.start) x = x.left;
                else x = x.right;
            }
            z.parent = y;
            if (y == null) root = z;
            else if (z.interval.start < y.interval.start) y.left = z;
            else y.right = z;
            insertFixup(z);
        }

        // 紅黑樹插入修復
        private void insertFixup(Node z) {
            while (z.parent != null && z.parent.color == Color.RED) {
                Node gp = z.parent.parent;
                if (z.parent == gp.left) {
                    Node y = gp.right;
                    if (y != null && y.color == Color.RED) {
                        z.parent.color = Color.BLACK;
                        y.color = Color.BLACK;
                        gp.color = Color.RED;
                        z = gp;
                    } else {
                        if (z == z.parent.right) {
                            z = z.parent;
                            rotateLeft(z);
                        }
                        z.parent.color = Color.BLACK;
                        gp.color = Color.RED;
                        rotateRight(gp);
                    }
                } else {
                    Node y = gp.left;
                    if (y != null && y.color == Color.RED) {
                        z.parent.color = Color.BLACK;
                        y.color = Color.BLACK;
                        gp.color = Color.RED;
                        z = gp;
                    } else {
                        if (z == z.parent.left) {
                            z = z.parent;
                            rotateRight(z);
                        }
                        z.parent.color = Color.BLACK;
                        gp.color = Color.RED;
                        rotateLeft(gp);
                    }
                }
            }
            root.color = Color.BLACK;
        }

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
            updateMax(x);
            updateMax(y);
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
            updateMax(y);
            updateMax(x);
        }

        // 刪除區間
        public void delete(Interval interval) {
            Node z = search(root, interval);
            if (z == null) return;
            Node y = z, x;
            Color yOriginalColor = y.color;
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
                updateMax(y);
            }
            updateMaxUpward(x != null ? x.parent : null);
            if (yOriginalColor == Color.BLACK) {
                deleteFixup(x, (x != null ? x.parent : null));
            }
        }

        // 節點替換
        private void transplant(Node u, Node v) {
            if (u.parent == null) root = v;
            else if (u == u.parent.left) u.parent.left = v;
            else u.parent.right = v;
            if (v != null) v.parent = u.parent;
        }

        // 查找最小
        private Node minimum(Node node) {
            while (node.left != null) node = node.left;
            return node;
        }

        // 查找區間節點
        private Node search(Node node, Interval interval) {
            while (node != null) {
                if (interval.start == node.interval.start && interval.end == node.interval.end) return node;
                if (interval.start < node.interval.start) node = node.left;
                else node = node.right;
            }
            return null;
        }

        // 刪除修復
        private void deleteFixup(Node x, Node parent) {
            while (x != root && (x == null || x.color == Color.BLACK)) {
                if (parent == null) break;
                if (x == parent.left) {
                    Node w = parent.right;
                    if (w != null && w.color == Color.RED) {
                        w.color = Color.BLACK;
                        parent.color = Color.RED;
                        rotateLeft(parent);
                        w = parent.right;
                    }
                    if ((w.left == null || w.left.color == Color.BLACK) &&
                        (w.right == null || w.right.color == Color.BLACK)) {
                        if (w != null) w.color = Color.RED;
                        x = parent;
                        parent = x.parent;
                    } else {
                        if (w.right == null || w.right.color == Color.BLACK) {
                            if (w.left != null) w.left.color = Color.BLACK;
                            w.color = Color.RED;
                            rotateRight(w);
                            w = parent.right;
                        }
                        if (w != null) w.color = parent.color;
                        parent.color = Color.BLACK;
                        if (w.right != null) w.right.color = Color.BLACK;
                        rotateLeft(parent);
                        x = root;
                    }
                } else {
                    Node w = parent.left;
                    if (w != null && w.color == Color.RED) {
                        w.color = Color.BLACK;
                        parent.color = Color.RED;
                        rotateRight(parent);
                        w = parent.left;
                    }
                    if ((w.left == null || w.left.color == Color.BLACK) &&
                        (w.right == null || w.right.color == Color.BLACK)) {
                        if (w != null) w.color = Color.RED;
                        x = parent;
                        parent = x.parent;
                    } else {
                        if (w.left == null || w.left.color == Color.BLACK) {
                            if (w.right != null) w.right.color = Color.BLACK;
                            w.color = Color.RED;
                            rotateLeft(w);
                            w = parent.left;
                        }
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

        // 更新max
        private void updateMax(Node node) {
            if (node == null) return;
            node.max = node.interval.end;
            if (node.left != null) node.max = Math.max(node.max, node.left.max);
            if (node.right != null) node.max = Math.max(node.max, node.right.max);
        }

        // 往上更新max
        private void updateMaxUpward(Node node) {
            while (node != null) {
                updateMax(node);
                node = node.parent;
            }
        }

        // 查詢重疊區間
        public List<Interval> searchOverlap(Interval target) {
            List<Interval> res = new ArrayList<>();
            searchOverlap(root, target, res);
            return res;
        }

        private void searchOverlap(Node node, Interval target, List<Interval> res) {
            if (node == null) return;
            if (isOverlap(node.interval, target)) res.add(node.interval);
            if (node.left != null && node.left.max >= target.start)
                searchOverlap(node.left, target, res);
            if (node.right != null && node.interval.start <= target.end)
                searchOverlap(node.right, target, res);
        }

        // 查詢包含點的所有區間
        public List<Interval> searchPoint(int point) {
            List<Interval> res = new ArrayList<>();
            searchPoint(root, point, res);
            return res;
        }

        private void searchPoint(Node node, int point, List<Interval> res) {
            if (node == null) return;
            if (node.interval.start <= point && point <= node.interval.end)
                res.add(node.interval);
            if (node.left != null && node.left.max >= point)
                searchPoint(node.left, point, res);
            if (node.right != null && node.interval.start <= point)
                searchPoint(node.right, point, res);
        }

        // 判斷重疊
        private boolean isOverlap(Interval a, Interval b) {
            return a.start <= b.end && b.start <= a.end;
        }

        // 中序列印
        public void inorderPrint() {
            inorderPrint(root);
            System.out.println();
        }

        private void inorderPrint(Node node) {
            if (node == null) return;
            inorderPrint(node.left);
            System.out.print(node.interval + "(max=" + node.max + ") ");
            inorderPrint(node.right);
        }
    }

    // ===== 測試 =====
    public static void main(String[] args) {
        IntervalTree tree = new IntervalTree();
        tree.insert(new Interval(15, 20));
        tree.insert(new Interval(10, 30));
        tree.insert(new Interval(17, 19));
        tree.insert(new Interval(5, 20));
        tree.insert(new Interval(12, 15));
        tree.insert(new Interval(30, 40));

        System.out.print("中序: ");
        tree.inorderPrint();

        Interval query = new Interval(14, 16);
        System.out.println("重疊 " + query + ": " + tree.searchOverlap(query));

        int point = 16;
        System.out.println("包含點 " + point + ": " + tree.searchPoint(point));

        tree.delete(new Interval(10, 30));
        System.out.print("刪除[10,30]後: ");
        tree.inorderPrint();
    }
}