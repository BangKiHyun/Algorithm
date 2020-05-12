package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//소수를 유난히도 좋아하는 창영이는 게임 아이디 비밀번호를 4자리 ‘소수’로 정해놓았다. 어느 날 창영이는 친한 친구와 대화를 나누었는데:
//
//“이제 슬슬 비번 바꿀 때도 됐잖아”
//“응 지금은 1033으로 해놨는데... 다음 소수를 무엇으로 할지 고민중이야"
//“그럼 8179로 해”
//“흠... 생각 좀 해볼게. 이 게임은 좀 이상해서 비밀번호를 한 번에 한 자리 밖에 못 바꾼단 말이야.
//예를 들어 내가 첫 자리만 바꾸면 8033이 되니까 소수가 아니잖아. 여러 단계를 거쳐야 만들 수 있을 것 같은데...
//예를 들면... 1033 1733 3733 3739 3779 8779 8179처럼 말이야.”
//“흠...역시 소수에 미쳤군. 그럼 아예 프로그램을 짜지 그래. 네 자리 소수 두 개를 입력받아서 바꾸는데 몇 단계나 필요한지 계산하게 말야.”
//“귀찮아”
//그렇다. 그래서 여러분이 이 문제를 풀게 되었다. 입력은 항상 네 자리 소수만(1000 이상) 주어진다고 가정하자.
//주어진 두 소수 A에서 B로 바꾸는 과정에서도 항상 네 자리 소수임을 유지해야 하고,
//‘네 자리 수’라 하였기 때문에 0039 와 같은 1000 미만의 비밀번호는 허용되지 않는다.
public class bj_1963_BFS {
    private static final int MAX = 100000;

    private static int cnt = 0;
    private static boolean[] prime = new boolean[MAX];
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int test = Integer.parseInt(st.nextToken());
        checkPrime();

        for (int test_case = 1; test_case <= test; test_case++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int finish = Integer.parseInt(st.nextToken());
            visit = new boolean[MAX];

            if (isPossible(start, finish)) {
                System.out.println(cnt);
                continue;
            }
            System.out.println("Impossible");
        }
    }

    private static void checkPrime() {
        for (int i = 2; i < MAX; i++) {
            if (prime[i]) continue;
            for (int j = i + i; j < MAX; j = j + i) {
                prime[j] = true;
            }
        }
    }

    private static boolean isPossible(int start, int finish) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));
        visit[start] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.num == finish) {
                cnt = cur.cnt;
                return true;
            }

            for (int type = 1; type <= 4; type++) {
                for (int changeNum = 0; changeNum < 10; changeNum++) {
                    int postNum = getChangeNum(cur.num, changeNum, type);
                    if (isValue(postNum)) {
                        visit[postNum] = true;
                        q.offer(new Node(postNum, cur.cnt + 1));
                    }
                }
            }
        }
        return false;
    }

    private static int getChangeNum(int num, int changeNum, int type) {
        int resultNum = 0;
        switch (type) {
            case 1:
                resultNum = changeNum * 1000;
                resultNum += (num - num / 1000 * 1000);
                break;
            case 2:
                resultNum = changeNum * 100;
                resultNum += (num / 1000 * 1000) + (num - num / 100 * 100);
                break;
            case 3:
                resultNum = changeNum * 10;
                resultNum += (num / 100 * 100) + (num - num / 10 * 10);
                break;
            case 4:
                resultNum = changeNum;
                resultNum += num / 10 * 10;
                break;
        }
        return resultNum;
    }

    private static boolean isValue(int num) {
        return num >= 1000 && num < MAX && !visit[num] && !prime[num];
    }

    private static class Node {
        private int num;
        private int cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
