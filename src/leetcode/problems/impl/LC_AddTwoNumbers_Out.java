package leetcode.problems.impl;

public class LC_AddTwoNumbers_Out {

    private static final long DIVIDE_VALUE = 10L;

    public static void main(String[] args) {
        ListNode listNode7 = new ListNode(9);
        ListNode listNode6 = new ListNode(9, listNode7);
        ListNode listNode5 = new ListNode(9, listNode6);
        ListNode listNode4 = new ListNode(9, listNode5);
        ListNode listNode3 = new ListNode(9, listNode4);
        ListNode listNode2 = new ListNode(9, listNode3);
        ListNode listNode1 = new ListNode(9, listNode2);

        ListNode listNode11 = new ListNode(9);
        ListNode listNode10 = new ListNode(9, listNode11);
        ListNode listNode9 = new ListNode(9, listNode10);
        ListNode listNode8 = new ListNode(9, listNode9);

        ListNode listnode = addTwoNumbers(listNode1, listNode8);

        System.out.print(listnode.val + " ");
        while (listnode.next != null) {
            listnode = listnode.next;
            System.out.print(listnode.val + " ");
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder strFirstListNodeVal = new StringBuilder();
        strFirstListNodeVal.append(l1.val);
        while (l1.next != null) {
            l1 = l1.next;
            strFirstListNodeVal.append(l1.val);
        }

        StringBuilder strSecondListNodeVal = new StringBuilder();
        strSecondListNodeVal.append(l2.val);
        while (l2.next != null) {
            l2 = l2.next;
            strSecondListNodeVal.append(l2.val);
        }

        long firstNumber = Long.parseLong(String.valueOf(strFirstListNodeVal.reverse()));
        long secondNumber = Long.parseLong(String.valueOf(strSecondListNodeVal.reverse()));
        long totalValue = firstNumber + secondNumber;

        ListNode answerListNode = new ListNode();
        ListNode tempListNode = answerListNode;
        answerListNode.val = (int) (totalValue % DIVIDE_VALUE);
        totalValue = totalValue / DIVIDE_VALUE;
        while (totalValue != 0) {
            int remainValue = (int) (totalValue % DIVIDE_VALUE);
            totalValue = totalValue / DIVIDE_VALUE;
            tempListNode.next = new ListNode(remainValue);
            tempListNode = tempListNode.next;
        }
        return answerListNode;
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
