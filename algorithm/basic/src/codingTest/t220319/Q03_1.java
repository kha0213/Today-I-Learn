package codingTest.t220319;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Stack;

public class Q03_1 {
    public static void main(String[] args){
      Q03_1 ex = new Q03_1();
      //2983
      String[] ledgers = {"01/01 4 50000","01/11 6 3555","02/01 0 -23555","02/25 5 5000","03/25 0 -15000","06/09 8 43951","12/30 9 99999"};
      System.out.println(ex.solution(ledgers));
    }

    public int solution(String[] ledgers) {
        Stack<Deposit> stack = new Stack<>();
        int answer = 0;
        for (String ledger : ledgers) {
            String[] s = ledger.split(" ");
            String[] split = s[0].split("/");
            LocalDate date = LocalDate.of(2022, Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            int interestRete = Integer.parseInt(s[1]);
            int amount = Integer.parseInt(s[2]);

            if(interestRete > 0) {
                stack.push(new Deposit(date, interestRete, amount));
            } else {
                amount *= -1;
                while(true) {
                    Deposit pop = stack.pop();
                    if (pop.amount > amount) {
                        int days = (int)ChronoUnit.DAYS.between(pop.date, date);
                        answer += (pop.amount * pop.interestRate) * days / 36500;
                        pop.amount -= amount;
                        stack.push(pop);
                        break;
                    } else if (pop.amount == amount) {
                        int days = (int)ChronoUnit.DAYS.between(pop.date, date);
                        answer += (pop.amount * pop.interestRate) * days / 36500;
                        break;
                    } else {
                        int days = (int)ChronoUnit.DAYS.between(pop.date, date);
                        answer += (pop.amount * pop.interestRate) * days / 36500;
                        amount -= pop.amount;
                    }
                }
            }
        }

        LocalDate date = LocalDate.of(2022, 12, 31);
        while(!stack.isEmpty()){
            Deposit pop = stack.pop();
            int days = (int)ChronoUnit.DAYS.between(pop.date, date);
            answer += (pop.amount * pop.interestRate) * days / 36500;
        }
        return answer;
    }

    static class Deposit {
        LocalDate date;
        int interestRate;
        int amount;

        public Deposit(LocalDate date, int interestRate, int amount) {
            this.date = date;
            this.interestRate = interestRate;
            this.amount = amount;
        }
    }
}
