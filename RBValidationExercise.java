// 檔案：RBValidationExercise.java
import java.util.*;

public class RBValidationExercise {

    // 顏色列舉
    enum Color { RED, BLACK }

    // 紅黑樹節點
    static class Node {
        final int key;
        final Color color;
        final Node left, right;

        Node(int key, Color color, Node left, Node right) {
            this.key = key;
            this.color = color;
            this.left = left;
            this.right = right;
        }
    }

    // 驗證結果
    public static class ValidationResult {
        public final boolean isValid;
        public final List<String> errors;
        public final int blackHeight; // 若有效，為整棵樹的黑高度；否則為 -1

        ValidationResult(boolean isValid, List<String> errors, int blackHeight) {
            this.isValid = isValid;
            this.errors = errors;
            this.blackHeight = blackHeight;
        }

        @Override
        public String toString() {
            return "isValid=" + isValid + ", blackHeight=" + blackHeight + ", errors=" + errors;
        }
    }

    // === 便利建樹工具（黑/紅 節點建構） ===
    public static Node B(int key, Node l, Node r) { return new Node(key, Color.BLACK, l, r); }
    public static Node R(int key, Node l, Node r) { return new Node(key, Color.RED,   l, r); }

    /**
     * 驗證入口
     * 黑高度定義：將 null 視為黑色 NIL，故 null 的黑高度為 1。
     */
    public static ValidationResult validate(Node root) {
        List<String> errors = new ArrayList<>();

        // 根節點必為黑（空樹亦視為有效）
        if (root != null && root.color != Color.BLACK) {
            errors.add("根節點必須為黑色，但實際為 " + root.color + "（key=" + root.key + "）");
        }

        int bh = dfsValidate(root, "root", errors);
        boolean ok = errors.isEmpty();
        return new ValidationResult(ok, errors, ok ? bh : -1);
    }

    /**
     * 回傳：若子樹有效，回傳該子樹的黑高度；若無效，回傳 -1。
     * 路徑字串例如：root、root->L、root->R->L ...
     */
    private static int dfsValidate(Node node, String path, List<String> errors) {
        if (node == null) {
            // NIL 葉子：黑高度 = 1 （計入 NIL 本身）
            return 1;
        }

        // 顏色存在性（理論上用 enum 不會為 null，但以防萬一）
        if (node.color == null) {
            errors.add("節點顏色為 null（path=" + path + ", key=" + node.key + "）");
        }

        // 紅節點的兩個子節點必須為黑（或為 null 視為黑）
        if (node.color == Color.RED) {
            if (node.left != null && node.left.color == Color.RED) {
                errors.add("紅節點的左子節點亦為紅色（紅紅相鄰）：key=" + node.key +
                           " 左子 key=" + node.left.key + "（path=" + path + "）");
            }
            if (node.right != null && node.right.color == Color.RED) {
                errors.add("紅節點的右子節點亦為紅色（紅紅相鄰）：key=" + node.key +
                           " 右子 key=" + node.right.key + "（path=" + path + "）");
            }
        }

        int leftBH  = dfsValidate(node.left,  path + "->L", errors);
        int rightBH = dfsValidate(node.right, path + "->R", errors);

        // 子樹若已判定無效，向上傳遞 -1
        if (leftBH == -1 || rightBH == -1) return -1;

        // 黑高度一致性：左右黑高度必須相等
        if (leftBH != rightBH) {
            errors.add("黑高度不一致於節點 key=" + node.key + "（path=" + path +
                       "）：leftBH=" + leftBH + ", rightBH=" + rightBH);
            return -1;
        }

        // 本節點之黑高度 = 子黑高度 + (本節點是否黑色)
        return leftBH + (node.color == Color.BLACK ? 1 : 0);
    }

    // ===== 一些測試樹 =====

    // 合法案例：
    //        10(B)
    //       /      \
    //    5(R)     15(R)
    public static Node validExample() {
        return B(10,
                R(5,  null, null),
                R(15, null, null));
    }

    // 錯誤：根為紅
    public static Node redRootExample() {
        return R(10, null, null);
    }

    // 錯誤：紅紅相鄰（5(R) 的左子 3 亦為紅）
    //         10(B)
    //        /      \
    //     5(R)     15(B)
    //     /
    //   3(R)
    public static Node redRedExample() {
        return B(10,
                R(5, R(3, null, null), null),
                B(15, null, null));
    }

    // 錯誤：黑高度不一致
    //       10(B)
    //      /
    //    5(B)
    public static Node blackHeightMismatchExample() {
        return B(10, B(5, null, null), null);
    }

    // ===== 簡單測試 =====
    public static void main(String[] args) {
        List<Node> cases = Arrays.asList(
                validExample(),
                redRootExample(),
                redRedExample(),
                blackHeightMismatchExample()
        );
        String[] names = {
                "Valid Example",
                "Red Root Example",
                "Red-Red Adjacent Example",
                "Black-Height Mismatch Example"
        };

        for (int i = 0; i < cases.size(); i++) {
            ValidationResult vr = validate(cases.get(i));
            System.out.println("[" + names[i] + "] " + vr);
        }
    }
}