package practice;

import java.util.ArrayList;
import java.util.LinkedList;

//순서가 정해진 작업 수행
//사이클이 발생하지 않는 방향 그래프에서 사용가능
public class Topology {
    public static final int MAX = 10;
    static int n, inDegree[] = new int[MAX];
    static ArrayList<Integer> a[] = new ArrayList[MAX];

    static void topolohySort() {
        int result[] = new int[MAX];
        LinkedList<Integer> q = new LinkedList<Integer>();

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        for (int i = 1; i <= n; i++) {
            if (q.isEmpty()) {
                System.out.print("사이클 발생!!");
                return;
            }
            int x = q.poll();
            result[i] = x;
            for (int j : a[x]) {
                if (--inDegree[j] == 0) {
                    q.offer(j);
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static void main(String[] args) {
        n = 7;
        for (int i = 0; i < MAX; i++) {
            a[i] = new ArrayList<>();
        }
        a[1].add(2);
        inDegree[2]++;
        a[1].add(5);
        inDegree[5]++;
        a[2].add(3);
        inDegree[3]++;
        a[3].add(4);
        inDegree[4]++;
        a[4].add(6);
        inDegree[6]++;
        a[5].add(6);
        inDegree[6]++;
        a[6].add(7);
        inDegree[7]++;
        topolohySort();
    }
}
