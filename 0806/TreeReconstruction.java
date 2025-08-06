import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode buildTreeFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inIndexMap.put(inorder[i], i);
        }
        return buildPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inIndexMap);
    }

    private static TreeNode buildPreIn(int[] preorder, int preL, int preR,
                                       int[] inorder, int inL, int inR,
                                       Map<Integer, Integer> inMap) {
        if (preL > preR || inL > inR) return null;

        int rootVal = preorder[preL];
        TreeNode root = new TreeNode(rootVal);
        int inRootIndex = inMap.get(rootVal);
        int leftSize = inRootIndex - inL;

        root.left = buildPreIn(preorder, preL + 1, preL + leftSize, inorder, inL, inRootIndex - 1, inMap);
        root.right = buildPreIn(preorder, preL + leftSize + 1, preR, inorder, inRootIndex + 1, inR, inMap);

        return root;
    }

    public static TreeNode buildTreeFromPostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inIndexMap.put(inorder[i], i);
        }
        return buildPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inIndexMap);
    }

    private static TreeNode buildPostIn(int[] postorder, int postL, int postR,
                                        int[] inorder, int inL, int inR,
                                        Map<Integer, Integer> inMap) {
        if (postL > postR || inL > inR) return null;

        int rootVal = postorder[postR];
        TreeNode root = new TreeNode(rootVal);
        int inRootIndex = inMap.get(rootVal);
        int leftSize = inRootIndex - inL;

        root.left = buildPostIn(postorder, postL, postL + leftSize - 1, inorder, inL, inRootIndex - 1, inMap);
        root.right = buildPostIn(postorder, postL + leftSize, postR - 1, inorder, inRootIndex + 1, inR, inMap);

        return root;
    }

    public static TreeNode buildCompleteTreeFromLevelOrder(Integer[] levelOrder) {
        if (levelOrder.length == 0 || levelOrder[0] == null) return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < levelOrder.length) {
            TreeNode current = queue.poll();
            if (i < levelOrder.length && levelOrder[i] != null) {
                current.left = new TreeNode(levelOrder[i]);
                queue.offer(current.left);
            }
            i++;
            if (i < levelOrder.length && levelOrder[i] != null) {
                current.right = new TreeNode(levelOrder[i]);
                queue.offer(current.right);
            }
            i++;
        }
        return root;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private static void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inorderHelper(node.left, result);
        result.add(node.val);
        inorderHelper(node.right, result);
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode tree1 = buildTreeFromPreIn(preorder, inorder);
        System.out.println("前序+中序建樹的中序走訪: " + inorderTraversal(tree1));

        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode tree2 = buildTreeFromPostIn(postorder, inorder);
        System.out.println("後序+中序建樹的中序走訪: " + inorderTraversal(tree2));

        Integer[] levelOrder = {1, 2, 3, 4, 5, 6, 7};
        TreeNode tree3 = buildCompleteTreeFromLevelOrder(levelOrder);
        System.out.println("層序建完全樹的中序走訪: " + inorderTraversal(tree3));
    }
}