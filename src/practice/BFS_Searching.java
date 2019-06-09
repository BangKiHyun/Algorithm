package practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 미로찾기에 많이 쓰임
// rootNode에서 가까운 것 부터 검색
public class BFS_Searching {
    static int number = 7;
    static boolean c[] = new boolean[7];
    static ArrayList<Integer> array[] = new ArrayList[8];

    static void bfs(int start){
        Queue q = new LinkedList<Integer>();
        q.offer(start);
        while(!q.isEmpty()){
            int x = (int) q.poll();
            System.out.print(x+" ");
            for(int i : array[x]){
                if(!c[i]){
                    q.offer(i);
                    c[i] = true;
                }
            }
        }
    }

    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        for(int i=0;i<8;i++){
            array[i] = new ArrayList<>();
        }

        for(int i=1;i<=7;i++){
            int x =sc.nextInt();
            int y =sc.nextInt();
            array[x].add(y);
            array[y].add(x);
        }
        bfs(2);
    }
}
