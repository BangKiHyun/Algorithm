package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//초기에 {0}, {1}, {2}, ... {n} 이 각각 n+1개의 집합을 이루고 있다.
//여기에 합집합 연산과, 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행하려고 한다.
//
//집합을 표현하는 프로그램을 작성하시오.
//첫째 줄에 n(1≤n≤1,000,000), m(1≤m≤100,000)이 주어진다. m은 입력으로 주어지는 연산의 개수이다.
//다음 m개의 줄에는 각각의 연산이 주어진다. 합집합은 0 a b의 형태로 입력이 주어진다.
//이는 a가 포함되어 있는 집합과, b가 포함되어 있는 집합을 합친다는 의미이다.
//두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산은 1 a b의 형태로 입력이 주어진다.
//이는 a와 b가 같은 집합에 포함되어 있는지를 확인하는 연산이다.
//a와 b는 n 이하의 자연수 또는 0이며 같을 수도 있다.
public class bj_1717_UnionFind {
    private static final int UNION = 0;

    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int cnt = Integer.parseInt(st.nextToken());
        parents = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= cnt; i++) {
            st = new StringTokenizer(br.readLine());
            int cmp = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            if (cmp == UNION) {
                unionParent(first, second);
            } else {
                System.out.println(findParent(first, second) == true ? "YES" : "NO");
            }
        }
    }

    private static void unionParent(int first, int second) {
        first = getParent(first);
        second = getParent(second);

        if (first > second) {
            parents[first] = second;
        } else {
            parents[second] = first;
        }
    }

    private static int getParent(int number) {
        if (number == parents[number]) {
            return number;
        }

        return parents[number] = getParent(parents[number]);
    }

    private static boolean findParent(int first, int second) {
        first = getParent(first);
        second = getParent(second);

        return first == second;
    }
}
