package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj_1806_투포인터 {
    private static int MAX = 100_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreElements()) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        long sum = 0;
        int start = 0, end = 0;
        int ans = MAX;

        while (end != n) {
            if (sum >= s) {
                ans = Math.min(ans, end - start);
                sum -= list.get(start++);
            } else {
                sum += list.get(end++);
            }
        }

        while (start != n) {
            if (sum >= s) {
                ans = Math.min(ans, end - start);
            }

            sum -= list.get(start++);
        }

        System.out.println(ans == MAX ? 0 : ans);
    }
}
