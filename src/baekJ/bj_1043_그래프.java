package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//사람의 수 N이 주어진다. 그리고 그 이야기의 진실을 아는 사람이 주어진다.
//그리고 각 파티에 오는 사람들의 번호가 주어진다. 지민이는 모든 파티에 참가해야 한다.
//이때, 지민이가 거짓말쟁이로 알려지지 않으면서, 과장된 이야기를 할 수 있는 파티 개수의 최댓값을 구하는 프로그램을 작성하시오.
public class bj_1043_그래프 {
    private static boolean[] participant;
    private static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫째 줄에 사람의 수 N과 파티의 수 M이 주어진다.
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int ans = 0;

        //둘째 줄에는 이야기의 진실을 아는 사람의 수와 번호가 주어진다. 진실을 아는 사람의 수가 먼저 주어지고 그 개수만큼 사람들의 번호가 주어진다.
        participant = new boolean[n + 1];
        line = br.readLine().split(" ");
        int trueCnt = Integer.parseInt(line[0]);
        if (trueCnt == 0) {
            System.out.println(m);
            return;
        }

        int[] liar = new int[trueCnt];
        for (int i = 1; i <= trueCnt; i++) {
            int p = Integer.parseInt(line[i]);
            liar[i - 1] = p;
            participant[p] = true;
        }

        list = new ArrayList[n + 1];
        ArrayList<Integer>[] partyList = new ArrayList[m];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            partyList[i] = new ArrayList<>();
        }

        //셋째 줄부터 M개의 줄에는 각 파티마다 오는 사람의 수와 번호가 같은 방식으로 주어진다.
        for (int i = 0; i < m; i++) {
            line = br.readLine().split(" ");
            int participation = Integer.parseInt(line[0]);
            if (participation == 0) ans++;
            if (participation == 1) partyList[i].add(Integer.parseInt(line[1]));
            else {
                for (int j = 1; j <= participation; j++) {
                    int from = Integer.parseInt(line[j]);
                    partyList[i].add(from);
                    for (int k = j + 1; k <= participation; k++) {
                        int to = Integer.parseInt(line[k]);
                        list[from].add(to);
                        list[to].add(from);
                    }
                }
            }
        }

        for (int i = 0; i < trueCnt; i++) {
            checkLiar(liar[i]);
        }

        for (int i = 0; i < partyList.length; i++) {
            if (partyList[i].size() == 0) continue;
            boolean flag = true;
            for (int j : partyList[i]) {
                if (participant[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) ans++;
        }

        System.out.println(ans);
    }

    private static void checkLiar(int idx) {
        for (int i : list[idx]) {
            if (!participant[i]) {
                participant[i] = true;
                checkLiar(i);
            }
        }
    }
}

/*
6 5
1 6
2 1 2
2 2 3
2 3 4
2 4 5
2 5 6

6 5
1 6
2 4 5
2 1 2
2 2 3
2 3 4
2 5 6
*/