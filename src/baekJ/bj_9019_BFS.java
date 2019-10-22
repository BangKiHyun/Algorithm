package baekJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_9019_BFS {
    private static Queue<Graph> q = new LinkedList<>();
    private static String[] DSLR = new String[4];
    private static boolean[] visit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt_test = sc.nextInt();
        for (int test_case = 1; test_case <= cnt_test; test_case++) {
            int A = sc.nextInt();
            int B = sc.nextInt();

            String source = convertIntToStr(A);
            String destination = convertIntToStr(B);

            q.add(new Graph(source, ""));
            visit = new boolean[10000];
            bfs(source, destination);
        }
    }

    private static void bfs(String source, String destination) {
        while (!q.isEmpty()) {
            Graph now = q.poll();

            if (destination.equals(now.number)) {
                System.out.println(now.command);
                q.clear();
                return;
            }

            DSLR[0] = getD(now.number);
            DSLR[1] = getS(now.number);
            DSLR[2] = getL(now.number);
            DSLR[3] = getR(now.number);

            for (int i = 0; i < 4; i++) {

                if (!visit[Integer.parseInt(DSLR[i])]) {
                    visit[Integer.parseInt(DSLR[i])] = true;

                    if (i == 0) {
                        q.add(new Graph(DSLR[i], now.command + "D"));
                    } else if (i == 1) {
                        q.add(new Graph(DSLR[i], now.command + "S"));
                    } else if (i == 2) {
                        q.add(new Graph(DSLR[i], now.command + "L"));
                    } else {
                        q.add(new Graph(DSLR[i], now.command + "R"));
                    }
                }
            }
        }
    }

    private static class Graph {
        String number, command;

        Graph(String number, String command) {
            this.number = number;
            this.command = command;
        }
    }

    private static String getD(String number) {
        int num = Integer.parseInt(number);
        num *= 2;

        if (num > 9999) {
            num %= 10000;
        }

        if(num < 1000){
            return convertIntToStr(num);
        }
        return convertIntToStr(num);
    }

    private static String getS(String number) {
        int num = Integer.parseInt(number);
        if (num == 0) {
            return "9999";
        } else {
            return convertIntToStr(num - 1);
        }
    }

    private static String getL(String number) {
        String tmp = number.substring(0, 1);
        number = number.substring(1, 4);
        number += tmp;

        return number;
    }

    private static String getR(String number) {
        String tmp = number.substring(3, 4);
        number = number.substring(0, 3);
        number = tmp + number;

        return number;
    }

    private static String convertIntToStr(int number) {
        if (number < 10) {
            return "000" + number;
        } else if (number < 100) {
            return "00" + number;
        } else if (number < 1000) {
            return "0" + number;
        } else
            return Integer.toString(number);
    }
}
