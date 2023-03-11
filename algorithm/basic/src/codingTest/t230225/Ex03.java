package codingTest.t230225;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Ex03 {
    public static void main(String[] args) {
        System.out.println("Hello World!");
//        int[] balance = {30, 30, 30, 30};
//        int[][] transaction = {{1, 2, 10}, {2, 3, 20}, {3, 4, 5}, {3, 4, 30}};
//        int[] abnormal = {1};
        // 0, 20, 15, 55

        int[] balance = {100, 1, 1,1,1};
        int[][] transaction = {{1, 2, 100}, {2, 3, 101}, {3, 4, 102}, {4, 5, 103}
                ,{5,1,104}
        };
        int[] abnormal = {1};
        //System.out.println(Arrays.toString(new Ex03().solution(balance, transaction, abnormal)));
    }

    public int[] solution(int[] balance, int[][] transaction, int[] abnormal) {
        LinkedList<User> users = new LinkedList<>();
        for (int i = 0; i < balance.length; i++) {
            users.add(new User(i + 1, new Account(i + 1, balance[i])));
        }

        for (int[] tran : transaction) {
            User from = users.get(tran[0] - 1);
            User to = users.get(tran[1] - 1);
            int amount = tran[2];

            while (amount > 0) {
                Account pop = from.accounts.pop();

                if(pop.balance > amount) {
                    pop.balance -= amount;
                    from.accounts.push(pop);
                    to.accounts.push(new Account(pop.userId, amount));
                    break;
                } else {
                    amount -= pop.balance;
                    to.accounts.push(new Account(pop.userId, pop.balance));
                }
            }
        }

        Set<Integer> abnormalSet =
                Arrays.stream(abnormal).boxed().collect(Collectors.toSet());

        return users.stream()
                .mapToInt(user -> user.getBalance(abnormalSet))
                .toArray();
    }


    static class User {
        private final int userId;
        private final Stack<Account> accounts = new Stack<>();

        public User(int userId, Account account) {
            this.userId = userId;
            this.accounts.push(account);
        }

        public int getBalance(Set<Integer> abnormalSet) {
            return accounts.stream()
                    .filter(account -> !abnormalSet.contains(account.userId))
                    .mapToInt(account -> account.balance)
                    .sum();
        }
    }

    static class Account {
        private final int userId;
        private int balance;

        public Account(int userId, int balance) {
            this.userId = userId;
            this.balance = balance;
        }
    }
}
