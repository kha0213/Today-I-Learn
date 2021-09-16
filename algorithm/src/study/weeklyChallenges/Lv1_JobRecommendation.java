package study.weeklyChallenges;

import java.util.*;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-14.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv1_JobRecommendation {
    public static void main(String[] args) {
        String[] table = {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
        String[] languages = {"JAVA", "JAVASCRIPT"};
        int[] preference = {7,5};
        System.out.println(new Lv1_JobRecommendation().solution(table, languages, preference));
    }

    public String solution(String[] table, String[] languages, int[] preference) {
        Map<String, Map<String, Integer>> jobInfo = new HashMap<>();
        for (String s : table) {
            String[] info = s.split(" ");
            Map<String, Integer> languageScore = new HashMap<>();
            for (int i = 1; i <= 5; i++) {
                languageScore.put(info[i],6-i);
            }
            jobInfo.put(info[0], languageScore);
        }
        return jobInfo.entrySet().stream().max((o1, o2) -> {
            int i1 = preferenceCalculate(o1.getValue(),languages,preference);
            int i2 = preferenceCalculate(o2.getValue(),languages,preference);
            if(i1 != i2) return Integer.compare(i1,i2);
            return o2.getKey().compareTo(o1.getKey());
        }).get().getKey();
    }

    private int preferenceCalculate(Map<String, Integer> value, String[] languages, int[] preference) {
        int result = 0;
        for (int i = 0; i < languages.length; i++) {
            result += value.getOrDefault(languages[i],0) * preference[i];
        }
        return result;
    }
}
