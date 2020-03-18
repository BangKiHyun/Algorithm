package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//다솜이는 유료 고속도로를 가지고 있다. 다솜이는 현재 고속도로에 휴게소를 N개 가지고 있는데,
//휴게소의 위치는 고속도로의 시작으로부터 얼만큼 떨어져 있는지로 주어진다. 다솜이는 지금 휴게소를 M개 더 세우려고 한다.
//
//다솜이는 이미 휴게소가 있는 곳에 휴게소를 또 세울 수 없고, 고속도로의 끝에도 휴게소를 세울 수 없다.
//
//다솜이는 이 고속도로를 이용할 때, 모든 휴게소를 방문한다.
//다솜이는 휴게소를 M개 더 지어서 휴게소가 없는 구간의 길이의 최댓값을 최소로 하려고 한다. (반드시 M개를 모두 지어야 한다.)
public class bj_1477_이분 {
    private static int[] restingPlace;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int highwayLength = Integer.parseInt(st.nextToken());

        restingPlace = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            restingPlace[i] = Integer.parseInt(st.nextToken());
        }

        /*if (n == 0) {
            System.out.println(0);
            return;
        }

        if (m == 0) {
            for (int i = 1; i < n; i++) {
                highwayLength = Math.min(restingPlace[i] - restingPlace[i - 1], highwayLength);
            }
            System.out.println(highwayLength);
            return;
        }*/

        Arrays.sort(restingPlace);

        System.out.println(goBinarySearch(highwayLength, m));
    }

    private static int goBinarySearch(int highwayLength, int spareCount) {
        int min = 1;
        int max = highwayLength - 1;

        while (min <= max) {
            int mid = (min + max) / 2;
            int standCount = getStandCount(mid);

            if (standCount <= spareCount) max = mid - 1;
            else min = mid + 1;
        }

        return min;
    }

    private static int getStandCount(int divideLength) {
        int standCount = 0;

        standCount += restingPlace[0] / divideLength;
        if (isOverlap(restingPlace[0], divideLength)) standCount--;

        for (int i = 1; i < restingPlace.length; i++) {
            int interval = restingPlace[i] - restingPlace[i - 1];
            standCount += interval / divideLength;

            if (isOverlap(interval, divideLength)) standCount--;
        }

        return standCount;
    }

    private static boolean isOverlap(int interval, int length) {
        return interval % length == 0;
    }
}
