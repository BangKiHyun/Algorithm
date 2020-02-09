package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_3020_이분 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int h = Integer.parseInt(line[1]);

        int[] empty = new int[h + 1];

        int min = n;
        for (int i = 0; i < n; i++) {
            int cnt = h;
            int height = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                while (height < h) {
                    empty[cnt]--;
                    min = Math.min(min, empty[cnt]);
                    cnt--;
                    height++;
                }
            } else {
                cnt = 1;
                while (height < h) {
                    empty[cnt]--;
                    min = Math.min(min, empty[cnt]);
                    cnt++;
                    height++;
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= h; i++) {
            if (min == empty[i]) {
                ans++;
            }
        }

        System.out.println(n + min + " " + ans);
    }
}
