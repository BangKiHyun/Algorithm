package programmers.hash;

import java.util.HashMap;
import java.util.Map;

public class pg_오픈채팅방 {
    private static final String ENTER = "Enter";

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        for (String answer : solution(record)) {
            System.out.println(answer);
        }
    }

    public static String[] solution(String[] record) {
        Map<String, String> idNicknameMap = new HashMap<>();
        int size = 0;
        for (String line : record) {
            String[] maybeOperationIdNickname = line.split(" ");
            if (maybeOperationIdNickname.length == 3) {
                if (maybeOperationIdNickname[0].equals(ENTER)) {
                    size++;
                }
                idNicknameMap.put(maybeOperationIdNickname[1], maybeOperationIdNickname[2]);
            } else {
                size++;
            }
        }

        String[] answer = new String[size];
        int answerIdx = 0;
        for (String line : record) {
            String[] maybeOperationIdNickname = line.split(" ");
            if (maybeOperationIdNickname[0].equals(ENTER)) {
                answer[answerIdx++] = idNicknameMap.get(maybeOperationIdNickname[1]) + "님이 들어왔습니다.";
            } else if (maybeOperationIdNickname[0].equals("Leave")) {
                answer[answerIdx++] = idNicknameMap.get(maybeOperationIdNickname[1]) + "님이 나갔습니다.";
            }
        }
        return answer;
    }
}
