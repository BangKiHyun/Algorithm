package SW.D4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_1226 {

    static final int N = 16;
    static int ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int ts=1; ts<=10; ts++) {
            ans=0;
            int T = sc.nextInt();

            int map[][] = new int[N][N];
            sc.nextLine();

            q = new LinkedList<>();
            visit = new boolean[N][N];
            for(int i=0; i<N; i++) {
                String line = sc.nextLine();
                for(int j=0; j<N; j++) {
                    map[i][j]=line.charAt(j)-'0';
                    if(map[i][j]==3) {
                        q.add(new Node(i,j));
                        visit[i][j]=true;
                    }
                }
            }
            bfs(map);
            System.out.println("#"+T+" "+ans);
        }
    }
    static int Y[] = {-1,1,0,0};
    static int X[] = {0,0,-1,1};

    static boolean visit[][];
    static void bfs(int map[][]) {
        while(!q.isEmpty()) {
            Node n = q.poll();
            for(int i=0; i<4; i++) {
                int ny = n.y+Y[i];
                int nx = n.x+X[i];

                if(ny<0 || nx<0 || ny>=N || nx>=N)
                    continue;
                if(visit[ny][nx])
                    continue;

                if(map[ny][nx]==0) {
                    q.add(new Node(ny,nx));
                    visit[ny][nx]=true;
                }
                if(map[ny][nx]==2) {
                    ans=1;
                    return;
                }
            }
        }
    }

    static Queue<Node> q;
    static class Node{
        int y, x;
        public Node(int y, int x) {
            super();
            this.y = y;
            this.x = x;
        }
    }
}