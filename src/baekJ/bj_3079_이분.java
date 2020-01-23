package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_3079_이분 {
    //상근이와 친구들은 오스트레일리아로 여행을 떠났다. 상근이와 친구들은 총 M명이고, 지금 공항에서 한 줄로 서서 입국심사를 기다리고 있다. 입국심사대는 총 N개가 있다. 각 입국심사관이 심사를 하는데 걸리는 시간은 사람마다 모두 다르다. k번 심사대에 앉아있는 심사관이 한 명을 심사를 하는데 드는 시간은 Tk이다.
    //
    //가장 처음에 모든 심사대는 비어있고, 심사를 할 준비를 모두 끝냈다. 상근이와 친구들은 비행기 하나를 전세내고 놀러갔기 때문에, 지금 심사를 기다리고 있는 사람은 모두 상근이와 친구들이다. 한 심사대에서는 한 번에 한 사람만 심사를 할 수 있다. 가장 앞에 서 있는 사람은 비어있는 심사대가 보이면 거기로 가서 심사를 받을 수 있다. 하지만 항상 이동을 해야 하는 것은 아니다. 더 빠른 심사대의 심사가 끝나길 기다린 다음에 그 곳으로 가서 심사를 받아도 된다.
    //상근이와 친구들이 심사를 받는데 걸리는 시간의 최솟값을 구하는 프로그램을 작성하시오.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫째 줄에 N과 M이 주어진다. (1 ≤ N ≤ 100,000, 1 ≤ M ≤ 1,000,000,000)
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        //다음 N개 줄에는 각 심사대에서 심사를 하는데 걸리는 시간인 Tk가 주어진다. (1 ≤ Tk ≤ 109)
        long[] audit_time = new long[(int) n];
        for (int i = 0; i < n; i++) {
            audit_time[i] = Long.parseLong(br.readLine());
        }

        long min = 1;
        long max = 1000000000000000000L;
        long mid;

        while (min <= max) {
            mid = (min + max) / 2;

            long sum = getCount(audit_time, mid);
            if (sum < m) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        System.out.println(min);
    }

    private static long getCount(long[] audit_time, long time) {
        long sum = 0;
        for (int i = 0; i < audit_time.length; i++) {
            sum += time / audit_time[i];
        }
        return sum;
    }
}
