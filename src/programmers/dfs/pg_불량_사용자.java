package programmers.dfs;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class pg_불량_사용자 {

    private static Set<Set<String>> bannedCase = new HashSet<>();

    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "abc1**"};

        System.out.println(solution(user_id, banned_id));
    }

    public static int solution(String[] user_id, String[] banned_id) {
        Set<String> bannedList = new LinkedHashSet<>();
        goDFS(user_id, banned_id, bannedList);

        return bannedCase.size();
    }

    private static void goDFS(String[] user_id, String[] banned_id, Set<String> bannedList) {
        if (bannedList.size() == banned_id.length) {
            if (isAllBanned(bannedList, banned_id)) {
                bannedCase.add(new HashSet<>(bannedList));
                return;
            }
        }

        for (String user : user_id) {
            if (!bannedList.contains(user)) {
                bannedList.add(user);
                goDFS(user_id, banned_id, bannedList);
                bannedList.remove(user);
            }
        }
    }

    private static boolean isAllBanned(Set<String> bannedList, String[] banned_id) {
        int idx = 0;
        for (String maybeBannedId : bannedList) {
            if (isNotBanned(maybeBannedId, banned_id[idx++])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNotBanned(String maybeBannedId, String bannedId) {
        if (maybeBannedId.length() != bannedId.length()) {
            return true;
        }

        for (int i = 0; i < bannedId.length(); i++) {
            char bannedText = bannedId.charAt(i);
            if (bannedText != '*' && maybeBannedId.charAt(i) != bannedText) {
                return true;
            }
        }
        return false;
    }
}
