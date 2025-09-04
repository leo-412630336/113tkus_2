
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 建立一個虛擬頭節點(dummy)，方便操作
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;  // 指標用來建構結果串列
        int carry = 0;          // 進位值

        // 當任一串列還有節點，或進位不為 0，就繼續
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;  // 先加上進位

            if (l1 != null) {
                sum += l1.val;   // 加上 l1 的值
                l1 = l1.next;    // 移動到下一個節點
            }

            if (l2 != null) {
                sum += l2.val;   // 加上 l2 的值
                l2 = l2.next;    // 移動到下一個節點
            }

            // sum 可能超過 9，要處理進位
            carry = sum / 10;
            int digit = sum % 10;

            // 建立新節點並加到結果串列
            curr.next = new ListNode(digit);
            curr = curr.next;
        }

        // 回傳真正的頭節點 (dummy.next)
        return dummy.next;
    }
}