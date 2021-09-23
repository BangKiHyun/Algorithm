package baekJ.towpointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_14719_빗물 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] height = new int[w];
        for (int idx = 0; idx < w; idx++) {
            height[idx] = Integer.parseInt(st.nextToken());
        }

        int leftPos = 0;
        int rightPos = w - 1;
        int maxLeftHeight = height[leftPos];
        int maxRightHeight = height[rightPos];
        int answer = 0;
        while (leftPos <= rightPos) {
            if (maxLeftHeight < maxRightHeight) {
                maxLeftHeight = Math.max(maxLeftHeight, height[leftPos]);
                answer += maxLeftHeight - height[leftPos++];
            } else {
                maxRightHeight = Math.max(maxRightHeight, height[rightPos]);
                answer += maxRightHeight - height[rightPos--];
            }
        }
        System.out.println(answer);
    }
}
