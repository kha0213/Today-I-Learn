package study.codeChallenges1;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-17.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv2_Test2 {
    public static void main(String[] args){
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        System.out.println(Arrays.toString(new Lv2_Test2().solution(info,query)));
    }

    public int[] solution(String[] info, String[] query) {
        Map<Gubun, List<Volunteer>> map = new HashMap<>();
        List<Volunteer> volunteers = Arrays.stream(info)
                .map(Volunteer::new)
                .collect(Collectors.toList());
        for (Volunteer volunteer : volunteers) {
            Gubun gubun = new Gubun(volunteer);
            List<Volunteer> list = map.getOrDefault(gubun, new ArrayList<>());
            list.add(volunteer);
            map.put(gubun, list);
        }
        return Arrays.stream(query)
                .mapToInt(search -> {
                    String[] split = search.split(" and ");
                    char language = split[0].charAt(0);
                    char group = split[1].charAt(0);
                    char career = split[2].charAt(0);
                    String[] s = split[3].split(" ");
                    char food = s[0].charAt(0);
                    int score = Integer.parseInt(s[1]);
                    return (int) volunteers.stream()
                            .filter(v -> language == '-' || v.language == language)
                            .filter(v -> group == '-' || v.group == group)
                            .filter(v -> career == '-' || v.career == career)
                            .filter(v -> food == '-' || v.food == food)
                            .filter(v -> v.score >= score)
                            .count();
                })
                .toArray();
    }
    static class Gubun {
        char language;
        char group;
        char career;
        char food;
        public Gubun(){};
        public Gubun(Volunteer v) {
            language = v.language;
            group = v.group;
            career = v.career;
            food = v.food;
        }
    }
    static class Volunteer {
        char language;
        char group;
        char career;
        char food;
        int score;
        public Volunteer(){}
        public Volunteer(String info) {
            String[] infos = info.split(" ");
            this.language = infos[0].charAt(0);
            this.group = infos[1].charAt(0);
            this.career = infos[2].charAt(0);
            this.food = infos[3].charAt(0);
            this.score = Integer.parseInt(infos[4]);
        }
    }
    
}
