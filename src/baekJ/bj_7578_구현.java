package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class bj_7578_구현 {
    private static ArrayList<Integer>[] lists;
    private static ArrayList<Line> lines = new ArrayList<>();
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        lists = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }

        String[] first = br.readLine().split(" ");
        String[] second = br.readLine().split(" ");

        int idx = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (first[i].equals(second[j])) {
                    if (i > j) lines.add(new Line(idx, j + 1, i + 1));
                    else lines.add(new Line(idx, i + 1, j + 1));
                    idx++;
                    break;
                }
            }
        }

        findCross();
        System.out.println(ans);
    }

    private static void findCross() {
        for (int i = 0; i < lines.size() - 1; i++) {
            Line now = lines.get(i);
            for (int j = i + 1; j < lines.size(); j++) {
                Line next = lines.get(j);
                if (lists[now.idx].contains(next.idx)) continue;

                if (isCross(now, next)) {
                    lists[now.idx].add(next.idx);
                    lists[next.idx].add(now.idx);
                    ans++;
                }
            }
        }
    }

    private static boolean isCross(Line now, Line next) {
        if (now.start < next.start) {
            if (next.start < now.end) return true;
        } else {
            if (next.end > now.start) return true;
        }

        return false;
    }

    private static class Line {
        private int idx;
        private int start;
        private int end;

        public Line(int idx, int start, int end) {
            this.idx = idx;
            this.start = start;
            this.end = end;
        }
    }
}
