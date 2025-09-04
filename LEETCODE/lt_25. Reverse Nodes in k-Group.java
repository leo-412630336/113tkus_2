class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevGroupEnd = dummy;
        ListNode groupStart = head;

        while (true) {
            ListNode groupEnd = prevGroupEnd;
            for (int i = 0; i < k && groupEnd != null; i++) groupEnd = groupEnd.next;
            if (groupEnd == null) break;

            ListNode nextGroupStart = groupEnd.next;

            ListNode prev = nextGroupStart;
            ListNode curr = groupStart;
            while (curr != nextGroupStart) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            prevGroupEnd.next = groupEnd;
            prevGroupEnd = groupStart;
            groupStart = nextGroupStart;
        }

        return dummy.next;
    }
}