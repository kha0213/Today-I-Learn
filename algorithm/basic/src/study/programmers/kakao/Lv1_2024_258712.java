package study.programmers.kakao;

import java.util.*;
import java.util.stream.Collectors;

//https://school.programmers.co.kr/learn/courses/30/lessons/258712
public class Lv1_2024_258712 {
    public static void main(String[] args) {
//        String[] friends = {"muzi", "ryan", "frodo", "neo"};
//        String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
//        String[] friends = {"joy", "brad", "alessandro", "conan", "david"};
//        String[] gifts = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};
        String[] friends = {"a", "b", "c"};
        String[] gifts = {"a b", "b a", "c a", "a c", "a c", "c a"};
        System.out.println(new Lv1_2024_258712().solution(friends, gifts));
    }

    public int solution(String[] friends, String[] gifts) {
        Map<String, Friend> friendMap = Arrays.stream(friends).collect(Collectors.toMap(i -> i, Friend::new));

        Map<Send, Integer> sendMap = new HashMap<>();
        for (String gift : gifts) {
            Send send = new Send(gift);
            sendMap.put(send, sendMap.getOrDefault(send, 0) + 1);
            friendMap.get(send.from).grade++;
            friendMap.get(send.to).grade--;
        }

        for (int i = 0; i < friends.length; i++) {
            Friend friend = friendMap.get(friends[i]);
            for (int j = i + 1; j < friends.length; j++) {
                Friend other = friendMap.get(friends[j]);
                addCount(friend, other, sendMap);
            }
        }
        return friendMap.values().stream().mapToInt(it -> it.count).max().getAsInt();
    }

    private void addCount(Friend friend, Friend other, Map<Send, Integer> sendMap) {
        int left = sendMap.getOrDefault(new Send(friend.name + " " + other.name), 0);
        int right = sendMap.getOrDefault(new Send(other.name + " " + friend.name), 0);

        if (left == right) {
            if (friend.grade > other.grade) {
                friend.count++;
            } else if (friend.grade < other.grade) {
                other.count++;
            }
        } else if (left > right) {
            friend.count++;
        } else {
            other.count++;
        }
    }

    static class Send {
        String from;
        String to;

        public Send(String gift) {
            String[] s = gift.split(" ");
            this.from = s[0];
            this.to = s[1];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Send send = (Send) o;
            return Objects.equals(from, send.from) && Objects.equals(to, send.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    static class Friend {
        String name;
        int grade = 0;
        int count = 0;

        public Friend(String name) {
            this.name = name;
        }
    }
}
