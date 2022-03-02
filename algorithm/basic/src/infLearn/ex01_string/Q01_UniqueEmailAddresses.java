package infLearn.ex01_string;


import java.util.HashSet;
import java.util.Set;

public class Q01_UniqueEmailAddresses {
    public static void main(String[] args){
      Q01_UniqueEmailAddresses ex = new Q01_UniqueEmailAddresses();
      String[] inputs = { "a@coding.com",
                          "b@coding.com",
                          "c@coding.com",
                          "test.email+james@coding.com",
                          "test.e.mail+toto.jane@coding.com",
                          "testemail+tom@cod.ing.com"};
      System.out.println(ex.solution(inputs));

    }

    public int solution(String[] input) {
        Set<String> result = new HashSet<>();
        for (String s : input) {
            result.add(rule(s));
        }
        return result.size();
    }

    private String rule(String input) {
        String[] split = input.split("@");
        String front = split[0];
        if (front.contains("+")) {
            front = front.substring(0, front.indexOf("+"));
        }
        front = front.replaceAll("\\.","");
        return front + "@" +split[1];
    }
}
