package codingTest.t220305;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Q01 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int end = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();
        int count = 0;

        while (count < end) {
            String word = br.readLine();
            if(word.equals("d") || word.equals("D")) {
                if (queue.isEmpty()) {
                    System.out.println("underflow");
                } else {
                    queue.poll();
                }
            } else { //e E
                int num = Integer.parseInt(br.readLine());
                if (queue.size() == 10) {
                    System.out.println("overflow");
                } else {
                    queue.offer(num);
                }
            }
            count++;
        }
        String result = queue.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println("result = " + result);
    }
}
