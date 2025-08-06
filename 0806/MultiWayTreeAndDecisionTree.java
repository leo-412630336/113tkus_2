import java.util.*;

class Node {
    String value;
    List<Node> children;

    public Node(String value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }
}

public class MultiWayTreeAndDecisionTree {

    public static void main(String[] args) {
        System.out.println("--- 實作多路樹 ---");
        Node root = buildMultiWayTree();

        System.out.println("\n深度優先走訪 (DFS): ");
        dfs(root);
        System.out.println("\n廣度優先走訪 (BFS): ");
        bfs(root);

        System.out.println("\n--- 計算樹的屬性 ---");
        System.out.println("樹的高度: " + calculateHeight(root));
        System.out.println("根節點的度數: " + calculateDegree(root));
        System.out.println("第二個子節點 'B' 的度數: " + calculateDegree(root.children.get(1)));

        System.out.println("\n--- 模擬決策樹：猜數字遊戲 ---");
        Node decisionTreeRoot = buildDecisionTree();
        System.out.println("決策過程:");
        simulateDecisionTree(decisionTreeRoot);
    }

    public static Node buildMultiWayTree() {
        Node root = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");
        Node g = new Node("G");
        Node h = new Node("H");
        Node i = new Node("I");

        root.addChild(b);
        root.addChild(c);
        root.addChild(d);

        b.addChild(e);
        b.addChild(f);

        c.addChild(g);

        d.addChild(h);
        d.addChild(i);

        return root;
    }

    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        for (Node child : node.children) {
            dfs(child);
        }
    }

    public static void bfs(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.value + " ");
            for (Node child : node.children) {
                queue.add(child);
            }
        }
    }

    public static int calculateHeight(Node node) {
        if (node == null || node.children.isEmpty()) {
            return 0; 
        }

        int maxHeight = 0;
        for (Node child : node.children) {
            maxHeight = Math.max(maxHeight, calculateHeight(child));
        }

        return maxHeight + 1;
    }

    public static int calculateDegree(Node node) {
        if (node == null) {
            return 0;
        }
        return node.children.size();
    }

    public static Node buildDecisionTree() {
        Node root = new Node("數字是單數還是雙數?");
        Node odd = new Node("單數");
        Node even = new Node("雙數");

        root.addChild(odd);
        root.addChild(even);

        Node oddResult = new Node("是 3 或 7?");
        Node evenResult = new Node("是 2 或 4?");

        odd.addChild(oddResult);
        even.addChild(evenResult);

        oddResult.addChild(new Node("猜對了，是 3！"));
        oddResult.addChild(new Node("猜對了，是 7！"));

        evenResult.addChild(new Node("猜對了，是 2！"));
        evenResult.addChild(new Node("猜對了，是 4！"));

        return root;
    }

    public static void simulateDecisionTree(Node node) {
        if (node.children.isEmpty()) {
            System.out.println("-> " + node.value);
            return;
        }

        System.out.println(node.value);

        Node nextDecision = node.children.get(0);
        System.out.println("我選擇: " + nextDecision.value);
        simulateDecisionTree(nextDecision);
    }
}