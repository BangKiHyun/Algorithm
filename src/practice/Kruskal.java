package practice;

import java.util.Arrays;
import java.util.Scanner;

//최소비용 신장 트리
public class Kruskal {
    static int getParent(int parent[], int x) {
        if (parent[x] == x) return x;
        return parent[x] = getParent(parent, parent[x]);
    }

    static void unionParent(int parent[], int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        if (a > b)
            parent[a] = b;
        else
            parent[b] = a;
    }

    static int findParent(int parent[], int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        if (a == b)
            return 1;
        else
            return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int parent[] = new int[8];
        for (int i = 1; i <= 7; i++) {
            parent[i] = i;
        }
        int arr[][] = new int[8][8];
        for (int i = 0; i<7;i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int dis = sc.nextInt();
            arr[a][b] = dis;
        }
        Arrays.sort(arr);

        for(int i=1;i<=7;i++){

        }
    }
}
