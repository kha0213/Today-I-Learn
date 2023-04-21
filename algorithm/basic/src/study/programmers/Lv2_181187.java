package study.programmers;

public class Lv2_181187 {
    public static void main(String[] args) {
        Lv2_181187 lv2_181187 = new Lv2_181187();
        System.out.println(lv2_181187.solution(2,3));
    }


    public long solution(int r1, int r2) {
        int r1z = r1 * r1;
        int r2z = r2 * r2;

        int answer = r2 - r1 + 1;

        for (int i = 1; i < r2; i++) {

        }
        return answer * 4;
    }
}
