package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//n행 n열의 격자로 나뉜 운동장이 있다.
//이 위에 k개의 돌멩이가 있는데, 하나의 돌멩이는 격자 한 칸에 정확히 들어가 있으며, 두 개 이상의 돌멩이가 한 칸에 들어가 있는 경우는 없다.
//
//사고의 위험을 없애기 위해 이 돌멩이를 모두 제거하고 깨끗한 운동장을 만들려고 한다.
//돌멩이를 제거할 때에는, 한 행이나 한 열을 따라 직선으로 달려가면서 그 행이나 열에 놓인 돌멩이를 모두 줍는 방식을 쓴다.
//
//여러분이 할 일은 운동장의 상태가 주어졌을 때 최소 몇 번이나 달려가야 돌멩이 줍기를 끝낼 수 있는지 계산하는 것이다.
public class bj_1867_이분매칭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에 n과 k가 주어진다. (1 ≤ n ≤ 500, 1 ≤ k ≤ 10,000)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] lists = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }

        //이후 k개의 줄에는 돌멩이의 위치가 한 줄에 하나씩 주어진다.
        //줄마다 첫 번째 숫자는 행 번호, 두 번째 숫자는 열 번호를 나타낸다.
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            lists[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }

        boolean[] visit = new boolean[n + 1];
        int[] cleaned = new int[n + 1];
        int runCnt = 0;
        for (int i = 1; i <= n; i++) {
            if (pickUpStone(i, visit, cleaned, lists)) runCnt++;
            Arrays.fill(visit, false);
        }

        System.out.println(runCnt);
    }

    private static boolean pickUpStone(int from, boolean[] visit, int[] cleaned, ArrayList<Integer>[] lists) {
        for (int to : lists[from]) {
            if (visit[to]) continue;
            visit[to] = true;

            if (cleaned[to] == 0 || pickUpStone(cleaned[to], visit, cleaned, lists)) {
                cleaned[to] = from;
                return true;
            }
        }
        return false;
    }
}
