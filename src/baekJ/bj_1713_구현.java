package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//월드초등학교 학생회장 후보는 일정 기간 동안 전체 학생의 추천에 의하여 정해진 수만큼 선정된다.
//그래서 학교 홈페이지에 추천받은 학생의 사진을 게시할 수 있는 사진틀을 후보의 수만큼 만들었다.
//추천받은 학생의 사진을 사진틀에 게시하고 추천받은 횟수를 표시하는 규칙은 다음과 같다.
//
//1.학생들이 추천을 시작하기 전에 모든 사진틀은 비어있다.
//2.어떤 학생이 특정 학생을 추천하면, 추천받은 학생의 사진이 반드시 사진틀에 게시되어야 한다.
//  비어있는 사진틀이 없는 경우에는 현재까지 추천 받은 횟수가 가장 적은 학생의 사진을 삭제하고, 그 자리에 새롭게 추천받은 학생의 사진을 게시한다.
//3.이때, 현재까지 추천 받은 횟수가 가장 적은 학생이 두 명 이상일 경우에는 게시된 지 가장 오래된 사진을 삭제한다.
//
//4.현재 사진이 게시된 학생이 다른 학생의 추천을 받은 경우에는 추천받은 횟수만 증가시킨다.
//5.사진틀에 게시된 사진이 삭제되는 경우에는 해당 학생이 추천받은 횟수는 0으로 바뀐다.
//후보의 수 즉, 사진틀의 개수와 전체 학생의 추천 결과가 추천받은 순서대로 주어졌을 때, 최종 후보가 누구인지 결정하는 프로그램을 작성하시오.
public class bj_1713_구현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int recoCnt = Integer.parseInt(br.readLine());

        ArrayList<Candidate> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Candidate(0, 0));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreElements()) {
            int candidate = Integer.parseInt(st.nextToken());
            boolean flag = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).idx == candidate) {
                    list.get(i).cnt++;
                    flag = true;
                }
            }

            if (flag) continue;
            list.add(new Candidate(candidate, 1));

            int min = 1001;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).cnt < min) {
                    min = list.get(i).cnt;
                }
            }
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).cnt == min) {
                    list.remove(i);
                    break;
                }
            }
        }
        Collections.sort(list, Candidate::compareTo);
        for (Candidate candidate : list) {
            if (candidate.idx == 0) continue;
            System.out.print(candidate.idx + " ");
        }
    }

    private static class Candidate implements Comparable<Candidate> {
        private int idx;
        private int cnt;

        public Candidate(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Candidate o) {
            return this.idx - o.idx;
        }
    }
}