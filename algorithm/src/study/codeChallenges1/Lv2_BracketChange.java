package study.codeChallenges1;

/**
 *  프로그래머스 > 2020 KAKAO BLIND RECRUITMENT > 괄호 변환
 *  https://programmers.co.kr/learn/courses/30/lessons/60058
 *
 * Created by Kim Young Long.
 * Date : 2021-09-13.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv2_BracketChange {
    public static void main(String[] args) {
        String p = "()))((()";
        System.out.println("new Lv2_BracketChange().solution() = " + new Lv2_BracketChange().solution(p));
    }

    public String solution(String p) {
        if(p.length() < 1) return "";
        if(!isRightString(p)) {
            p = execute(p);
        }
        return p;
    }

    private String execute(String str) {
        if(str.length()==0) return "";
        String[] divideStr = divide(str);
        if(isRightString(divideStr[0])) {
            divideStr[1] = execute(divideStr[1]);
        } else {
            return "(" + execute(divideStr[1]) + ")" + changeStr(divideStr[0]);

        }
        return divideStr[0] + divideStr[1];
    }

    private String changeStr(String u) {
        String temp = u.substring(1, u.length() - 1);
        return temp.replaceAll("\\(", "-")
        .replaceAll("\\)","(")
        .replaceAll("-",")");
    }

    private String[] divide(String str) {
        if(isRightString(str)){
            return new String[]{str,""};
        }
        String u = null, v=null;
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                count++;
            }else{
                count--;
            }
            if (count == 0) {
                u = str.substring(0, i+1);
                v = str.substring(i+1, str.length());
                break;
            }

        }
        return new String[]{u,v};
    }

    private boolean isRightString(String str) {
        char[] chars = str.toCharArray();
        int count = 0;
        for (char aChar : chars) {
            if (aChar == '(') {
                count++;
            } else {
                count--;
                if (count < 0) return false;
            }
        }
        return true;
    }
}
