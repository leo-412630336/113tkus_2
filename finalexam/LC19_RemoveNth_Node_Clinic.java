import java.util.Scanner;

// 定義鏈結串列節點
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}

public class LC19_RemoveNth_Node_Clinic {

    /**
     * @param head 鏈結串列的頭節點
     * @param k 倒數第 k 個節點
     * @return 刪除節點後的新鏈結串列頭節點
     *
     * 時間複雜度: O(n) - 兩個指針都只遍歷一次鏈結串列。
     * 空間複雜度: O(1) - 只使用了幾個額外指針。
     */
    public static ListNode removeNthFromEnd(ListNode head, int k) {
        // 建立一個虛擬頭節點，簡化邊界處理
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;

        // 快指針先走 k 步
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        // 雙指針同步移動，直到 fast 到達尾部
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // 刪除節點
        slow.next = slow.next.next;

        return dummy.next;
    }

    // 輔助函式：從陣列建立鏈結串列
    public static ListNode createLinkedList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    // 輔助函式：列印鏈結串列
    public static void printLinkedList(ListNode head) {
        if (head == null) {
            System.out.println("鏈結串列已空");
            return;
        }
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("請輸入節點總數 n:");
        int n = scanner.nextInt();
        
        System.out.println("請輸入 " + n + " 個節點的值，以空格分隔:");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println("請輸入要刪除的倒數第 k 筆資料（1 <= k <= n）:");
        int k = scanner.nextInt();
        
        scanner.close();

        ListNode head = createLinkedList(arr);
        System.out.print("原始序列為: ");
        printLinkedList(head);

        ListNode newHead = removeNthFromEnd(head, k);
        System.out.print("刪除後序列為: ");
        printLinkedList(newHead);
    }
}