package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

import static java.lang.Math.min;

public class NetworkFlow {
    public static final int MAX = 100;
    public static final int INF = 1000000000;

    static int n = 6, result;
    static int c[][] = new int[MAX][MAX];
    static int f[][] = new int[MAX][MAX];
    static int d[] = new int[MAX];
    static ArrayList<Integer> a[] = new ArrayList[MAX];

    static void maxFlow(int start, int end){
        while (true){
            Arrays.fill(d,-1);
            Queue q =new LinkedList();
            q.offer(start);
            while (!q.isEmpty()){
                int x = (int)q.poll();
                for(int i: a[x]){
                    if(c[x][i] - f[x][i] > 0 && d[i] == -1){
                        q.offer(i);
                        d[i] = x;
                        if(i == end) break;
                    }
                }
            }
            if(d[end] == -1) break;
            int flow = INF;
            for(int i = end; i != start; i = d[i]){
                flow = min(flow, c[d[i]][i] - f[d[i]][i]);
            }
        }
    }
}
