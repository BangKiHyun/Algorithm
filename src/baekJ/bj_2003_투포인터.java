package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//N개의 수로 된 수열 A[1], A[2], …, A[N] 이 있다.
//이 수열의 i번째 수부터 j번째 수까지의 합 A[i]+A[i+1]+…+A[j-1]+A[j]가 M이 되는
//경우의 수를 구하는 프로그램을 작성하시오.
public class bj_2003_투포인터 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreElements()) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        int start = 0, end = 0, sum = 0, count = 0;

        while (true) {
            if (end == n) {
                break;
            }

            if (sum >= m) {
                sum -= list.get(start++);
            } else {
                sum += list.get(end++);
            }

            if (sum == m) {
                count++;
            }
        }

        while (start != n) {
            sum -= list.get(start++);
            if (sum == m) {
                count++;
            }
        }

        System.out.println(count);
    }
}
