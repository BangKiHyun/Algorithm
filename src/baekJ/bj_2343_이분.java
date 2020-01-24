package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_2343_이분 {
    //강토는 자신의 기타 레슨 동영상을 블루레이로 만들어 판매하려고 한다. 블루레이에는 총 N개의 레슨이 들어가는데, 블루레이를 녹화할 때, 레슨의 순서가 바뀌면 안 된다. 순서가 뒤바뀌는 경우에는 레슨의 흐름이 끊겨, 학생들이 대혼란에 빠질 수 있기 때문이다. 즉, i번 레슨과 j번 레슨을 같은 블루레이에 녹화하려면 i와 j 사이의 모든 레슨도 같은 블루레이에 녹화해야 한다.
    //
    //강토는 이 블루레이가 얼마나 팔릴지 아직 알 수 없기 때문에, 블루레이의 개수를 가급적 줄이려고 한다. 오랜 고민 끝에 강토는 M개의 블루레이에 모든 기타 레슨 동영상을 녹화하기로 했다. 이때, 블루레이의 크기(녹화 가능한 길이)를 최소로 하려고 한다. 단, M개의 블루레이는 모두 같은 크기이어야 한다.
    //
    //강토의 각 레슨의 길이가 분 단위(자연수)로 주어진다. 이때, 가능한 블루레이의 크기 중 최소를 구하는 프로그램을 작성하시오.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //첫째 줄에 레슨의 수 N (1 ≤ N ≤ 100,000)과 M (1 ≤ M ≤ N)이 주어진다.
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 다음 줄에는 강토의 기타 레슨의 길이가 레슨 순서대로 분 단위로(자연수)로 주어진다. 각 레슨의 길이는 10,000분을 넘지 않는다.
        int[] lesson = new int[n];
        st = new StringTokenizer(br.readLine());
        int i = 0;
        int max = 0;
        while (st.hasMoreElements()) {
            lesson[i] = Integer.parseInt(st.nextToken());
            max += lesson[i];
            i++;
        }

        int min = 1;
        int mid;

        while (min <= max) {
            mid = (min + max) / 2;

            int blueray_cnt = getBlueRayCount(lesson, mid, max);

            if (blueray_cnt > m) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        System.out.println(min);
    }

    private static int getBlueRayCount(int[] lesson, int len, int max) {
        int num = 1;
        int sum = 0;

        for (int i = 0; i < lesson.length; i++) {
            if (lesson[i] > len) {
                num = max;
                break;
            }
            sum += lesson[i];
            if (sum > len) {
                sum = lesson[i];
                num++;
            }
        }
        return num;
    }
}
