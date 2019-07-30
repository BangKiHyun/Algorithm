package SW.D4;

import java.util.Scanner;

public class sw_1231 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int problem = 0;

        while (problem++ < 10) {
            int half, index;
            int count = sc.nextInt();
            Node[] tree = new Node[count + 1];

            for (int i = 0; i < count; i++) {
                index = sc.nextInt();
                half = count / 2;

                if (index < half) {
                    tree[index] = new Node(sc.next(), sc.nextInt(), sc.nextInt());
                } else if (index == half) {
                    if (count % 2 == 0) {
                        tree[index] = new Node(sc.next(), sc.nextInt());
                    } else {
                        tree[index] = new Node(sc.next(), sc.nextInt(), sc.nextInt());
                    }
                } else {
                    tree[index] = new Node(sc.next());
                }
            }
            System.out.print("#" + problem + " ");
            inOrder(tree, tree[1]);
            System.out.println();
        }
        sc.close();
    }

    private static void inOrder(Node[] tree, Node node) {
        if (node != null) {
            inOrder(tree, tree[node.left]);
            System.out.print(node.data);
            inOrder(tree, tree[node.right]);
        }
    }
    static class Node {
        String data;
        int left, right;

        Node(String data, int left, int right) {
            this(data, left);
            this.right = right;
        }

        Node(String data, int left) {
            this(data);
            this.left = left;
        }

        Node(String data) {
            this.data = data;
        }
    }
}