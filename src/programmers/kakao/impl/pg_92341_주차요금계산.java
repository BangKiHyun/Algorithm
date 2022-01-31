package programmers.kakao.impl;

import java.util.*;

public class pg_92341_주차요금계산 {

    public static void main(String[] args) {
        final pg_92341_주차요금계산 task = new pg_92341_주차요금계산();
        int[] fees = {180, 5000, 10, 600}; //기본 시간, 기본 요금, 단위 시간, 단위 요금
        String[] records = {"05:34 5961 IN",
                "06:00 0000 IN",
                "06:34 0000 OUT",
                "07:59 5961 OUT",
                "07:59 0148 IN",
                "18:59 0000 IN",
                "19:09 0148 OUT",
                "22:59 5961 IN",
                "23:00 5961 OUT"};
        for (int answer : task.solution(fees, records)) {
            System.out.print(answer + " ");
        }
    }

    public int[] solution(int[] fees, String[] records) {
        Map<String, ParkingInfo> numberMinutesMap = new HashMap<>();
        for (String record : records) {
            String[] minutesNumberCommand = getSplitValue(record);
            String number = minutesNumberCommand[1];
            int time = Integer.parseInt(minutesNumberCommand[0]);
            if (minutesNumberCommand[2].equals("IN")) {
                goEntrance(numberMinutesMap, number, time, fees[1]);
                continue;
            }
            numberMinutesMap.get(number).calculateTime(time);
            numberMinutesMap.get(number).initTime();
        }

        int leaveTime = Integer.parseInt(convertToMinute("23:59"));
        List<String> keyList = new ArrayList<>(numberMinutesMap.keySet());
        Collections.sort(keyList);
        int[] answer = new int[keyList.size()];
        int answerIdx = 0;
        for (String number : keyList) {
            ParkingInfo parkingInfo = numberMinutesMap.get(number);
            if (!parkingInfo.alreadyCalculate()) {
                parkingInfo.calculateTime(leaveTime);
            }
            answer[answerIdx++] = parkingInfo.calculateFee(fees[0], fees[2], fees[3]);
        }
        return answer;
    }

    private void goEntrance(Map<String, ParkingInfo> numberMinutesMap, String number, int time, int baseFee) {
        if (numberMinutesMap.containsKey(number)) {
            numberMinutesMap.get(number).enterTime = time;
        } else {
            numberMinutesMap.put(number, new ParkingInfo(time, baseFee));
        }
    }

    private String[] getSplitValue(String record) {
        String[] splitValue = record.split(" ");
        splitValue[0] = convertToMinute(splitValue[0]);
        return splitValue;
    }

    private String convertToMinute(String time) {
        final String[] hourMinutes = time.split(":");
        int hour = Integer.parseInt(hourMinutes[0]);
        int minutes = Integer.parseInt(hourMinutes[1]);
        return String.valueOf(minutes + (hour * 60));
    }

    private static class ParkingInfo {
        private int enterTime;
        private int totalFee;
        private int totalTime;

        public ParkingInfo(int enterTime, int fee) {
            this.enterTime = enterTime;
            this.totalFee = fee;
            this.totalTime = 0;
        }

        public void calculateTime(int leaveTime) {
            totalTime += leaveTime - enterTime;
        }

        public int calculateFee(int baseMinute, int unitMinute, int unitFee) {
            totalTime -= baseMinute;
            if (totalTime <= 0) return totalFee;
            if (totalTime % unitMinute == 0) {
                totalFee += (totalTime / unitMinute) * unitFee;
                return totalFee;
            }
            totalFee += ((totalTime / unitMinute) + 1) * unitFee;
            return totalFee;
        }

        public void initTime() {
            this.enterTime = -1;
        }

        public boolean alreadyCalculate() {
            return enterTime == -1;
        }

        public int getTotalFee() {
            return this.totalFee;
        }
    }
}
