package practice;

import java.util.ArrayList;
import java.util.Scanner;

public class DFS_Searching {
    static int MAX = 30;
    static boolean c[] = new boolean[MAX];
    static ArrayList<Integer> a[] = new ArrayList[MAX];

    static void dfs(int x) {
        if (c[x]) return; //노드 방문 여부 검사
        c[x] = true;
        System.out.print(x + " "); //방문 과정이 제대로 이루어 졌는지 확인하는 출력문
        for (int i : a[x]) {
            dfs(i);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt(); // 노드 개수 선언
        int e = sc.nextInt(); // 간선 개수 선언
        int start = sc.nextInt(); //시작 노드 선언

        for (int i = 1; i <= v; i++) {
            a[i] = new ArrayList<>(); //초기화
        }

        for (int i = 0; i < e; i++) { // 연결 노드 선언
            int x = sc.nextInt();
            int y = sc.nextInt();
            a[x].add(y);
            a[y].add(x);
        }
        dfs(start);
    }
}
