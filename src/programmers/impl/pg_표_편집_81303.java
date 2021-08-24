package programmers.impl;

import java.util.*;

public class pg_표_편집_81303 {
    private List<Pos> posList = new ArrayList<>();
    private Stack<Integer> removeStack = new Stack<>();
    private int curPos;

    public static void main(String[] args) {
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};

        final pg_표_편집_81303 task = new pg_표_편집_81303();
        System.out.println(task.solution(n, k, cmd));
    }

    public String solution(int size, int pos, String[] cmd) {
        initPosList(size);
        this.curPos = pos;
        for (String command : cmd) {
            if (command.length() > 1) {
                String[] commandAndNumber = command.split(" ");
                executeCommand(commandAndNumber[0], commandAndNumber[1]);
            } else {
                executeCommand(command);
            }
        }

        StringBuilder answerBuilder = new StringBuilder();
        for (Pos answerPos : posList) {
            if (answerPos.isExist) {
                answerBuilder.append("O");
                continue;
            }
            answerBuilder.append("X");
        }
        return answerBuilder.toString();
    }

    private void initPosList(int size) {
        for (int idx = 0; idx < size; idx++) {
            posList.add(new Pos(idx, true));
        }
    }

    private void executeCommand(String command, String number) {
        if (command.equals("D")) {
            executeDown(Integer.parseInt(number));
        } else {
            executeUp(Integer.parseInt(number));
        }
    }

    private void executeDown(int number) {
        int originPos = curPos + 1;
        for (int idx = originPos; idx < posList.size(); idx++) {
            curPos++;
            if (!posList.get(idx).isExist) continue;
            if (--number == 0) break;
        }
    }

    private void executeUp(int number) {
        int originPos = curPos - 1;
        for (int idx = originPos; idx >= 0; idx--) {
            curPos--;
            if (!posList.get(idx).isExist) continue;
            if (--number == 0) break;
        }
    }

    private void executeCommand(String command) {
        if (command.equals("C")) {
            executeRemove();
        } else {
            executeRestore();
        }

    }

    private void executeRemove() {
        final Pos pos = posList.get(curPos);
        removeStack.push(curPos);
        pos.remove();
        if (!isLast()) {
            changeLastPos();
        }else{
            changePos();
        }
    }

    private boolean isLast() {
        for (int idx = curPos; idx < posList.size(); idx++) {
            if (posList.get(idx).isExist) return false;
        }
        return true;
    }

    private void changeLastPos() {
        for (int idx = curPos - 1; idx >= 0; idx--) {
            if (posList.get(idx).isExist) {
                curPos = idx;
                break;
            }
        }
    }

    private void changePos() {
        for (int idx = curPos + 1; idx < posList.size(); idx++) {
            if (posList.get(idx).isExist) {
                curPos = idx;
                break;
            }
        }
    }

    private void executeRestore() {
        final Pos pos = posList.get(removeStack.pop());
        pos.restore();
    }

    private static class Pos {
        private int idx;
        private boolean isExist;

        public Pos(int idx, boolean isExist) {
            this.idx = idx;
            this.isExist = isExist;
        }

        public void remove() {
            this.isExist = false;
        }

        public void restore() {
            this.isExist = true;
        }
    }
}
