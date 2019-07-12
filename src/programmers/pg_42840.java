package programmers;

public class pg_42840 {
    public static int[] solution(int[] answers) {
        int su1[] = {1, 2, 3, 4, 5};
        int su2[] = {2, 1, 2, 3, 2, 4, 2, 5};
        int su3[] = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int temp[] = new int[3];
        int idx = 0;
        int max = -1;
        int cnt = 0;

        temp[0] = count(su1, answers);
        temp[1] = count(su2, answers);
        temp[2] = count(su3, answers);

        for (int i = 0; i < 3; i++) {
            if (temp[i] > max) {
                max = temp[i];
            }
            ;
        }
        for (int i = 0; i < 3; i++) {
            if (temp[i] == max) {
                cnt++;
            }
        }
        int ans[] = new int[cnt];
        for (int i = 0; i < 3; i++) {
            if (temp[i] == max) {
                ans[idx] = i + 1;
                idx++;
            }
        }

        return ans;
    }

    public static int count(int su[], int[] answers) {
        int cnt = 0;
        int i = 0;
        int j = 0;
        while (i != answers.length) {
            if (answers[i] == su[j]) {
                cnt++;
            }
            i++;
            j++;
            if (j == su.length) {
                j = 0;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int ans[] = {1, 3, 2, 4, 2, 1, 2, 5, 4, 3, 5};
        int res[] = solution(ans);
    }
}
