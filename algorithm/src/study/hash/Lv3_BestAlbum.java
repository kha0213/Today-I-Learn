package study.hash;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 *  [프로그래머스 > 해시 > 베스트앨범]
 *  https://programmers.co.kr/learn/courses/30/lessons/42579
 *
 * Created by Kim Young Long.
 * Date : 2021-04-26.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class Lv3_BestAlbum {
    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};


        System.out.println("solution(genres) = " + Arrays.toString(solution(genres,plays)));
    }

    public static int[] solution(String[] genres, int[] plays) {
        Map<String, List<PlayLoc>> map = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            List<PlayLoc> list = map.getOrDefault(genres[i], new ArrayList<>());
            list.add(new PlayLoc(plays[i], i));
            map.put(genres[i], list);
        }
        Set<Map.Entry<String, List<PlayLoc>>> entries = map.entrySet();
        List<Map.Entry<String, List<PlayLoc>>> collect = entries.stream()
                .sorted((i, j) ->
                        j.getValue().stream().map(playLoc -> playLoc.plays).reduce(0, Integer::sum)
                        - i.getValue().stream().map(playLoc -> playLoc.plays).reduce(0, Integer::sum))
                .collect(Collectors.toList());

        List<Integer> result = new ArrayList<>();
        for (Map.Entry<String, List<PlayLoc>> entry : collect) {
            List<PlayLoc> value = entry.getValue().stream().sorted((i,j) -> j.plays - i.plays).collect(Collectors.toList());
            for (int i = 0; i < value.size() && i < 2; i++) {
                result.add(value.get(i).location);
            }
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
    static class PlayLoc {
        int plays;
        int location;
        public PlayLoc(int plays, int location) {
            this.plays = plays;
            this.location = location;
        }
    }
}
