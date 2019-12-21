package programmers;

import java.util.HashMap;

public class pg_42888_kakao {
    public static void main(String[] args) {
        String recod[] = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        for (String s : solution(recod)) {
            System.out.println(s);
        }
    }

    private static String[] solution(String[] record) {
        HashMap<String, String> hashMap = new HashMap<>();
        int size = 0;
        for (String s : record) {
            String[] text = s.split(" ");
            if (text.length == 3) {
                if (text[0].equals("Enter")) size++;
                hashMap.put(text[1], text[2]);
            } else size++;
        }

        String[] answer = new String[size];
        String name;
        int idx = 0;
        for (int i = 0; i < record.length; i++) {
            String[] text = record[i].split(" ");
            name = hashMap.get(text[1]);
            if (text.length == 3 && text[0].equals("Enter")) {
                answer[idx] = name + "님이 들어왔습니다.";
                idx++;
            } else if (text.length == 2) {
                answer[idx] = name + "님이 나갔습니다.";
                idx++;
            }
        }
        return answer;
    }
}
