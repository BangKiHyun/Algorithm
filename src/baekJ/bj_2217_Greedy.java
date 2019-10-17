package baekJ;

import java.util.Arrays;
import java.util.Scanner;

public class bj_2217_Greedy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rope_num = sc.nextInt();
        int rope_list[] = new int[rope_num];
        int max = -1;

        for (int i = 0; i < rope_num; i++) {
            rope_list[i] = sc.nextInt();
        }

        //로프 정렬
        Arrays.sort(rope_list);
        //최대값 초기화
        max = rope_list[rope_num - 1];

        int rope_cnt = rope_num;
        for (int i = 0; i < rope_num; i++) {
            max = Math.max(max, (rope_list[i] * rope_cnt--));
        }
        System.out.println(max);
    }
}
