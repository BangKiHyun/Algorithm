package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//상근이는 매일 아침 실험실로 출근해서 샘플의 무게를 재는 일을 하고 있다. 상근이는 두 샘플을 고른 뒤, 저울을 이용해서 무게의 차이를 잰다.
//
//교수님의 마음에 들기 위해서 매일 아침부터 무게를 재고 있지만, 가끔 교수님이 실험실에 들어와서 상근이에게 어떤 두 샘플의 무게의 차이를 물어보기도 한다.
//이때, 상근이는 지금까지 잰 결과를 바탕으로 대답을 할 수도 있고, 못 할 수도 있다.
//
//상근이는 결과를 출근한 첫 날부터 공책에 적어 두었다. 하지만, 엄청난 양의 무게가 적혀있기 때문에, 교수님의 질문에 재빨리 대답할 수가 없었다.
//이런 상근이를 위해서 프로그램을 만들려고 한다.
//
//상근이가 실험실에서 한 일이 순서대로 주어진다. 어떤 두 샘플의 무게의 차이를 구할 수 있는지 없는지를 알아내는 프로그램을 작성하시오.

//상근이가 무게를 쟀다면, ! a b w와 같은 형식으로 주어진다.
//이 뜻은 b가 a보다 w그램 무겁다는 뜻이다. (a ≠ b) w는 1,000,000을 넘지 않는 음이 아닌 정수이다. 모든 측정은 정확하고, 일관성을 유지한다.
//
//교수님의 질문은 ? a b와 같은 형식으로 주어진다. 이 뜻은 b가 a보다 얼마나 무거운지를 출력하라는 뜻이다.
//
//마지막 줄에는 0이 두 개 주어진다.
public class bj_3830_구현 {
    private static int[] parent;
    private static long[] weight;
    private static long[][] comparativeWeight;
    private static ArrayList<Integer>[] lists;
    private static boolean[] visit;

    private static final String EXPERIMENT = "!";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) {
                return;
            }

            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            lists = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                lists[i] = new ArrayList<>();
            }
            weight = new long[n + 1];
            comparativeWeight = new long[n + 1][n + 1];
            visit = new boolean[n + 1];
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                String text = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (text.equals(EXPERIMENT)) {
                    int w = Integer.parseInt(st.nextToken());
                    lists[a].add(b);
                    lists[b].add(a);
                    comparativeWeight[a][b] = w;
                    comparativeWeight[b][a] = -w;
                    unionParent(a, b);
                } else {
                    if (!findParent(a, b)) System.out.println("UNKNOWN");
                    else {
                        visit[a] = true;
                        checkConnected(a);
                        System.out.println(weight[b] - weight[a]);
                    }
                }
            }
        }
    }

    private static void performExperiment(int a, int b) {
        if (weight[a] == 0 && weight[b] == 0) {
            weight[b] = comparativeWeight[a][b];
        } else if (weight[a] == 0 && weight[b] != 0) {
            weight[a] = weight[b] - comparativeWeight[a][b];
        } else {
            weight[b] = weight[a] + comparativeWeight[a][b];
        }
    }

    private static void checkConnected(int from) {
        for (int to : lists[from]) {
            if (!visit[to]) {
                visit[to] = true;
                performExperiment(from, to);
                checkConnected(to);
            }
        }
    }

    private static void unionParent(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        if (a > b) parent[b] = a;
        else parent[a] = b;
    }

    private static boolean findParent(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        if (a == b) return true;
        else return false;
    }

    private static int getParent(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = getParent(parent[x]);
    }
}


//Union-FInd 이를 이용해서 ? 의 대상이 같은 Parent일 때 값 비교 가능
//Parent 값이 같지 않을 경우 UNKNOWN output
//? 일 때 값 비교 시 두번째 - 첫번째 값 output
//! 일 때 3가지 케이스로 나뉨
//첫 번째 a와 b의 값이 둘 다 0일 때 -> b=w
//두 번째 a만 0일 때 -> a=b-w
//세 번째 b만 0일 때 -> b=a+w

//ArrayList[] 를 이용해서 연관된 모든 숫자를 add 해준다
//weight[][] 배열을 만들어서 각각의 무게차이를 저장해준다
//교수님의 질문이 들어왔을 때 ArrayList[]에 연결되어 있는 값들을 차례대로 방문하며 각자의 weight 를 갱신 시켜준다.

/*
6 10
! 2 6 18
! 3 4 24
! 3 5 37
! 1 5 29
! 3 6 29
? 2 5
! 2 5 26
? 2 6
! 2 4 13
? 3 5
12 20
! 3 8 38
! 9 1 49
! 6 8 66
! 4 8 49
! 6 9 30
! 5 12 22
! 2 12 9
? 1 9
! 6 11 33
! 11 1 46
! 10 2 6
! 6 7 15
! 3 12 37
! 11 2 23
! 7 2 41
? 6 11
? 1 7
! 2 8 10
! 3 11 5
? 1 8
*/

/*
26
18
37
-49
33
-64
-13
*/