package programmers;

import java.util.HashMap;
import java.util.Map;

public class pg_49993_Re {
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        System.out.println(solution(skill, skill_trees));
    }

    public static int solution(String skill, String[] skill_trees) {
        Map<Character, Integer> map = new HashMap<>();

        int idx = 1;
        for (char s : skill.toCharArray()) {
            map.put(s, idx++);
        }

        int ans = 0;
        for (String skillTree : skill_trees) {
            int curIdx = 1;
            boolean pass = true;
            for (char s : skillTree.toCharArray()) {
                if (map.containsKey(s)) {
                    if (map.get(s) == curIdx) {
                        curIdx++;
                    } else {
                        pass = false;
                        break;
                    }
                }
            }
            if (pass) {
                ans++;
            }
        }
        return ans;
    }
}
