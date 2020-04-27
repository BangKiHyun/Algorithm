package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

//세로 두 줄, 가로로 N개의 칸으로 이루어진 표가 있다.
//첫째 줄의 각 칸에는 정수 1, 2, …, N이 차례대로 들어 있고 둘째 줄의 각 칸에는 1이상 N이하인 정수가 들어 있다.
//첫째 줄에서 숫자를 적절히 뽑으면, 그 뽑힌 정수들이 이루는 집합과, 뽑힌 정수들의 바로 밑의 둘째 줄에 들어있는 정수들이 이루는 집합이 일치한다.
//이러한 조건을 만족시키도록 정수들을 뽑되, 최대로 많이 뽑는 방법을 찾는 프로그램을 작성하시오.
public class bj_2668_DFS {
    private static int[] card;
    private static ArrayList<Integer> list = new ArrayList<>();
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        card = new int[n + 1];
        visit = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            card[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {
            visit[i] = true;
            goDFS(i, i);
            visit[i] = false;
        }

        Collections.sort(list);
        System.out.println(list.size());
        for (int ans : list) {
            System.out.println(ans);
        }
    }

    private static void goDFS(int idx, int finish) {
        if (card[idx] == finish) {
            list.add(finish);
        }

        if (!visit[card[idx]]) {
            visit[card[idx]] = true;
            goDFS(card[idx], finish);
            visit[card[idx]] = false;
        }
    }
}
