package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_1298_이분매칭 {
    private static ArrayList<Integer>[] lists;
    private static int[] owner;
    private static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에는 노트북이 섞인 날 어제 노트북을 구입한 사람의 수 N(1 ≤ N ≤ 100)과 노트북 예상의 개수 M(0 ≤ M ≤ 5,000) 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        lists = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }

        owner = new int[n + 1];
        check = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int me = Integer.parseInt(st.nextToken());
            int laptop = Integer.parseInt(st.nextToken());
            lists[me].add(laptop);
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(check, false);
            if (findLaptop(i)) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    private static boolean findLaptop(int me) {
        for (int laptop : lists[me]) {
            if (check[laptop]) continue;
            check[laptop] = true;

            if (owner[laptop] == 0 || findLaptop(owner[laptop])) {
                owner[laptop] = me;
                return true;
            }
        }
        return false;
    }
}
