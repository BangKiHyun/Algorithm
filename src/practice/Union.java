package practice;

import java.util.ArrayList;
import java.util.Scanner;

public class Union {
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

    static int findParent(int parent[], int a, int b){
        a = getParent(parent,a);
        b = getParent(parent, b);
        if(a==b)
            return 1;
        else
            return 0;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        int parent[] = new int [v+1];

        for(int i=1;i<=v;i++){
            parent[i] = i;
        }

        for(int i=0;i<e;i++){
            int x=sc.nextInt();
            int y=sc.nextInt();
            unionParent(parent, x, y);
        }
        System.out.print("1과 5는 연결되어있습니까? " + findParent(parent,1,3));
    }
}
