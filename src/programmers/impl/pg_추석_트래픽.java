package programmers.impl;

import java.util.ArrayList;
import java.util.List;

public class pg_추석_트래픽 {

    private static final String DELIMITER = " ";
    private static final String TIME_DELIMITER = ":";

    private static final int MSEC = 1_000;

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
        List<Log> logList = new ArrayList<>();
        for (String line : lines) {
            String[] dayTimeProcessingTime = line.split(DELIMITER);
            String time = dayTimeProcessingTime[1];
            String[] hhmmss = time.split(TIME_DELIMITER);
            long hour = toMsOfHour(hhmmss[0]);
            long minute = toMsOfMinute(hhmmss[1]);
            long second = toMsOfSecond(hhmmss[2]);

            long endTime = hour + minute + second;
            long startTime = endTime - toMsOfProcessingTime(dayTimeProcessingTime[2]) + 1;
            logList.add(new Log(startTime, endTime));
        }

        int maxProcessing = 0;
        for (Log log : logList) {
            long startSectionOfStartTime = log.startTime;
            long endSectionOfStartTime = log.startTime + MSEC;
            long startSectionOfEndTime = log.endTime;
            long endSectionOfEndTime = log.endTime + MSEC;

            int processingCountOfStartTime = 0;
            int processingCountOfEndTime = 0;
            for (Log targetLog : logList) {
                if (targetLog.startTime >= startSectionOfStartTime && targetLog.startTime < endSectionOfStartTime) {
                    processingCountOfStartTime++;
                } else if (targetLog.endTime >= startSectionOfStartTime && targetLog.endTime < endSectionOfStartTime) {
                    processingCountOfStartTime++;
                } else if (targetLog.startTime <= startSectionOfStartTime && targetLog.endTime >= endSectionOfStartTime) {
                    processingCountOfStartTime++;
                }

                if (targetLog.startTime >= startSectionOfEndTime && targetLog.startTime < endSectionOfEndTime) {
                    processingCountOfEndTime++;
                } else if (targetLog.endTime >= startSectionOfEndTime && targetLog.endTime < endSectionOfEndTime) {
                    processingCountOfEndTime++;
                } else if (targetLog.startTime <= startSectionOfEndTime && targetLog.endTime >= endSectionOfEndTime) {
                    processingCountOfEndTime++;
                }
            }
            maxProcessing = Math.max(maxProcessing, processingCountOfStartTime);
            maxProcessing = Math.max(maxProcessing, processingCountOfEndTime);
        }
        return maxProcessing;
    }

    private static long toMsOfHour(String strHour) {
        int hour = Integer.parseInt(strHour);
        return hour * 3600 * MSEC;
    }

    private static long toMsOfMinute(String strMinute) {
        int minute = Integer.parseInt(strMinute);
        return minute * 60 * MSEC;
    }

    private static long toMsOfSecond(String strSecond) {
        return (long) (Double.parseDouble(strSecond) * MSEC);
    }

    private static long toMsOfProcessingTime(String strProcessingTime) {
        strProcessingTime = strProcessingTime.substring(0, strProcessingTime.length() - 1);
        return toMsOfSecond(strProcessingTime);
    }

    private static class Log {
        private long startTime;
        private long endTime;

        public Log(long startTime, long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
