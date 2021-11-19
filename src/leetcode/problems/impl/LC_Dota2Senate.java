package leetcode.problems.impl;

public class LC_Dota2Senate {

    public static void main(String[] args) {
        String senate = "DR";
        final LC_Dota2Senate task = new LC_Dota2Senate();
        System.out.println(task.predictPartyVictory(senate));
    }

    public String predictPartyVictory(String senate) {
        char[] sArr = senate.toCharArray();
        int rCnt = 0;
        int dCnt = 0;
        for (char ch : sArr) {
            if (ch == 'R') {
                rCnt++;
            } else {
                dCnt++;
            }
        }
        int rRemove = 0;
        int dRemove = 0;
        while (rCnt != 0 && dCnt != 0) {
            for (int i = 0; i < sArr.length; i++) {
                if (sArr[i] == '#') {
                    continue;
                }
                if (sArr[i] == 'R') {
                    if (rRemove > 0) {
                        rRemove--;
                        rCnt--;
                        sArr[i] = '#';
                    } else {
                        dRemove++;
                    }
                } else {
                    if (dRemove > 0) {
                        dRemove--;
                        dCnt--;
                        sArr[i] = '#';
                    } else {
                        rRemove++;
                    }
                }
            }
        }
        return rCnt != 0 ? "Radiant" : "Dire";
    }
}
