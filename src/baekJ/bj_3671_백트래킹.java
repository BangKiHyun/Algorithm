package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_3671_백트래킹 {
    private static final int INF = 10_000_000;

    private static Set<Integer> primeSet = new HashSet<>();
    private static int n;
    private static List<String> list;
    private static boolean[] visit;
    private static Set<Integer> curContainSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        initPrimeList();

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            list = Arrays.asList(br.readLine().split(""));
            visit = new boolean[list.size()];
            curContainSet = new HashSet<>();
            String cur = "";
            goDFS(cur);
            System.out.println(curContainSet.size());
        }
    }

    private static void initPrimeList() {
        int[] temp = new int[INF];

        for (int i = 2; i < INF; i++) {
            if (temp[i] != 0) {
                continue;
            }
            primeSet.add(i);
            for (int j = i + i; j < INF; j += i) {
                temp[j] = i;
            }
        }
    }

    private static void goDFS(final String cur) {
        if (!cur.equals("")) {
            int candidate = Integer.parseInt(cur);
            if (primeSet.contains(candidate) && !curContainSet.contains(candidate)) {
                curContainSet.add(candidate);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (!visit[i]) {
                visit[i] = true;
                String next = cur + list.get(i);
                goDFS(next);
                visit[i] = false;
            }
        }
    }
}
