
public class BSTConversionAndBalance {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    static class DoublyListNode {
        int val;
        DoublyListNode prev, next;
        DoublyListNode(int val) {
            this.val = val;
        }
    }

    private DoublyListNode head = null, prev = null;

    public DoublyListNode bstToDoublyLinkedList(TreeNode root) {
        inOrderConvert(root);
        return head;
    }

    private void inOrderConvert(TreeNode node) {
        if (node == null) return;
        inOrderConvert(node.left);
        DoublyListNode curr = new DoublyListNode(node.val);
        if (prev == null) {
            head = curr;
        } else {
            prev.next = curr;
            curr.prev = prev;
        }
        prev = curr;
        inOrderConvert(node.right);
    }

    public TreeNode sortedArrayToBST(int[] arr) {
        return buildBST(arr, 0, arr.length - 1);
    }

    private TreeNode buildBST(int[] arr, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = buildBST(arr, left, mid - 1);
        node.right = buildBST(arr, mid + 1, right);
        return node;
    }

    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int left = checkHeight(node.left);
        int right = checkHeight(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1; 
        }
        return Math.max(left, right) + 1;
    }

    private int runningSum = 0;

    public void convertToGreaterSumTree(TreeNode root) {
        reverseInOrder(root);
    }

    private void reverseInOrder(TreeNode node) {
        if (node == null) return;
        reverseInOrder(node.right);
        runningSum += node.val;
        node.val = runningSum;
        reverseInOrder(node.left);
    }

    public static void main(String[] args) {
        BSTConversionAndBalance obj = new BSTConversionAndBalance();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        DoublyListNode head = obj.bstToDoublyLinkedList(root);
        System.out.print("雙向串列：");
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();

        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balancedBST = obj.sortedArrayToBST(arr);
        System.out.println("平衡 BST 高度是否合理：" + obj.isBalanced(balancedBST));

        System.out.println("原始 BST 是否平衡：" + obj.isBalanced(root));

        obj.convertToGreaterSumTree(root);
        System.out.print("Greater Sum Tree 中序：");
        printInOrder(root);
    }

    public static void printInOrder(TreeNode node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.print(node.val + " ");
        printInOrder(node.right);
    }
}