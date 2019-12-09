package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class bj_2164_Queue {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N장의 카드가 있다. 각각의 카드는 차례로 1부터 N까지의 번호가 붙어 있으며, 1번 카드가 제일 위에, N번 카드가 제일 아래인 상태로 순서대로 카드가 놓여 있다.
        int n = Integer.parseInt(br.readLine());
        Card card = new Card(n);

        while (card.remain()) {
            card.drop();
            card.swap();
        }

        System.out.println(card.getCard());
    }

    private static class Card {
        private int n;
        private LinkedList<Integer> cardList = new LinkedList<>();

        public Card(int n) {
            this.n = n;

            init();
        }

        private void init() {
            for (int i = 1; i <= this.n; i++) {
                this.cardList.add(i);
            }
        }

        public void drop() {
            this.cardList.poll();
            this.n--;
        }

        public void swap() {
            int temp = this.cardList.poll();
            this.cardList.addLast(temp);
        }

        public int getCard() {
            return cardList.poll();
        }

        public boolean remain() {
            return n != 1;
        }
    }
}
