package baekJ;

import java.util.Scanner;

public class bj_2503_브루트포스 {
    private static int n;
    private static String baseball[] = new String[3];
    private static String candidate[][];
    private static int ans = 0;

    public static void main(String[] args) {
        init();
        findAnswer();
        System.out.println(ans);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        candidate = new String[n + 1][3];

        for (int i = 0; i <= n; i++) {
            String tmp = sc.nextLine();
            candidate[i] = tmp.split(" ");
        }
    }

    private static void findAnswer() {
        for (int i = 123; i <= 987; i++) {
            boolean correct = true;
            String[] num = (Integer.toString(i)).split("");
            if (isValid(num)) {
                for (int j = 1; j <= n; j++) {
                    int[] strikeballCnt = getStrikeAndBall(num, candidate[j][0].split(""));
                    if (!(strikeballCnt[0] == Integer.parseInt(candidate[j][1]) && strikeballCnt[1] == Integer.parseInt(candidate[j][2]))) {
                        correct = false;
                        break;
                    }
                }
                if (correct) ans++;
            }
        }
    }

    private static boolean isValid(String num[]) {
        if ((num[0] != num[1] && num[1] != num[2] && num[0] != num[2]) && (!num[0].equals("0") && !num[1].equals("0") && !num[2].equals("0")))
            return true;

        return false;
    }

    private static int[] getStrikeAndBall(String num[], String[] candidate) {
        int strikeballCnt[] = {0, 0};
        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (candidate[i] == num[j]) {
                    if (i == j) {
                        strikeballCnt[0]++;
                    } else {
                        strikeballCnt[1]++;
                    }
                }
            }
        }
        return strikeballCnt;
    }
}
