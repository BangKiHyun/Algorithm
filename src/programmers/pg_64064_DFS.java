package programmers;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class pg_64064_DFS {

    private static Set<Set<String>> ans = new HashSet<>();

    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"*rodo", "*rodo", "******"};
        System.out.println(solution(user_id, banned_id));
    }

    private static int solution(String[] user_id, String[] banned_id) {
        Set<String> set = new LinkedHashSet<>();
        goDFS(user_id, banned_id, set);

        return ans.size();
    }

    private static void goDFS(final String[] user_id, final String[] banned_id, final Set<String> set) {
        if (set.size() == banned_id.length) {
            if (isAllBanned(set, banned_id)) {
                ans.add(new HashSet<>(set));
                return;
            }
        }

        for (String user : user_id) {
            if (!set.contains(user)) {
                set.add(user);
                goDFS(user_id, banned_id, set);
                set.remove(user);
            }
        }
    }

    private static boolean isAllBanned(final Set<String> set, final String[] banned_id) {
        int idx = 0;
        for (String userId : set) {
            if (!isContain(userId, banned_id[idx++])) {
                return false;
            }
        }

        return true;
    }

    private static boolean isContain(final String userId, final String bannedId) {
        int len = userId.length();
        if (bannedId.length() != len) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            char banned = bannedId.charAt(i);
            if (banned != '*' && banned != userId.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
//tc
//"frodo", "fradi", "crodo", "abc123", "frodoc"
//"fr*d*", "abc1**"
