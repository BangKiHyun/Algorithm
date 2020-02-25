package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//에라토스테네스의 체는 N보다 작거나 같은 모든 소수를 찾는 유명한 알고리즘이다.
//
//이 알고리즘은 다음과 같다.
//
//2부터 N까지 모든 정수를 적는다.
//아직 지우지 않은 수 중 가장 작은 수를 찾는다. 이것을 P라고 하고, 이 수는 소수이다.
//P를 지우고, 아직 지우지 않은 P의 배수를 크기 순서대로 지운다.
//아직 모든 수를 지우지 않았다면, 다시 2번 단계로 간다.
//N, K가 주어졌을 때, K번째 지우는 수를 구하는 프로그램을 작성하시오.
public class bj_2960_에라토스테네스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int ans = Eratos(n, k);
        System.out.println(ans);
    }

    private static int Eratos(int n, int k) {
        int[] arr = new int[n + 1];
        initArr(arr, n);

        int cnt = 0;

        for (int i = 2; i <= n; i++) {
            if (arr[i] != 0) {
                for (int j = i; j <= n; j += i) {
                    if (arr[j] == 0) continue;
                    arr[j] = 0;
                    cnt++;
                    if (cnt == k) {
                        return j;
                    }
                }
            }
        }
        return -1;
    }

    private static void initArr(int[] arr, int n) {
        for (int i = 2; i <= n; i++) {
            arr[i] = i;
        }
    }
}
