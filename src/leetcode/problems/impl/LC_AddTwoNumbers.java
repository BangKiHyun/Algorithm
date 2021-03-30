package leetcode.problems.impl;

public class LC_AddTwoNumbers {
    private static final int DIVIDE_VALUE = 10;

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
        ListNode answerNode = new ListNode();
        ListNode dummyNode = answerNode;
        int carryValue = 0;
        int totalValue;
        while (l1 != null || l2 != null) {
            totalValue = carryValue;
            if (l1 != null) {
                totalValue += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                totalValue += l2.val;
                l2 = l2.next;
            }

            carryValue = totalValue / DIVIDE_VALUE;
            int remainValue = totalValue % DIVIDE_VALUE;
            dummyNode.next = new ListNode(remainValue);
            dummyNode = dummyNode.next;
        }

        if (carryValue != 0) {
            dummyNode.next = new ListNode(carryValue);
        }
        return answerNode.next;
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
    }
}
