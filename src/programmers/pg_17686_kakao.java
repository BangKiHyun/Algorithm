package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class pg_17686_kakao {
    public static void main(String[] args) {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};

        for (String s : solution(files)) {
            System.out.println(s);
        }
    }

    private static String[] solution(String[] files) {
        ArrayList<File> list = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            int len = files[i].length();
            String head = getHead(files[i]);
            String number = getNumber(files[i].substring(head.length(), len));
            String tail = getTail(files[i], head.length() + number.length(), len);

            list.add(new File(head, number, tail));
        }

        Collections.sort(list, new FileComparator());

        String[] ans = new String[files.length];
        int idx = 0;
        for (File i : list) {
            ans[idx] = i.head + i.number + i.tail;
            idx++;
        }

        return ans;
    }

    private static String getHead(String file) {
        for (int i = 0; i < file.length(); i++) {
            if (isNumber(file.charAt(i))) return file.substring(0, i);
        }
        return "";
    }

    private static String getNumber(String file) {
        for (int i = 0; i < file.length(); i++) {
            if (!isNumber(file.charAt(i))) return file.substring(0, i);
        }
        return "";
    }

    private static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private static String getTail(String file, int start, int end) {
        return file.substring(start, end);
    }


    private static class File {
        String head;
        String number;
        String tail;

        public File(String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
    }

    private static class FileComparator implements Comparator<File> {
        @Override
        public int compare(File o1, File o2) {
            String firstHead = o1.head.toUpperCase();
            String secondHead = o2.head.toUpperCase();

            if (firstStringIsBigger(firstHead, secondHead)) return 1;
            else {
                if (firstHead.equals(secondHead)) {
                    if (firstNumberIsBigger(Integer.parseInt(o1.number), Integer.parseInt(o2.number))) return 1;
                    else {
                        if (Integer.parseInt(o1.number) == Integer.parseInt(o2.number)) {
                            return 1;
                        }
                        return -1;
                    }
                }
                return -1;
            }
        }

        private boolean firstStringIsBigger(String first, String second) {
            char[] c1 = first.toCharArray();
            char[] c2 = second.toCharArray();

            for (int i = 0; i < c1.length; i++) {
                if (c1[i] > c2[i]) return true;
                else return false;
            }
            return true;
        }

        private boolean firstNumberIsBigger(int num1, int num2) {
            return num1 > num2;
        }
    }
}

//TC
//{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
//{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
