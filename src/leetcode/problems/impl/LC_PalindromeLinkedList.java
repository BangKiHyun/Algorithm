package leetcode.problems.impl;

import java.util.ArrayList;
import java.util.List;

public class LC_PalindromeLinkedList {

    public static void main(String[] args) {
        final ListNode tail = new ListNode(2, new ListNode(1));
        final ListNode next = new ListNode(2, tail);
        final ListNode head = new ListNode(1, next);
        final LC_PalindromeLinkedList task = new LC_PalindromeLinkedList();
        System.out.println(task.isPalindrome(head));
    }

    public boolean isPalindrome(ListNode head) {
        List<Integer> values = new ArrayList<>();
        while (head != null) {
            values.add(head.val);
            head = head.next;
        }
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) != values.get(values.size() - i - 1)) {
                return false;
            }
        }
        return true;
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

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}