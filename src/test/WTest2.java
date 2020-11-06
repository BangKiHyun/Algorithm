package test;

import java.util.StringTokenizer;

public class WTest2 {
    public static void main(String[] args) {
        String logs = "2019/05/01 23:59:19\n" +
                "2019/06/01 22:35:20\n" +
                "2019/08/01 14:01:22\n" +
                "2019/08/01 15:01:23\n" +
                "2019/08/02 03:02:35\n" +
                "2019/10/03 04:05:40\n" +
                "2019/10/04 06:23:10\n" +
                "2019/10/10 08:23:20\n" +
                "2019/10/12 08:42:24\n" +
                "2019/10/23 08:43:26\n" +
                "2019/11/14 08:43:29\n" +
                "2019/11/01 10:19:02\n" +
                "2019/12/01 11:23:10";

        for (int ans : solution(logs)) {
            System.out.print(ans + " ");
        }
    }

    public static int[] solution(String logs) {
        int[] ans = new int[24];
        StringTokenizer st = new StringTokenizer(logs, "\n");
        while (st.hasMoreElements()) {
            ans[getAmericanHour(st.nextToken())]++;
        }

        return ans;
    }

    private static int getAmericanHour(String dateTime) {
        String time = getTime(dateTime);
        int hour = Integer.parseInt(time.split(":")[0]);
        int americanHour = hour + 9;
        if (americanHour >= 24) {
            return americanHour - 24;
        }
        return americanHour;
    }

    private static String getTime(String dateTime) {
        return dateTime.split(" ")[1];
    }
}
