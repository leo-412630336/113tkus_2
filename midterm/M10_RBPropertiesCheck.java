import java.util.*;

public class M10_RBPropertiesCheck {
    static class Node {
        int val;
        char color;
        Node(int v, char c) { val = v; color = c; }
    }

    static Node[] tree;

    private static boolean redRedViolation(int idx) {
        if (idx >= tree.length || tree[idx] == null) return false;
        Node node = tree[idx];
        int l = 2 * idx + 1, r = 2 * idx + 2;
        if (node.color == 'R') {
            if ((l < tree.length && tree[l] != null && tree[l].color == 'R') ||
                (r < tree.length && tree[r] != null && tree[r].color == 'R'))
                return true;
        }
        return redRedViolation(l) || redRedViolation(r);
    }

    private static int blackHeight(int idx) {
        if (idx >= tree.length || tree[idx] == null) return 1;
        int lbh = blackHeight(2 * idx + 1);
        int rbh = blackHeight(2 * idx + 2);
        if (lbh == -1 || rbh == -1 || lbh != rbh) return -1;
        return lbh + (tree[idx].color == 'B' ? 1 : 0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        tree = new Node[n];
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            String col = sc.next();
            if (val == -1) tree[i] = null;
            else tree[i] = new Node(val, col.charAt(0));
        }

        if (tree[0] == null || tree[0].color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (tree[i] != null && tree[i].color == 'R') {
                int l = 2 * i + 1, r = 2 * i + 2;
                if ((l < n && tree[l] != null && tree[l].color == 'R') ||
                    (r < n && tree[r] != null && tree[r].color == 'R')) {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
            }
        }

        if (blackHeight(0) == -1) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }
}

/*
時間複雜度：O(n)
空間複雜度：O(n)
*/