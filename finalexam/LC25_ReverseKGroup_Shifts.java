import java.util.Scanner;

// 定義鏈結串列節點
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}

public class LC25_ReverseKGroup_Shifts {

    /**
     * @param head 鏈結串列的頭節點
     * @param k 每組反轉的節點數
     * @return 反轉後的新頭節點
     *
     * 時間複雜度: O(n) - 每個節點最多被處理兩次（檢查長度和反轉）。
     * 空間複雜度: O(1) - 只使用了幾個額外的指針。
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        // 建立虛擬頭節點，簡化邊界處理
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode current = head;

        while (current != null) {
            // 檢查是否還有足夠的 k 個節點
            ListNode end = current;
            for (int i = 0; i < k - 1 && end != null; i++) {
                end = end.next;
            }

            // 如果 end 為 null，表示剩餘節點不足 k，保持原樣
            if (end == null) {
                break;
            }

            // 準備反轉
            ListNode next_group_head = end.next;
            // 斷開當前組與後續部分的連結
            end.next = null;
            
            // 反轉當前組
            ListNode reversedHead = reverse(current);
            
            // 重新連接鏈結串列
            prev.next = reversedHead;
            current.next = next_group_head;

            // 更新指針，準備處理下一組
            prev = current;
            current = next_group_head;
        }

        return dummy.next;
    }

    // 輔助函式：反轉一個單向鏈結串列
    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        return prev;
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
        
        System.out.println("請輸入每組反轉的節點數 k:");
        int k = scanner.nextInt();
        
        System.out.println("請輸入班表序列（以空格分隔，-1 結尾）:");
        ArrayList<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            int val = scanner.nextInt();
            if (val == -1) break;
            list.add(val);
        }
        scanner.close();

        int[] arr = list.stream().mapToInt(i -> i).toArray();
        ListNode head = createLinkedList(arr);
        System.out.print("原始班表序列為: ");
        printLinkedList(head);

        ListNode reversedHead = reverseKGroup(head, k);
        System.out.print("反轉後班表序列為: ");
        printLinkedList(reversedHead);
    }
}