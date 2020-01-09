package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_13458_구현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫째 줄에 시험장의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다.
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st1, st2;
        //둘째 줄에는 각 시험장에 있는 응시자의 수 Ai (1 ≤ Ai ≤ 1,000,000)가 주어진다.
        st1 = new StringTokenizer(br.readLine());
        st2 = new StringTokenizer(br.readLine());
        int main = Integer.parseInt(st2.nextToken());
        int support = Integer.parseInt(st2.nextToken());
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st1.nextToken()) - main;
            ans++;
            if (num <= 0) continue;
            else {
                int mok = num / support;
                double remain = (double) num % support;
                if (remain == 0) ans += mok;
                else ans += mok + 1;
            }
        }
        System.out.println(ans);
    }
}
