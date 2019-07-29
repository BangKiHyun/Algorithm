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
            int ans = cal(c.length / 2, n);
            System.out.println("#" + test_case + " " + ans);
        }
        br.close();
    }

    static int cal(int len, int n) {
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < n; j++) {

            }
        }
        return ans;
    }

    static void inOrder(Node tree[], Node node) {
        if (node != null) {
            inOrder(tree, tree[node.left]);
            System.out.println(node.data);
            c[idx] = node.data;
            idx++;
            inOrder(tree, tree[node.right]);
        }
    }

    static class Node {
        String data;
        int left, right;

        public Node(String data, int left, int right) {
            super();
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(String data) {
            super();
            this.data = data;
        }
    }
}
