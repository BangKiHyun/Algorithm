package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class bj_9466_DFS {
    private static ArrayList<Integer> list;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            visit = new boolean[n];
            list = new ArrayList<>();
            String[] input = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                list.add(Integer.parseInt(input[i]) - 1);
            }

            int ans = 0;
            for (int i = 0; i < n; i++) {
                if (!isTeam(list.get(i), i)) ans++;
                Arrays.fill(visit, false);
            }
            System.out.println(ans);
        }
    }

    private static boolean isTeam(int next, int start) {
        if (start == next) {
            return true;
        }

        if (visit[next]) return false;

        visit[next] = true;
        if (isTeam(list.get(next), start)) {
            return true;
        }

        return false;
    }
}
