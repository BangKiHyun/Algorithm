package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//정수로 이루어진 크기가 같은 배열 A, B, C, D가 있다.
//
//A[a], B[b], C[c], D[d]의 합이 0인 (a, b, c, d) 쌍의 개수를 구하는 프로그램을 작성하시오.
public class bj_7453_이분 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫째 줄에 배열의 크기 n (1 ≤ n ≤ 4000)이 주어진다
        int n = Integer.parseInt(br.readLine());

        //다음 n개 줄에는 A, B, C, D에 포함되는 정수가 공백으로 구분되어져서 주어진다.
        //배열에 들어있는 정수의 절댓값은 최대 228이다.
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];
        String[] line;
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            a[i] = Integer.parseInt(line[0]);
            b[i] = Integer.parseInt(line[1]);
            c[i] = Integer.parseInt(line[2]);
            d[i] = Integer.parseInt(line[3]);
        }

        long[] ab = new long[n * n];
        long[] cd = new long[n * n];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ab[idx] = a[i] + b[j];
                cd[idx] = c[i] + d[j];
                idx++;
            }
        }

        Arrays.sort(cd);

        int min, max, mid;
        long low, high;
        long cnt = 0;
        for (int i = 0; i < n * n; i++) {
            min = 0;
            max = n * n;
            while (min < max) {
                mid = (min + max) / 2;
                if (ab[i] + cd[mid] < 0) min = mid + 1;
                else max = mid;
            }
            low = max;

            min = 0;
            max = n * n;
            while (min < max) {
                mid = (min + max) / 2;
                if (ab[i] + cd[mid] <= 0) min = mid + 1;
                else max = mid;
            }
            high = max;
            cnt += (high - low);
        }

        System.out.println(cnt);
    }
}
