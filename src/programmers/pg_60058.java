package programmers;

public class pg_60058 {
    public static void main(String[] args) {
        String p = //"()))((()";
                //")(";
                //"(()())()";
                ")()()()(";
        System.out.println(solution(p));
    }

    private static String solution(String p) {
        Bracket bracket = new Bracket(p);
        int start_idx = 0;
        bracket.correct = true;
        for (int i = 0; i < bracket.preText.length(); i++) {
            String splitText = bracket.preText.substring(i, i + 1);
            bracket.changeCount(splitText);
            if (bracket.count < 0) bracket.correct = false;
            if (bracket.isSameCount()) {
                bracket.setText(start_idx, i + 1);
                if (bracket.correct) {
                    bracket.addPostText();
                } else {
                    bracket.setText(start_idx + 1, i);
                    bracket.reverseText();
                    bracket.addPostText("(");
                    bracket.addPostText();
                    bracket.addPostText(")");
                }
                start_idx = i + 1;
                bracket.correct = true;
            }
        }
        return bracket.getPostText();
    }

    private static class Bracket {
        private String text;
        private String preText;
        private String postText = "";
        private int count;
        private boolean correct;

        public Bracket(String preText) {
            this.preText = preText;
        }

        public void setText(int start_idx, int end_idx) {
            this.text = preText.substring(start_idx, end_idx);
        }

        public void changeCount(String splitText) {
            if (isLeftBlock(splitText)) this.count++;
            else this.count--;
        }

        private boolean isLeftBlock(String bracket) {
            return bracket.equals("(");
        }

        public boolean isSameCount() {
            return count == 0 ? true : false;
        }

        public void addPostText() {
            this.postText += text;
        }

        public void reverseText() {
            String temp = text;
            this.text = "";
            for (int i = 0; i < temp.length(); i++) {
                if (isLeftBlock(temp.substring(i, i + 1))) this.text += ")";
                else this.text += "(";
            }
        }

        public void addPostText(String text) {
            this.postText += text;
        }

        public String getPostText() {
            return postText;
        }
    }
}
