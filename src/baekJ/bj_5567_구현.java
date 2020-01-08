package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class bj_5567_구현 {
    private static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        StringTokenizer st;
        ArrayList<Integer>[] lists = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            lists[first].add(second);
            lists[second].add(first);
        }

        findFriend(lists, 0, 1);
        System.out.println(set.size());
    }

    private static void findFriend(ArrayList<Integer> lists[], int cnt, int idx) {
        if (cnt == 2) return;
        for (int i : lists[idx]) {
            if(i == 1) continue;
            set.add(i);
            findFriend(lists, cnt + 1, i);
        }
    }
}
