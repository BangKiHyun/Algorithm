package programmers.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pg_오픈채팅방_Re {

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi",
                "Enter uid4567 Prodo",
                "Leave uid1234",
                "Enter uid1234 Prodo",
                "Change uid4567 Ryan"};
        final pg_오픈채팅방_Re task = new pg_오픈채팅방_Re();
        for (String answer : task.solution(record)) {
            System.out.println(answer);
        }
    }

    public String[] solution(String[] record) {
        Map<String, String> idNameMap = new HashMap<>();
        List<CommandInfo> commandInfoList = new ArrayList<>();
        for (String command : record) {
            String[] commandIdName = command.split(" ");
            if (!commandIdName[0].equals("Leave")) {
                idNameMap.put(commandIdName[1], commandIdName[2]);
            }
            if (!commandIdName[0].equals("Change")) {
                commandInfoList.add(new CommandInfo(commandIdName[0], commandIdName[1]));
            }
        }

        String[] answer = new String[commandInfoList.size()];
        int answerIdx = 0;
        for (CommandInfo commandInfo : commandInfoList) {
            if (commandInfo.command.equals("Enter")) {
                answer[answerIdx++] = idNameMap.get(commandInfo.id) + "님이 들어왔습니다.";
            } else if (commandInfo.command.equals("Leave")) {
                answer[answerIdx++] = idNameMap.get(commandInfo.id) + "님이 나갔습니다.";
            }
        }
        return answer;
    }


    private static class CommandInfo {
        private String command;
        private String id;

        public CommandInfo(String command, String id) {
            this.command = command;
            this.id = id;
        }
    }
}