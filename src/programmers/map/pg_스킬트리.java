package programmers.map;

import java.util.HashMap;
import java.util.Map;

public class pg_스킬트리 {
    private static Map<String, Integer> skillMap = new HashMap<>();

    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};

        System.out.println(solution(skill, skill_trees));
    }

    public static int solution(String skill, String[] skill_trees) {
        initSkillMap(skill);

        int correctCnt = 0;
        for (String skillTree : skill_trees) {
            if (isCorrectSkillTree(skillTree)) {
                correctCnt++;
            }
        }

        return correctCnt;
    }

    private static void initSkillMap(String skill) {
        int priority = 0;
        for (String s : skill.split("")) {
            skillMap.put(s, priority++);
        }
    }

    private static boolean isCorrectSkillTree(String skillTree) {
        int curPriority = 0;
        for (String skill : skillTree.split("")) {
            if (skillMap.containsKey(skill)) {
                if (skillMap.get(skill) != curPriority) {
                    return false;
                }
                curPriority++;
            }
        }
        return true;
    }
}
