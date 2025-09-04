import java.util.PriorityQueue;
import java.util.Scanner;

// 定義鏈結串列節點
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}

public class LC23_MergeKLists_Hospitals {

    /**
     * @param lists 包含 k 個已排序鏈結串列的陣列
     * @return 合併後的單一排序鏈結串列
     *
     * 時間複雜度: O(N log k) - N 為總節點數，k 為鏈結串列數。每個節點被推入和彈出堆一次，操作為 O(log k)。
     * 空間複雜度: O(k) - 堆中最多同時存放 k 個節點。
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        // 邊界檢查
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 建立最小堆，並設定比較器
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 初始化：將所有非空鏈結串列的頭節點放入堆中
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.add(node);
            }
        }

        // 建立虛擬頭節點和當前指針
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // 當堆不為空時，持續取出最小節點
        while (!minHeap.isEmpty()) {
            // 從堆中取出當前所有頭節點中的最小值
            ListNode minNode = minHeap.poll();
            
            // 將此最小節點接到合併後的串列上
            current.next = minNode;
            current = current.next;

            // 如果該節點還有後續節點，將其推入堆中
            if (minNode.next != null) {
                minHeap.add(minNode.next);
            }
        }
        
        return dummy.next;
    }

    // 輔助函式：從輸入建立鏈結串列
    public static ListNode createLinkedList(Scanner scanner) {
        ListNode head = null;
        ListNode current = null;
        int val = scanner.nextInt();
        while (val != -1) {
            ListNode newNode = new ListNode(val);
            if (head == null) {
                head = newNode;
                current = head;
            } else {
                current.next = newNode;
                current = current.next;
            }
            val = scanner.nextInt();
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
        
        System.out.println("請輸入鏈結串列的數量 k:");
        int k = scanner.nextInt();
        
        ListNode[] lists = new ListNode[k];
        for (int i = 0; i < k; i++) {
            System.out.println("請輸入第 " + (i + 1) + " 個鏈結串列（以空格分隔，-1 結尾）:");
            lists[i] = createLinkedList(scanner);
        }
        
        scanner.close();

        ListNode mergedList = mergeKLists(lists);
        
        System.out.print("合併後的候診序列為: ");
        printLinkedList(mergedList);
    }
}