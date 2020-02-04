package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//언제나 최고만을 지향하는 굴지의 대기업 진영 주식회사가 신규 사원 채용을 실시한다. 인재 선발 시험은 1차 서류심사와 2차 면접시험으로 이루어진다. 최고만을 지향한다는 기업의 이념에 따라 그들은 최고의 인재들만을 사원으로 선발하고 싶어 한다.
//
//그래서 진영 주식회사는, 다른 모든 지원자와 비교했을 때 서류심사 성적과 면접시험 성적 중 적어도 하나가 다른 지원자보다 떨어지지 않는 자만 선발한다는 원칙을 세웠다. 즉, 어떤 지원자 A의 성적이 다른 어떤 지원자 B의 성적에 비해 서류 심사 결과와 면접 성적이 모두 떨어진다면 A는 결코 선발되지 않는다.
//
//이러한 조건을 만족시키면서, 진영 주식회사가 이번 신규 사원 채용에서 선발할 수 있는 신입사원의 최대 인원수를 구하는 프로그램을 작성하시오.
public class bj_1946_그리디 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에는 테스트 케이스의 개수 T(1 ≤ T ≤ 20)가 주어진다.
        int testcase = Integer.parseInt(br.readLine());

        while (testcase > 0) {
            testcase--;

            //각 테스트 케이스의 첫째 줄에 지원자의 숫자 N(1 ≤ N ≤ 100,000)이 주어진다.
            int n = Integer.parseInt(br.readLine());

            //둘째 줄부터 N개 줄에는 각각의 지원자의 서류심사 성적, 면접 성적의 순위가 공백을 사이에 두고 한 줄에 주어진다. 두 성적 순위는 모두 1위부터 N위까지 동석차 없이 결정된다고 가정한다.
            ArrayList<Score> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String[] score = br.readLine().split(" ");
                list.add(new Score(Integer.parseInt(score[0]), Integer.parseInt(score[1])));
            }

            Collections.sort(list, new ScoreComparator());

            if (n == 1) {
                System.out.println(1);
                return;
            } else if (n == 2) {
                System.out.println(2);
                return;
            }

            /*int pass_cnt = 1;
            for (int i = 1; i < list.size(); i++) {
                boolean pass = true;
                Score now = list.get(i);
                for (int j = 0; j < i; j++) {
                    int next_interview = list.get(j).interview;
                    if (next_interview < now.interview) {
                        pass = false;
                        break;
                    }
                }
                if (pass) pass_cnt++;
            }*/

            int pass_cnt = 1;
            int high_interview = list.get(0).interview;

            for (int i = 1; i < list.size(); i++) {
                int next_interview = list.get(i).interview;

                if (next_interview < high_interview) pass_cnt++;

                high_interview = Math.min(next_interview, high_interview);
            }

            System.out.println(pass_cnt);
        }
    }

    private static class Score {
        private int document;
        private int interview;

        public Score(int document, int interview) {
            this.document = document;
            this.interview = interview;
        }
    }

    private static class ScoreComparator implements Comparator<Score> {

        @Override
        public int compare(Score o1, Score o2) {
            return o1.document - o2.document;
        }
    }
}

//풀이 방법 1
//Comparator를 통해 서류점수 내림차순으로 정렬
//i=0부터 시작
//i보다 <은 신규 사원 중 자신의 면접점수보다 높은 사원이있으면 Fail 없으면 Pass

//가지치기 부분
//면접점수의 가장 높은 점수를 계속해서 갱신해줘야한다
//안그러면 시간초과남!