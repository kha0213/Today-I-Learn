package study.codeChallenges1;

/**
 *
 * 프로그래머스 > 2021 KAKAO BLIND RECRUITMENT > 신규 아이디 추천
 * https://programmers.co.kr/learn/courses/30/lessons/72410
 *
 * Created by Kim Young Long.
 * Date : 2021-04-23.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv1_newIdRecommend {
    public static void main(String[] args) {
        System.out.println("solution(\"...!@BaT#*..y.abcdefghijklm\") = " + solution("...!@BaT#*..y.abcdefghijklm"));
        System.out.println("solution(\"z-+.^.\") = " + solution("z-+.^."));
        System.out.println("solution(\"=.=\") = " + solution("=.="));
        System.out.println("solution(\"123_.def\") = " + solution("123_.def"));
        System.out.println("solution(\"abcdefghijklmn.p\") = " + solution("abcdefghijklmn.p"));
    }

    public static String solution(String new_id) {
        StringBuilder sb = new StringBuilder(new_id.toLowerCase().replaceAll("[^0-9a-z-_.]","").replaceAll("[.]{2,}","."));
        if(sb.charAt(0)=='.') sb.deleteCharAt(0);
        if(sb.length() > 0 && sb.charAt(sb.length() - 1) == '.' ) sb.deleteCharAt(sb.length() - 1 );
        if(sb.length() == 0) sb.append("a");
        if(sb.length() >= 16) sb = new StringBuilder(sb.substring(0,15));
        if(sb.length() > 0 && sb.charAt(sb.length() - 1) == '.' ) sb.deleteCharAt(sb.length() - 1 );

        if(sb.length() == 1) return sb.toString()+sb.toString()+sb.toString();
        else if(sb.length() == 2) return sb.append(sb.charAt(1)).toString();
        return sb.toString();
    }
}
