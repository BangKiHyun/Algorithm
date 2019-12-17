package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_2503_OOP_Re {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Baseball baseball = new Baseball();

        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            String num = st.nextToken();
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());
            baseball.list.add(new Baseball(num.split(""), strike, ball));
        }

        int ans = 0;
        for (int i = 123; i <= 987; i++) {
            boolean correct = true;
            if (baseball.isValid(Integer.toString(i).split(""))) {
                for (int j = 0; j < T; j++) {
                    int[] SB = baseball.getStrikeBallCnt(Integer.toString(i).split(""), j);
                    if (!baseball.isCorrect(SB, j)) {
                        correct = false;
                        break;
                    }
                }
                if (correct) ans++;
            }
        }
        System.out.println(ans);
    }

    private static class Baseball {
        private String[] number;
        private int strike_cnt;
        private int ball_cnt;
        ArrayList<Baseball> list = new ArrayList<>();

        public Baseball() {
        }

        public Baseball(String[] number, int strike_cnt, int ball_cnt) {
            this.number = number;
            this.strike_cnt = strike_cnt;
            this.ball_cnt = ball_cnt;
        }

        public boolean isValid(String[] candidate) {
            return !candidate[0].equals(candidate[1]) && !candidate[1].equals(candidate[2]) && !candidate[2].equals(candidate[0]) && !candidate[0].equals("0") && !candidate[1].equals("0") && !candidate[2].equals("0");
        }

        public int[] getStrikeBallCnt(String[] candidate, int idx) {
            int strike = 0;
            int ball = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (isStrike(i, j, idx, candidate)) strike++;
                    if (isBall(i, j, idx, candidate)) ball++;
                }
            }
            return new int[]{strike, ball};
        }

        private boolean isStrike(int i, int j, int idx, String[] candidate) {
            return i == j && list.get(idx).number[i].equals(candidate[j]);
        }

        private boolean isBall(int i, int j, int idx, String[] candidate) {
            return i != j && list.get(idx).number[i].equals(candidate[j]);
        }

        public boolean isCorrect(int[] SB, int idx) {
            return SB[0] == list.get(idx).strike_cnt && SB[1] == list.get(idx).ball_cnt;
        }
    }
}
