package study.leetcode;

public class AddBinary {
    public static void main(String[] args) {
        AddBinary addBinary = new AddBinary();
        // 100
        System.out.println(addBinary.addBinary("11", "1"));
        // 10101
        System.out.println(addBinary.addBinary("1010", "1011"));
    }

    public String addBinary(String a, String b) {
        int i = Integer.parseInt(a, 2);
        return Integer.toBinaryString(i + Integer.parseInt(b, 2));
    }
}
