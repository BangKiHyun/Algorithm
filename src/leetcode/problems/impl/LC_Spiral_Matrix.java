package leetcode.problems.impl;

import java.util.ArrayList;
import java.util.List;

public class LC_Spiral_Matrix {

    public static void main(String[] args) {
        final LC_Spiral_Matrix problem = new LC_Spiral_Matrix();
        int[][] matrix = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        for (int answer : problem.spiralOrder(matrix)) {
            System.out.print(answer + " ");
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int topIdx = 0;
        int bottomIdx = matrix.length - 1;
        int leftIdx = 0;
        int rightIdx = matrix[0].length - 1;
        int dir = 0;

        List<Integer> answer = new ArrayList<>();
        while (topIdx <= bottomIdx && leftIdx <= rightIdx) {
            if (dir == 0) { // left -> right
                for (int idx = leftIdx; idx <= rightIdx; idx++) {
                    answer.add(matrix[topIdx][idx]);
                }
                topIdx++;
            } else if (dir == 1) { // top -> bottom
                for (int idx = topIdx; idx <= bottomIdx; idx++) {
                    answer.add(matrix[idx][rightIdx]);
                }
                rightIdx--;
            } else if (dir == 2) { // right -> left
                for (int idx = rightIdx; idx >= leftIdx; idx--) {
                    answer.add(matrix[bottomIdx][idx]);
                }
                bottomIdx--;
            } else { // bottom -> top
                for (int idx = bottomIdx; idx >= topIdx; idx--) {
                    answer.add(matrix[idx][leftIdx]);
                }
                leftIdx++;
            }
            dir = (dir + 1) % 4;
        }
        return answer;
    }
}
