import java.util.Scanner;

// 定義鏈結串列節點
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}

public class LC24_SwapPairs_Shifts {

    /**
     * @param head 鏈結串列的頭節點
     * @return 交換後的新頭節點
     *
     * 時間複雜度: O(n) - 只需要遍歷鏈結串列一次。
     * 空間複雜度: O(1) - 只使用了幾個額外的指針。
     */
    public static ListNode swapPairs(ListNode head) {
        // 建立虛擬頭節點，簡化邊界處理
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // 檢查是否有至少兩個節點可以交換
        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = prev.next.next;

            // 執行交換
            prev.next = b;
            a.next = b.next;
            b.next = a;

            // 將 prev 指針移到新的位置，準備下一輪交換
            prev = a;
        }

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
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("請輸入班表時段的數量 n:");
        int n = scanner.nextInt();
        
        System.out.println("請輸入 " + n + " 個時段的值，以空格分隔:");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        
        scanner.close();

        ListNode head = createLinkedList(arr);
        System.out.print("原始班表序列為: ");
        printLinkedList(head);

        ListNode swappedHead = swapPairs(head);
        System.out.print("交換後班表序列為: ");
        printLinkedList(swappedHead);
    }
}