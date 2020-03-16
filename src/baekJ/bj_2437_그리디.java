package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//무게가 양의 정수인 N개의 저울추가 주어질 때, 이 추들을 사용하여 측정할 수 없는 양의 정수 무게 중 최솟값을 구하는 프로그램을 작성하시오.
//
//예를 들어, 무게가 각각 3, 1, 6, 2, 7, 30, 1인 7개의 저울추가 주어졌을 때, 이 추들로 측정할 수 없는 양의 정수 무게 중 최솟값은 21이다.
public class bj_2437_그리디 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> weight_list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            weight_list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(weight_list);
        long sum = 0;
        for (int i : weight_list) {
            if (i <= sum + 1) {
                sum += i;
            }else{
                System.out.println(sum + 1);
                return;
            }
        }
        System.out.println(sum + 1);
    }
}

//1 1 2 3 6 7 30
//5455++ 또는 5459--