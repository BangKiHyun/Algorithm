package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


//한 배열 A[1], A[2], …, A[n]에 대해서, 부 배열은 A[i], A[i+1], …, A[j-1], A[j] (단, 1 ≤ i ≤ j ≤ n)을 말한다.
//이러한 부 배열의 합은 A[i]+…+A[j]를 의미한다.
//각 원소가 정수인 두 배열 A[1], …, A[n]과 B[1], …, B[m]이 주어졌을 때,
//A의 부 배열의 합에 B의 부 배열의 합을 더해서 T가 되는 모든 부 배열 쌍의 개수를 구하는 프로그램을 작성하시오.
public class bj_2143_투포인터 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreElements()) {
            firstList.add(Integer.parseInt(st.nextToken()));
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreElements()) {
            secondList.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> firstSumList = createSumList(firstList);
        List<Integer> secondSumList = createSumList(secondList);
        Collections.sort(firstSumList);
        Collections.sort(secondSumList);

        int firstIdx = 0, secondIdx = secondSumList.size() - 1;
        long count = 0;
        while (firstIdx < firstSumList.size() && secondIdx >= 0) {
            int firstSum = firstSumList.get(firstIdx);
            int secondSum = secondSumList.get(secondIdx);
            long firstCount = 0;
            long secondCount = 0;

            if (firstSum + secondSum == T) {
                while (firstIdx < firstSumList.size() && firstSumList.get(firstIdx) == firstSum) {
                    firstIdx++;
                    firstCount++;
                }

                while (secondIdx >= 0 && secondSumList.get(secondIdx) == secondSum) {
                    secondIdx--;
                    secondCount++;
                }
            }

            count += firstCount * secondCount;

            if (firstSum + secondSum > T) {
                secondIdx--;
            } else if (firstSum + secondSum < T) {
                firstIdx++;
            }
        }

        System.out.println(count);
    }

    private static List<Integer> createSumList(List<Integer> list) {
        List<Integer> sumList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int sum = 0;
            for (int j = i; j < list.size(); j++) {
                sum += list.get(j);
                sumList.add(sum);
            }
        }

        return sumList;
    }
}
