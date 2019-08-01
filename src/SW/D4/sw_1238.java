package SW.D4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_1238 {
    static int MAX = 101;
    static boolean visit[];
    static LinkedList<Integer> a[];
    static Queue<Node> q;
    static int result[];
    static int max, temp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int test_case = 1; test_case <= 10; test_case++) {
            int len = sc.nextInt();
            int start = sc.nextInt();
            visit = new boolean[MAX];
            a = new LinkedList[MAX];
            q = new LinkedList<>();
            result = new int[MAX];
            max = -1;
            temp = 0;
            for (int i = 1; i < MAX; i++) {
                a[i] = new LinkedList<>();
            }
            for (int i = 0; i < len / 2; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                a[x].add(y);
            }
            q.add(new Node(start, 0));
            visit[start] = true;
            int ans = result();
            System.out.println("#" + test_case + " " + ans);
        }
    }

    static int result() {
        int ans = 0;
        while (!q.isEmpty()) {
            Node n = q.poll();
            if (temp < n.cnt) {
                temp = n.cnt;
                max = n.num;
            } else{
                if(n.num > max){
                    max = n.num;
                }
            }
            for (int i : a[n.num]) {
                if (!visit[i]) {
                    q.add(new Node(i, n.cnt + 1));
                    visit[i] = true;
                }
            }
        }
        ans = max;
        return ans;
    }

    static class Node {
        int num, cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}