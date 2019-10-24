package problem;

import java.util.Scanner;

public class bj_17822_시뮬 {
    private static int n, m, k;
    private static int[][] state;
    private static int[][] copyState;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        state = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                state[i][j] = sc.nextInt();
            }
        }

        int stateNum, direction, rotateCnt;

        stateNum = sc.nextInt();
        direction = sc.nextInt();
        rotateCnt = sc.nextInt();

        rotateState(stateNum, direction, rotateCnt);

        int sum = 0;
        if (isChecked()) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(copyState[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println(getSum());
            return;
        } else {
            double avg = 0;
            sum = getSum();
            avg = getAvg(sum);
            changeState(avg);
        }

        System.out.println(getSum());
        return;
    }

    private static void rotateState(int stateNum, int direction, int rotateCnt) {
        while (rotateCnt != 0) {
            int tmpNum = stateNum;
            if (tmpNum == 1) {
                for (int i = 0; i < n; i++) {
                    if (direction == 0) {
                        rotateRight(tmpNum - 1);
                    } else {
                        rotateLeft(tmpNum - 1);
                    }
                }
                tmpNum++;
            } else {
                while (tmpNum <= n) {
                    if (direction == 0) {
                        rotateRight(tmpNum - 1);
                    } else {
                        rotateLeft(tmpNum - 1);
                    }
                    tmpNum *= tmpNum;
                }
            }
            rotateCnt--;
        }
    }

    private static void rotateRight(int stateNum) {
        int tmp1 = state[stateNum][0];

        for (int i = 0; i < m - 1; i++) {
            int tmp2 = state[stateNum][i + 1];
            state[stateNum][i + 1] = tmp1;
            tmp1 = tmp2;
        }
        state[stateNum][0] = tmp1;
    }

    private static void rotateLeft(int stateNum) {
        int tmp1 = state[stateNum][m - 1];

        for (int i = m - 1; i > 0; i--) {
            int tmp2 = state[stateNum][i - 1];
            state[stateNum][i - 1] = tmp1;
            tmp1 = tmp2;
        }
    }

    private static boolean isChecked() {
        boolean check = false;
        copyState = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copyState[i][j] = state[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j != m - 1) {
                    if (state[i][j] == state[i][j + 1]) {
                        copyState[i][j] = 0;
                        copyState[i][j + 1] = 0;
                        check = true;
                    }
                } else {
                    if (state[i][0] == state[i][j]) {
                        copyState[i][0] = 0;
                        copyState[i][j] = 0;
                        check = true;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (state[j][i] == state[j + 1][i]) {
                    copyState[j][i] = 0;
                    copyState[j + 1][i] = 0;
                    check = true;
                }
            }
        }
        return check;
    }

    private static int getSum() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += copyState[i][j];
            }
        }
        return sum;
    }

    private static double getAvg(int sum) {
        return sum / (n * m);
    }

    private static void changeState(double avg) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyState[i][j] != 0 && copyState[i][j] < avg) {
                    copyState[i][j]++;
                } else if (copyState[i][j] != 0 && copyState[i][j] > avg) {
                    copyState[i][j]--;
                }
            }
        }
    }
}
