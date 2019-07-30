package SW.D4;

import java.io.*;

public class sw_1232 {

    static String c[];
    static int idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= 10; test_case++) {
            String s = br.readLine();
            int n = Integer.parseInt(s);
            c = new String[n];
            idx = 0;
            Node[] tree = new Node[n + 1];
            for (int i = 1; i <= n; i++) {
                String v = br.readLine();
                String arr[] = v.split(" ");
                if (arr[1].equals("-") || arr[1].equals("+") || arr[1].equals("*") || arr[1].equals("/")) {
                    tree[i] = new Node(arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
                } else {
                    tree[i] = new Node(arr[1]);
                }
            }
            inOrder(tree, tree[1]);
            String ans = " ";
            System.out.println("#" + test_case + " " + ans);
        }
        br.close();
    }

    static String op(String s, int n1, int n2) {
        int ans = 0;
        switch (s) {
            case "-":
                ans = n1 - n2;
                break;
            case "+":
                ans = n1 + n2;
                break;
            case "*":
                ans = n1 * n2;
                break;
            case "/":
                ans = n1 / n2;
            default:
                break;
        }
        return Integer.toString(ans);
    }

    static void inOrder(Node tree[], Node node) {
        if (node != null) {
            inOrder(tree, tree[node.left]);

            inOrder(tree, tree[node.right]);
        }
    }

    static class Node {
        String data;
        int left, right;

        public Node(String data, int left, int right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(String data) {
            this.data = data;
        }
    }
}
