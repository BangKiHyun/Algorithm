package baekJ.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//스타트링크에는 세명의 직원이 일을 하고 있다. 세 직원의 이름은 강호(A), 준규(B), 수빈(C) 이다.
//이 회사의 직원은 특별한 룰을 가지고 있는데, 바로 하루에 한 명만 출근한다는 것이다. 3일간의 출근 기록이 "AAC"라는 것은 처음 이틀은 A가 출근했고, 셋째 날엔 C만 출근했다는 뜻이다.
//A는 매일 매일 출근할 수 있다. B는 출근한 다음날은 반드시 쉬어야 한다. C는 출근한 다음날과 다다음날을 반드시 쉬어야 한다. 따라서, 모든 출근 기록이 올바른 것은 아니다.
//예를 들어, B는 출근한 다음날 쉬어야 하기 때문에, "BB"는 절대로 나올 수 없는 출근 기록이다.
//출근 기록 S가 주어졌을 때, S의 모든 순열 중에서 올바른 출근 기록인 것 아무거나 출력하는 프로그램을 작성하시오.
public class bj_14238_출근기록_Timeout {
    private static char[] members;
    private static boolean[] visit;
    private static Map<Character, Integer> memberRequiredRestTimeMap;
    private static Deque<Character> attendanceRecordQueue;
    private static String answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(solution(br.readLine()));
    }

    private static String solution(String input) {
        if (input.isEmpty()) return String.valueOf(-1);

        members = input.toCharArray();
        visit = new boolean[members.length];
        initMemberRequiredRestTimeMap();
        attendanceRecordQueue = new LinkedList<>();

        if (findAnswer(members.length, 0)) {
            return answer;
        }
        return String.valueOf(-1);
    }

    private static void initMemberRequiredRestTimeMap() {
        memberRequiredRestTimeMap = new HashMap<>();
        memberRequiredRestTimeMap.put('A', 0);
        memberRequiredRestTimeMap.put('B', 1);
        memberRequiredRestTimeMap.put('C', 2);
    }

    private static boolean findAnswer(int depth, int count) {
        if (depth == count) {
            if (isCorrect()) {
                answer = getAnswer();
                return true;
            }
        }

        for (int idx = 0; idx < members.length; idx++) {
            if (!visit[idx]) {
                visit[idx] = true;
                attendanceRecordQueue.add(members[idx]);
                if (findAnswer(depth, count + 1)) {
                    return true;
                }
                attendanceRecordQueue.pollLast();
                visit[idx] = false;
            }
        }
        return false;
    }

    private static boolean isCorrect() {
        Map<Character, Integer> memberRestTimeMap = new HashMap<>();
        int time = -1;
        for (Character member : attendanceRecordQueue) {
            time++;
            if (!memberRestTimeMap.containsKey(member)) {
                memberRestTimeMap.put(member, memberRequiredRestTimeMap.get(member) + time);
                continue;
            }
            int requiredRestTime = memberRestTimeMap.get(member);
            if (requiredRestTime >= time) return false;
            memberRestTimeMap.put(member, time + memberRequiredRestTimeMap.get(member));
        }
        return true;
    }

    private static String getAnswer() {
        StringBuilder sb = new StringBuilder();
        while (!attendanceRecordQueue.isEmpty()) {
            sb.append(attendanceRecordQueue.poll());
        }
        return String.valueOf(sb);
    }
}
