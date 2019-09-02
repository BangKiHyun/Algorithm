package problem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_14891 {
    static LinkedList<Integer>[] lists = new LinkedList[5];
    static int rotateNum;
    static Queue<Node> q = new LinkedList<>();
    static boolean[] visit;
    static int ans = 0;

    public static void main(String[] args) {
        init();
        solution();
        result();
        System.out.println(ans);
    }

    private static void solution() {
        while (!q.isEmpty()) {
            Node n = q.poll();
            visit = new boolean[5];
            visit[n.gear] = true;
            rotate(n.gear, n.d);
        }
    }

    private static void rotate(int gearNum, int d) {
        LinkedList gear = lists[gearNum];

        int next_num = gearNum + 1;
        int back_num = gearNum - 1;

        if (next_num < 5 && !visit[next_num]) {
            visit[next_num] = true;
            rotate(next_num, directionRight((int) gear.get(2), next_num, d));
        }

        if (back_num > 0 && !visit[back_num]) {
            visit[back_num] = true;
            rotate(back_num, directionLeft((int) gear.get(6), back_num, d));
        }

        if (d == 1) {
            int temp = (int) gear.getLast();
            gear.removeLast();
            gear.addFirst(temp);
        } else if (d == -1) {
            int temp = (int) gear.getFirst();
            gear.poll();
            gear.add(7, temp);
        }
    }

    private static void result() {
        int cnt = 1;
        for (int i = 1; i < 5; i++) {
            if (lists[i].getFirst() == 1) {
                ans += cnt;
            }
            cnt *= 2;
        }
    }

    private static int directionRight(int current, int gearNum, int d) {
        if (current == lists[gearNum].get(6)) return 0;
        else return d * -1;
    }

    private static int directionLeft(int current, int gearNum, int d) {
        if (current == lists[gearNum].get(2)) return 0;
        else return d * -1;
    }

    private static class Node {
        int gear, d;

        Node(int gear, int d) {
            this.gear = gear;
            this.d = d;
        }
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i < 5; i++) {
            lists[i] = new LinkedList();
            String s = sc.next();
            for (int j = 0; j < 8; j++) {
                lists[i].add(Integer.parseInt(s.substring(j, j + 1)));
            }
        }
        rotateNum = sc.nextInt();
        for (int i = 0; i < rotateNum; i++) {
            q.add(new Node(sc.nextInt(), sc.nextInt()));
        }
    }
}
