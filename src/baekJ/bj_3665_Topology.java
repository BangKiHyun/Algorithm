package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//올해 ACM-ICPC 대전 인터넷 예선에는 총 n개의 팀이 참가했다.
//팀은 1번부터 n번까지 번호가 매겨져 있다. 놀랍게도 올해 참가하는 팀은 작년에 참가했던 팀과 동일하다.
//
//올해는 인터넷 예선 본부에서는 최종 순위를 발표하지 않기로 했다.
//그 대신에 작년에 비해서 상대적인 순위가 바뀐 팀의 목록만 발표하려고 한다.(작년에는 순위를 발표했다)
//예를 들어, 작년에 팀 13이 팀 6 보다 순위가 높았는데, 올해 팀 6이 팀 13보다 순위가 높다면, (6, 13)을 발표할 것이다.
//
//창영이는 이 정보만을 가지고 올해 최종 순위를 만들어보려고 한다.
//작년 순위와 상대적인 순위가 바뀐 모든 팀의 목록이 주어졌을 때, 올해 순위를 만드는 프로그램을 작성하시오.
//하지만, 본부에서 발표한 정보를 가지고 확실한 올해 순위를 만들 수 없는 경우가 있을 수도 있고, 일관성이 없는 잘못된 정보일 수도 있다.
//이 두 경우도 모두 찾아내야 한다.
public class bj_3665_Topology {
    private static int n;
    private static int[] inDegree;
    private static ArrayList<Integer>[] lists;
    private static ArrayList<Integer> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            answer = new ArrayList<>();

            n = Integer.parseInt(br.readLine());
            inDegree = new int[n + 1];
            lists = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                lists[i] = new ArrayList<>();
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer> ranks = new ArrayList<>();
            while (st.hasMoreElements()) {
                ranks.add(Integer.parseInt(st.nextToken()));
            }

            for (int i = 0; i < ranks.size(); i++) {
                int from = ranks.get(i);
                for (int j = i + 1; j < ranks.size(); j++) {
                    lists[from].add(ranks.get(j));
                    inDegree[ranks.get(j)]++;
                }
            }

            int team = Integer.parseInt(br.readLine());
            for (int teamCnt = 0; teamCnt < team; teamCnt++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                if (lists[from].contains(to)) {
                    lists[to].add(from);
                    inDegree[from]++;
                    inDegree[to]--;
                    lists[from].remove((Integer) to);
                } else {
                    lists[from].add(to);
                    inDegree[to]++;
                    inDegree[from]--;
                    lists[to].remove((Integer) from);
                }
            }
            if (canFindRank()) {
                StringBuilder sb = new StringBuilder();
                if (answer.size() != n) {
                    System.out.println("IMPOSSIBLE");
                } else {
                    for (int ans : answer) {
                        sb.append(ans);
                    }
                    System.out.println(sb);
                }
            } else {
                System.out.println("?");
            }
        }
    }

    private static boolean canFindRank() {
        Queue<Integer> q = new LinkedList<>();
        for (int num = 1; num <= n; num++) {
            if (inDegree[num] == 0) {
                q.offer(num);
                answer.add(num);
            }
        }

        if (q.isEmpty()) return false;

        while (!q.isEmpty()) {
            if (q.size() > 1) {
                return false;
            }
            int start = q.poll();
            for (int to : lists[start]) {
                if (--inDegree[to] == 0) {
                    q.offer(to);
                    answer.add(to);
                }
            }
        }

        return true;
    }
}
