package programmers;

import java.util.LinkedList;

public class pg_60058_OOP {
    public static void main(String[] args) {
        String p = //"(()())()";
                //"()))((()";
                ")(";
        System.out.println(solution(p));
    }

    private static String solution(String p) {
        Bracket bracket = new Bracket();

        for (int i = 0; i < p.length(); i++) {
            String text = p.substring(i, i + 1);
            bracket.setText(text);
            bracket.changeCount();
            if (bracket.isValid()) {
                bracket.addText();
            } else {
                bracket.holdText();
            }
        }

        return bracket.getAnswer();
    }

    private static class Bracket {
        private int count;
        private String text;
        private String answer = "";
        private LinkedList<String> temp = new LinkedList<>();

        public Bracket() {
        }

        public void setText(String text) {
            this.text = text;
        }

        public void changeCount() {
            if (text.equals("(")) {
                this.count++;
            } else {
                this.count--;
            }
        }

        public boolean isValid() {
            return count >= 0 ? true : false;
        }

        public void addText() {
            this.answer += text;
            if (!tempIsEmpty()) {
                addTemp();
            }
        }

        private boolean tempIsEmpty() {
            return temp.isEmpty();
        }

        private void addTemp() {
            while (!temp.isEmpty()) {
                this.answer += this.temp.poll();
            }
        }

        public void holdText() {
            if (textIsLeft()) {
                this.temp.addFirst(text);
            } else {
                this.temp.add(text);
            }
        }

        private boolean textIsLeft() {
            return text.equals("(");
        }

        public String getAnswer() {
            return answer;
        }
    }
}
