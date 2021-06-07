package leetcode.problems.impl;

public class LC_Swap_Nodes_in_Pairs {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        System.out.println(swapPairs(head));
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode headNode = head;
        while (head.next != null) {
            head = swapNode(head, head.next);
        }
        return headNode;
    }

    private static ListNode swapNode(ListNode cur, ListNode next) {
        int tempVal = cur.val;
        cur.val = next.val;
        next.val = tempVal;
        return next.next == null ? next : next.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
