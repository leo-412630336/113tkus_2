// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 先走 n+1 步
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // fast 與 slow 一起走，直到 fast 到尾
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除 slow 的下一個節點
        slow.next = slow.next.next;

        return dummy.next;
    }
}