package test;

public class MyTest1_1 {

    public static void main(String[] args) {
        String[] recommend_list = {"cow1", "cow2", "cow3", "answer1", "answer2", "answer3"};
        String new_id = "answer1";
        final MyTest1_1 task = new MyTest1_1();
        System.out.println(task.solution(recommend_list, new_id));
    }

    public String solution(String[] recommend_list, String new_id) {
        String[] strAndNumber = divide(new_id);
        String answer = new_id;
        while (true) {
            if (contains(recommend_list, strAndNumber[0] + strAndNumber[1])) {
                strAndNumber[1] = String.valueOf(Integer.parseInt(strAndNumber[1]) + 1);
                answer = strAndNumber[0] + strAndNumber[1];
            } else {
                if (strAndNumber[1].equals("0")) answer = strAndNumber[0];
                else answer = strAndNumber[0] + strAndNumber[1];
                break;
            }
        }
        return answer;
    }

    private String[] divide(String new_id) {
        int divideIdx = 0;
        for (char id : new_id.toCharArray()) {
            if (id >= '0' && id <= '9') {
                break;
            }
            divideIdx++;
        }
        if (divideIdx == new_id.length()) {
            return new String[]{new_id, String.valueOf(0)};
        }
        return new String[]{new_id.substring(0, divideIdx), new_id.substring(divideIdx)};
    }

    private boolean contains(String[] recommend_list, String id) {
        for (String recommendId : recommend_list) {
            if (recommendId.equals(id)) return true;
        }
        return false;
    }
}
