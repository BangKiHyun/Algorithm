package programmers;

public class pg_42586 {
    static public int[] solution(int[] progresses, int[] speeds) {
        int day[] = new int[progresses.length];
        int date[] = new int[progresses.length];
        int idx = 0;
        for (int i = 0; i < progresses.length; i++) {
            int cnt = 0;
            while (progresses[i] < 100) {
                cnt++;
                progresses[i] += speeds[i];
            }
            day[i] = cnt;
        }
        int first = day[0];
        int cnt = 0;
       for(int second : day){
            if(second > first){
                first = second;
                date[idx] = cnt;
                idx++;
                cnt = 0;
            }
            cnt++;
        }
       date[idx] = cnt;
       idx++;
       int[] answer = new int[idx];
       for(int i=0;i<idx;i++){
           answer[i] = date[i];
       }
       return answer;
    }

    public static void main(String[] args) {
        int[] p = {93, 30, 55};
        int[] s = {1, 30, 5};
        int result[] = solution(p, s);
    }
}
