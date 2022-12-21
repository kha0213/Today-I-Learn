package study.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/140108
 */
public class Lv1_140108 {
    public int solution(String s) {
        int answer = 0;
        char basic = s.charAt(0);
        int be = 1, af = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == basic) {
                be++;
            } else {
                af++;
            }
            if (be == af) {
                answer++;
            }
        }
        return answer;
    }
}
