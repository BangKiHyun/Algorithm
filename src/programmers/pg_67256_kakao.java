package programmers;

import java.util.Arrays;
import java.util.List;

public class pg_67256_kakao {
    private static final String right = "R";
    private static final String left = "L";

    private static List<Pos> list = Arrays.asList(
            new Pos(3, 1),
            new Pos(0, 0),
            new Pos(0, 1),
            new Pos(0, 2),
            new Pos(1, 0),
            new Pos(1, 1),
            new Pos(1, 2),
            new Pos(2, 0),
            new Pos(2, 1),
            new Pos(2, 2));

    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";
        System.out.println(solution(numbers, hand));
    }

    private static String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        Pos rightHand = new Pos(3, 2);
        Pos leftHand = new Pos(3, 0);

        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];

            if (isLeftHand(number)) {
                leftHand = list.get(number);
                sb.append(left);
            } else if (isRightHand(number)) {
                rightHand = list.get(number);
                sb.append(right);
            } else {
                if (closeToRight(number, rightHand, leftHand, hand)) {
                    rightHand = list.get(number);
                    sb.append(right);
                } else {
                    leftHand = list.get(number);
                    sb.append(left);
                }
            }
        }

        return String.valueOf(sb);
    }

    private static boolean isLeftHand(int number) {
        return number == 1 || number == 4 || number == 7;
    }

    private static boolean isRightHand(int number) {
        return number == 3 || number == 6 || number == 9;
    }

    private static boolean closeToRight(int number, Pos rightHand, Pos leftHand, String myHand) {
        int rightDistance = getDistance(list.get(number), rightHand);
        int leftDistance = getDistance(list.get(number), leftHand);

        if (rightDistance < leftDistance || (rightDistance == leftDistance && myHand.equals("right"))) {
            return true;
        }
        return false;
    }

    private static int getDistance(Pos cur, Pos rightHand) {
        return Math.abs(cur.x - rightHand.x) + Math.abs(cur.y - rightHand.y);
    }

    private static class Pos {
        private int x;
        private int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
