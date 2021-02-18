package programmers.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class pg_셔틀버스 {

    public static void main(String[] args) {
        int n = 2;
        int t = 10;
        int m = 2;
        String[] timetable = {"09:10", "09:09", "08:00"};
        System.out.println(solution(n, t, m, timetable));
    }

    public static String solution(int n, int t, int m, String[] timetable) {
        List<Integer> crewArrivalTimes = Arrays.stream(timetable)
                .map(pg_셔틀버스::convertToMinute)
                .sorted()
                .collect(Collectors.toList());

        int answer = 0;
        int busTime = convertToMinute("09:00");
        while (n > 0) {
            n--;
            int capacityPerson = m;
            int lastBoardingTimeOfCrew = 0;

            while (!crewArrivalTimes.isEmpty()) {
                int crewArrivalTime = crewArrivalTimes.get(0);
                if (crewArrivalTime <= busTime && capacityPerson > 0) {
                    crewArrivalTimes.remove(0);
                    capacityPerson--;
                    lastBoardingTimeOfCrew = crewArrivalTime;
                } else {
                    break;
                }
            }

            // 마지막 버스가 아니면
            if (n > 0) {
                // 남아 있는 크루가 없으면 마지막 버스 도착시간 저장 후 끝냄
                if (crewArrivalTimes.isEmpty()) {
                    answer = busTime + (n + 1) * t;
                    break;
                }
                busTime += t;
            } else { // 마지막 버스면 수용 인원이 남아있는지 확인
                // 수용 인원이 남아있으면 버스시간 저장
                if (capacityPerson > 0) {
                    answer = busTime;
                } else { // 수용 인원이 남아있지 않으면 마지막에 탄 크루시간에서 1을 뺌
                    answer = lastBoardingTimeOfCrew - 1;
                }
                break;
            }
        }

        return convertToHHMM(answer);
    }

    private static Integer convertToMinute(String time) {
        String[] hourAndMinute = time.split(":");
        return Integer.parseInt(hourAndMinute[0]) * 60 + Integer.parseInt(hourAndMinute[1]);
    }

    private static String convertToHHMM(int time) {
        int hour = time / 60;
        int minute = time % 60;

        String strHour = hour < 10 ? "0" + hour : String.valueOf(hour);
        String strMinute = minute < 10 ? "0" + minute : String.valueOf(minute);

        return strHour + ":" + strMinute;
    }
}
