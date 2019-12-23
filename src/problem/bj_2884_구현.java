package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_2884_구현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());

        if (minute >= 45) {
            System.out.println(hour + " " + (minute - 45));
        } else {
            minute = minute + 60 - 45;
            if (hour == 0) System.out.println(23 + " " + minute);
            else System.out.println(--hour + " " + minute);
        }
    }
}
