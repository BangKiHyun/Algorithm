package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

//남극에 사는 김지민 선생님은 학생들이 되도록이면 많은 단어를 읽을 수 있도록 하려고 한다.
//그러나 지구온난화로 인해 얼음이 녹아서 곧 학교가 무너지기 때문에, 김지민은 K개의 글자를 가르칠 시간 밖에 없다.
//김지민이 가르치고 난 후에는, 학생들은 그 K개의 글자로만 이루어진 단어만을 읽을 수 있다.
//김지민은 어떤 K개의 글자를 가르쳐야 학생들이 읽을 수 있는 단어의 개수가 최대가 되는지 고민에 빠졌다.
//
//남극언어의 모든 단어는 "anta"로 시작되고, "tica"로 끝난다. 남극언어에 단어는 N개 밖에 없다고 가정한다. 학생들이 읽을 수 있는 단어의 최댓값을 구하는 프로그램을 작성하시오.
public class bj_1062_DFS {
    private static int max = 0;
    private static HashSet<Character> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에 단어의 개수 N과 K가 주어진다. N은 50보다 작거나 같은 자연수이고, K는 26보다 작거나 같은 자연수 또는 0이다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        //둘째 줄부터 N개의 줄에 남극 언어의 단어가 주어진다. 단어는 영어 소문자로만 이루어져 있고, 길이가 8보다 크거나 같고, 15보다 작거나 같다. 모든 단어는 중복되지 않는다.
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String text = br.readLine();
            text = text.replaceAll("[antic]", "");
            list.add(text);
        }

        if (isNothing(k)) {
            System.out.println(0);
            return;
        }

        if (isAll(k)) {
            System.out.println(n);
            return;
        }

        initSet();

        int cnt = 5;
        teachAlphabet(0, list, cnt, k);

        System.out.println(max);
    }

    private static boolean isNothing(int k) {
        return k < 5;
    }

    private static boolean isAll(int k) {
        return k == 26;
    }

    private static void initSet() {
        set.add((char) 97);
        set.add((char) 99);
        set.add((char) 105);
        set.add((char) 110);
        set.add((char) 116);
    }

    private static void teachAlphabet(int start, ArrayList<String> list, int depth, int cnt) {
        if (cnt == depth) {
            max = Math.max(max, getKnowWords(list));
            return;
        }

        for (int i = start; i < 26; i++) {
            if (!set.contains((char) i + 97)) {
                set.add((char) (i + 97));
                teachAlphabet(i, list, depth + 1, cnt);
                set.remove((char) (i + 97));
            }
        }
    }

    private static int getKnowWords(ArrayList<String> list) {
        int cnt = 0;
        char[] word;
        boolean flag;
        for (String s : list) {
            flag = true;
            word = s.toCharArray();
            for (int i = 0; i < word.length; i++) {
                if (!set.contains(word[i])) {
                    flag = false;
                    break;
                }
            }
            if (flag) cnt++;
        }
        return cnt;
    }
}
