package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class bj_1526_구현 {
    private static String answer = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int originNum = Integer.parseInt(br.readLine());
        int answer = 0;
        Queue<Integer> q = new LinkedList<>();
        if(originNum >= 4){
            q.add(4);
            answer = 4;
        }
        if(originNum >= 7){
            q.add(7);
            answer = 7;
        }

        while (!q.isEmpty()){
            int nowNum = q.poll();
            nowNum *= 10;
            nowNum += 4;

            if(originNum >= nowNum){
                answer = nowNum;
                q.add(nowNum);
            }

            nowNum += 3;
            if(originNum >= nowNum){
                answer = nowNum;
                q.add(nowNum);
            }
        }
        System.out.println(answer);
    }
}
