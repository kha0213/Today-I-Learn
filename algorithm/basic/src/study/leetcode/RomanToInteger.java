package study.leetcode;

public class RomanToInteger {
    public static void main(String[] args) {
        RomanToInteger romanToInteger = new RomanToInteger();
        //58
        System.out.println(romanToInteger.romanToInt("MCMXCIV"));
    }

    public int romanToInt(String s) {
        int before = s.length();
        s= s.replaceAll("CM", "");
        int result = (before - s.length()) * 450;

        before = s.length();
        s= s.replaceAll("CD", "");
        result += (before - s.length()) * 200;

        before = s.length();
        s= s.replaceAll("XC", "");
        result += (before - s.length()) * 45;

        before = s.length();
        s= s.replaceAll("XL", "");
        result += (before - s.length()) * 20;

        before = s.length();
        s= s.replaceAll("IX", "");
        result += (before - s.length()) / 2 * 9;

        before = s.length();
        s= s.replaceAll("IV", "");
        result += (before - s.length()) * 2;

        for (char c : s.toCharArray()) {
            switch (c) {
                case 'I' : result += 1; break;
                case 'V' : result += 5; break;
                case 'X' : result += 10; break;
                case 'L' : result += 50; break;
                case 'C' : result += 100; break;
                case 'D' : result += 500; break;
                case 'M' : result += 1000; break;
            }
        }

        return result;
    }
}
