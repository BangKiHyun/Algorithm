package programmers;

import java.util.*;

public class pg_17676_kakao {
    public static void main(String[] args) {
        String[] lines = {"2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"};

        System.out.println(solution(lines));
    }

    public static int solution(String[] lines) {
        List<Log> logs = new ArrayList<>();

        StringTokenizer st;
        for (String line : lines) {
            st = new StringTokenizer(line);
            st.nextToken();

            String time = st.nextToken();
            String[] arrTime = time.split(":");

            long hour = Long.parseLong(arrTime[0]) * 3600 * 1000;
            long minute = Long.parseLong(arrTime[1]) * 60 * 1000;
            long second = (long) ((Double.parseDouble(arrTime[2])) * 1000);

            long endTime = hour + minute + second;

            long precessingTime = (long) ((Double.parseDouble(st.nextToken().replace("s", ""))) * 1000);
            long startTime = endTime - precessingTime + 1;

            logs.add(new Log(startTime, endTime));
        }

        int maxCnt = 0;
        for (Log log : logs) {
            long startSectionOfStart = log.startTime;
            long endSectionOfStart = log.startTime + 1000;
            int startCnt = 0;

            long startSectionOfEnd = log.endTime;
            long endSectionOfEnd = log.endTime + 1000;
            int endCnt = 0;
            for (Log target : logs) {
                if(target.startTime >= startSectionOfStart && target.startTime < endSectionOfStart){
                    startCnt++;
                }else if(target.startTime <= startSectionOfStart && target.endTime >= endSectionOfStart){
                    startCnt++;
                }else if(target.endTime >= startSectionOfStart && target.endTime < endSectionOfStart){
                    startCnt++;
                }

                if(target.startTime >= startSectionOfEnd && target.startTime < endSectionOfEnd){
                    endCnt++;
                }else if(target.startTime <= startSectionOfEnd && target.endTime >= endSectionOfEnd){
                    endCnt++;
                }else if(target.endTime >= startSectionOfEnd && target.endTime < endSectionOfEnd){
                    endCnt++;
                }
            }
            maxCnt = Math.max(maxCnt, startCnt);
            maxCnt = Math.max(maxCnt, endCnt);
        }
        return maxCnt;
    }

    private static class Log {
        private long startTime;
        private long endTime;

        public Log(final long startTime, final long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
