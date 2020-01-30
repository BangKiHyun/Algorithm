package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_9576_이분매칭 {
    //백준이는 방 청소를 하면서 필요 없는 전공 서적을 사람들에게 나눠주려고 한다. 나눠줄 책을 모아보니 총 N권이었다. 책이 너무 많기 때문에 백준이는 책을 구분하기 위해 각각 1부터 N까지의 정수 번호를 중복되지 않게 매겨 두었다.
    //
    //조사를 해 보니 책을 원하는 서강대학교 학부생이 총 M명이었다. 백준이는 이 M명에게 신청서에 두 정수 a, b (1t든 책을 이미 다른 학생에게 주고 없다면 그 학생에게는 책을 주지 않는다.
    //
    //백준이가 책을 줄 수 있는 최대 학생 수를 구하시오.
    private static ArrayList<Integer>[] lists;
    private static boolean[] check;
    private static int[] book_num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //첫째 줄에 테스트 케이스의 수가 주어진다.
        int test_case = Integer.parseInt(br.readLine());
        while (test_case != 0) {
            test_case--;

            //각 케이스의 첫 줄에 정수 N(1 ≤ N ≤ 1,000)과 M(1 ≤ M ≤ 1,000)이 주어진다.
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            lists = new ArrayList[m + 1];
            check = new boolean[n + 1];
            book_num = new int[n + 1];
            for (int i = 1; i <= m; i++) {
                lists[i] = new ArrayList<>();
            }

            //다음 줄부터 M개의 줄에는 각각 정수 ai, bi가 주어진다. (1 ≤ ai ≤ bi ≤ N)
            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                for (int j = start; j <= end; j++) {
                    lists[i].add(j);
                }
            }

            int ans = 0;
            for (int i = 1; i <= m; i++) {
                Arrays.fill(check, false);
                if (goBipartiteMatching(i)) ans++;
            }

            System.out.println(ans);
        }
    }

    private static boolean goBipartiteMatching(int start) {
        for (int i : lists[start]) {
            if (check[i]) continue;
            check[i] = true;

            if (book_num[i] == 0 || goBipartiteMatching(book_num[i])) {
                book_num[i] = start;
                return true;
            }
        }
        return false;
    }
}
