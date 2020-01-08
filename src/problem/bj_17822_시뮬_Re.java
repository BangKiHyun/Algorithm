package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_17822_시뮬_Re {
    private static final int RIGHT = 0;
    private static int[][] plate;
    private static double sum = 0;
    private static int existNum = 0;
    private static ArrayList<Integer>[] lists;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int cnt = Integer.parseInt(st.nextToken());
        plate = new int[n + 1][m];
        existNum = n * m;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                plate[i][j] = Integer.parseInt(st.nextToken());
                sum += plate[i][j];
            }
        }

        for (int i = 0; i < cnt; i++) {
            if (existNum == 0) {
                System.out.println(0);
                return;
            }
            st = new StringTokenizer(br.readLine());
            int plateNum = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int rotateCnt = Integer.parseInt(st.nextToken());
            rotatePlate(plateNum, direction, rotateCnt);

            if (isAdjacency()) {
                deletePlate();
            } else {
                double avg = getAvg();
                changePlate(avg);
            }
        }
        System.out.println((int) sum);
    }

    private static void rotatePlate(int plateNum, int direction, int rotateCnt) {
        if (rotateCnt == 0) return;
        int mulNum = plateNum;
        while (plateNum < plate.length) {
            if (direction == RIGHT) rotateRight(plateNum);
            else rotateLeft(plateNum);

            if (isOne(mulNum)) plateNum++;
            else plateNum *= mulNum;
        }
        rotatePlate(mulNum, direction, --rotateCnt);
    }

    private static void rotateRight(int idx) {
        int temp1 = plate[idx][0];
        for (int i = 1; i < plate[idx].length; i++) {
            int temp2 = plate[idx][i];
            plate[idx][i] = temp1;
            temp1 = temp2;
        }
        plate[idx][0] = temp1;
    }

    private static void rotateLeft(int idx) {
        int temp1 = plate[idx][0];
        for (int i = plate[idx].length - 1; i > 0; i--) {
            int temp2 = plate[idx][i];
            plate[idx][i] = temp1;
            temp1 = temp2;
        }
        plate[idx][0] = temp1;
    }

    private static boolean isOne(int num) {
        return num == 1;
    }

    private static boolean isAdjacency() {
        boolean flag = false;
        lists = new ArrayList[plate.length];
        for (int i = 1; i < plate.length; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 1; i < plate.length; i++) {
            for (int j = 0; j < plate[0].length; j++) {
                int num3, num4;
                if (i < plate.length - 1) {
                    num3 = plate[i][j];
                    num4 = plate[i + 1][j];
                    if (!isZero(num3, num4) && isSame(num3, num4)) {
                        flag = true;
                        if (!lists[i].contains(j)) {
                            lists[i].add(j);
                            sum -= num3;
                            existNum--;
                        }
                        if (!lists[i + 1].contains(j)) {
                            lists[i + 1].add(j);
                            sum -= num4;
                            existNum--;
                        }
                    }
                }
                int num1, num2;
                int pos;
                if (isLastIdx(j)) {
                    num1 = plate[i][j];
                    num2 = plate[i][0];
                    pos = 0;
                } else {
                    num1 = plate[i][j];
                    num2 = plate[i][j + 1];
                    pos = j + 1;
                }

                if (!isZero(num1, num2) && isSame(num1, num2)) {
                    flag = true;
                    if (!lists[i].contains(pos)) {
                        lists[i].add(pos);
                        sum -= num2;
                        existNum--;
                    }
                    if (!lists[i].contains(j)) {
                        lists[i].add(j);
                        sum -= num1;
                        existNum--;
                    }
                }
            }
        }
        return flag;
    }

    private static boolean isLastIdx(int idx) {
        return idx == plate[0].length - 1;
    }

    private static boolean isZero(int n1, int n2) {
        return n1 == 0 || n2 == 0;
    }

    private static boolean isSame(int n1, int n2) {
        return n1 == n2;
    }

    private static void deletePlate() {
        for (int i = 1; i < plate.length; i++) {
            for (int j : lists[i]) {
                plate[i][j] = 0;
            }
        }
    }

    private static double getAvg() {
        return sum / existNum;
    }

    private static void changePlate(double avg) {
        for (int i = 1; i < plate.length; i++) {
            for (int j = 0; j < plate[0].length; j++) {
                if (plate[i][j] != 0 && plate[i][j] != avg) {
                    if (isBigger(plate[i][j], avg)) plate[i][j]--;
                    else plate[i][j]++;
                }
            }
        }
    }

    private static boolean isBigger(int plateNum, double avg) {
        return plateNum > avg;
    }
}