package codingTest.t221224;

public class Ex01 {
    public static void main(String[] args) {
        System.out.println(new Ex01().solution("xyZA","ABCxy"));
        System.out.println( new Ex01().solution("AxA","AyA"));
        System.out.println( new Ex01().solution("Abcde","bcdXAbcd"));
    }

    public String solution(String s1, String s2) {
        String str1 = overlapping(s1, s2);
        String str2 = overlapping(s2, s1);

        return getOrderString(str1, str2);
    }

    private String getOrderString(String str1, String str2) {
        if (str1.length() < str2.length()) {
            return str1;
        } else if (str1.length() > str2.length()) {
            return str2;
        }
        return str1.compareTo(str2) > 0 ? str2 : str1;
    }

    public String overlapping(String str1, String str2) {
        int str1Length = str1.length();
        int idx = Math.min(str1Length, str2.length());
        while(idx >= 0) {
            if (str1.substring(str1Length - idx, str1Length).equals(str2.substring(0,idx))) {
                break;
            }
            idx--;
        }
        return str1 + str2.substring(idx);
    }
}
