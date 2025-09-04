import java.util.Scanner;

// 定義鏈結串列節點
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}

public class LC21_MergeTwoLists_Clinics {

    /**
     * @param list1 第一個已排序的鏈結串列
     * @param list2 第二個已排序的鏈結串列
     * @return 合併後的排序鏈結串列
     *
     * 時間複雜度: O(n + m) - 遍歷兩個鏈結串列各一次。
     * 空間複雜度: O(1) - 只使用了幾個額外指針，沒有額外的儲存空間。
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 建立虛擬頭節點和當前指針
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // 當兩個鏈結串列都還有節點時，進行比較合併
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // 將剩下的非空鏈結串列直接接到合併串列的尾部
        if (list1 != null) {
            current.next = list1;
        }
        if (list2 != null) {
            current.next = list2;
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

        System.out.println("請輸入第一個數列的長度 n 和第二個數列的長度 m，以空格分隔:");
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] arr1 = new int[n];
        System.out.println("請輸入 " + n + " 個已排序的整數，以空格分隔:");
        for (int i = 0; i < n; i++) {
            arr1[i] = scanner.nextInt();
        }

        int[] arr2 = new int[m];
        System.out.println("請輸入 " + m + " 個已排序的整數，以空格分隔:");
        for (int i = 0; i < m; i++) {
            arr2[i] = scanner.nextInt();
        }

        scanner.close();

        ListNode list1 = createLinkedList(arr1);
        ListNode list2 = createLinkedList(arr2);

        ListNode mergedList = mergeTwoLists(list1, list2);

        System.out.print("合併後的序列為: ");
        printLinkedList(mergedList);
    }
}