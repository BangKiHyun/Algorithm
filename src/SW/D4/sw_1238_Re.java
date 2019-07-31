package SW.D4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_1238_Re {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int len, startPnt;
        int map[][];
        int visit[];
        int from, to;
        FindPnt curPnt;
        Queue<FindPnt> que = new LinkedList();

        int loopcnt;
        int resultNum;
        int tempCnt;

        for (int i = 1; i <= 10; i++) {
            map = new int[101][101];
            visit = new int[101];
            len = in.nextInt();
            startPnt = in.nextInt();

            for (int j = 0; j < len / 2; j++) {
                from = in.nextInt();
                to = in.nextInt();

                map[from][to] = 1;
            }

            loopcnt = 1;
            que.add(new FindPnt(startPnt, loopcnt));
            visit[startPnt] = 1;
            resultNum = -1;
            tempCnt = 0;
            while (!que.isEmpty()) {
                curPnt = que.poll();

                if (tempCnt < curPnt.cnt) {
                    tempCnt = curPnt.cnt;
                    resultNum = curPnt.data;
                } else {
                    if (curPnt.data > resultNum) {
                        resultNum = curPnt.data;
                    }
                }

                for (int j = 1; j <= 100; j++) {
                    if (visit[j] != 1 && map[curPnt.data][j] == 1) {
                        visit[j] = 1;
                        que.add(new FindPnt(j, curPnt.cnt + 1));
                    }
                }
            }
            System.out.println("#" + i + " " + resultNum);
        }
    }

    static class FindPnt {
        int data;
        int cnt;

        public FindPnt(int data, int cnt) {
            this.data = data;
            this.cnt = cnt;
        }
    }
}