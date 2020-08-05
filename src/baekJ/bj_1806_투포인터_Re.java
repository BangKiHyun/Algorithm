package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다.
//이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중, 가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.
public class bj_1806_투포인터_Re {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>();
        while (st.hasMoreElements()) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        int start = 0;
        int end = 0;
        int curSum = 0;
        int length = Integer.MAX_VALUE;

        while (end < n) {
            if (curSum < s) {
                curSum += list.get(end++);
            } else {
                length = Math.min(length, end - start);
                curSum -= list.get(start++);
            }
        }

        while (start < n) {
            if (curSum >= s) {
                length = Math.min(length, end - start);
            }
            curSum -= list.get(start++);
        }

        System.out.println(length == Integer.MAX_VALUE ? 0 : length);
    }
}
