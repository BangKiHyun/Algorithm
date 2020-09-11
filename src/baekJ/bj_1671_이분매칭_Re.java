package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class bj_1671_이분매칭_Re {
    private static int[] eating;
    private static boolean[] visit;
    private static List<Integer>[] eatableList;
    private static Shark[] sharks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        eating = new int[n + 1];
        visit = new boolean[n + 1];
        eatableList = new ArrayList[n + 1];
        sharks = new Shark[n + 1];

        for (int i = 1; i <= n; i++) {
            eatableList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int IQ = Integer.parseInt(st.nextToken());

            sharks[i] = new Shark(size, speed, IQ);
        }

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (sharks[i].eatable(sharks[j])) {
                    eatableList[i].add(j);
                    continue;
                }
                if (sharks[j].eatable(sharks[i])) {
                    eatableList[j].add(i);
                }
            }
        }

        int catchCnt = 0;
        for (int k = 0; k < 2; k++) {
            for (int i = 1; i <= n; i++) {
                Arrays.fill(visit, false);
                if (eatShark(i)) catchCnt++;
            }
        }

        System.out.println(n - catchCnt);
    }

    private static boolean eatShark(final int from) {
        for (int to : eatableList[from]) {
            if (visit[to]) continue;
            visit[to] = true;

            if (eating[to] == 0 || eatShark(eating[to])) {
                eating[to] = from;
                return true;
            }
        }

        return false;
    }

    private static class Shark {
        private int size;
        private int speed;
        private int IQ;

        public Shark(final int size, final int speed, final int IQ) {
            this.size = size;
            this.speed = speed;
            this.IQ = IQ;
        }

        public boolean eatable(final Shark shark) {
            return this.size >= shark.size &&
                    this.speed >= shark.speed &&
                    this.IQ >= shark.IQ;
        }
    }
}
