package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_11399_그리디 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int inputCount = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        for (int cnt = 0; cnt < inputCount; cnt++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);

        int accumulateTime = 0;
        int totalTime = 0;
        for(int time : list){
            accumulateTime += time;
            totalTime += accumulateTime;
        }

        System.out.println(totalTime);
    }
}
