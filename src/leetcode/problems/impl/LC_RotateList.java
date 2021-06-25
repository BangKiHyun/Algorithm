package leetcode.problems.impl;

public class LC_RotateList {

    public static void main(String[] args) {
        final LC_RotateList task = new LC_RotateList();
        final ListNode head = new ListNode(1);
        head.next = new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))));
        int k = 2;
        final ListNode node = task.rotateRight(head, k);
        System.out.println(node.val);
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;

        ListNode tail = head;
        ListNode tempNode = head;
        int lenght = 1;
        while (tail.next != null) {
            tail = tempNode.next;
            tempNode = tempNode.next;
            lenght++;
        }

        if (k > lenght) {
            k %= lenght;
        }
        k = lenght - k;
        while (k-- != 0) {
            if (head != null) {
                tail.next = new ListNode(head.val);
                tail = tail.next;
                head = head.next;
            }
        }
        return head;
    }

    public static class ListNode {
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
    }
}

