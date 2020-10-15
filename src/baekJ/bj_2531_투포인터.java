package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class bj_2531_투포인터 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushiArr = new int[N];
        for (int i = 0; i < N; i++) {
            sushiArr[i] = Integer.parseInt(br.readLine());
        }

        int ans = 0;
        Set<Integer> set = new HashSet<>();
        int start = 0;
        int end = 0;
        while (end != N) {
            int curSushi = sushiArr[end];
            if (end - start == k - 1) {
                if (!set.contains(c)) {
                    System.out.println(set.size() + 1);
                    return;
                } else {
                    ans = k;
                }
            }
            if (!set.contains(curSushi) && end - start <= k) {
                set.add(curSushi);
                end++;
            } else {
                set.remove(sushiArr[start++]);
            }
        }

        if (!set.contains(c)) {
            System.out.println(set.size() + 1 > ans ? set.size() + 1 : ans);
        } else {
            System.out.println(set.size() > ans ? set.size() : ans);
        }
    }
}
