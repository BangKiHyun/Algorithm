package programmers;

public class pg_43163 {

    static boolean[] v;
    public static int dfs(String begin, String target, String[] words) {
        int answer = 0;
        String dupBegin;

        for(int i = 0; i < words.length; i++) {
            if(v[i]) continue;

            for(int j = 0; j < begin.length(); j++) {
                dupBegin = begin;
                dupBegin = (j > 0 ? begin.substring(0, j) : "")
                        + words[i].charAt(j) + (j < begin.length()-1 ? begin.substring(j+1) : "");

                if(dupBegin.equals(target))
                    return 1;
                if(dupBegin.equals(words[i])) {
                    v[i] = true;
                    answer = 1;
                    answer += dfs(dupBegin, target, words);
                }
            }
        }
        return answer;
    }

    public static int solution(String begin, String target, String[] words) {
        v = new boolean[words.length + 1];
        for(int i = 0; i < words.length; i++){
            if(target.equals(words[i]))
                return dfs(begin, target, words);
        }
        return 0;
    }


    public static void main(String[] args) {
        String b = "hit";
        String t = "cog";
        String words[] = {"hot", "dot", "dog", "lot", "log", "cog"};
        int ans = solution(b, t, words);
        System.out.println(ans);
    }
}
