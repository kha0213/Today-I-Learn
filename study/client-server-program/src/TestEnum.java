import java.util.Arrays;
import java.util.List;

/**
 * 2021-03-09
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
public class TestEnum {
    public static void main(String[] args) {
        System.out.println(Fruit.BANANA.ordinal());
    }
    public enum Fruit {
        APPLE("apple",80, Arrays.asList(3,4,5)),
        ORANGE("orange",50, Arrays.asList(9, 10, 11)),
        PEACH("peach",200, Arrays.asList(7,8)),
        BANANA("banana",100, Arrays.asList(6, 7, 8, 9));

        private final String name;
        private final int calorie;
        private final List<Integer> season;

        Fruit(String name, int calorie, List<Integer> season) {
            this.name = name;
            this.calorie = calorie;
            this.season = season;
        }

        public String getName() {
            return name;
        }
        public int getCalorie() {
            return calorie;
        }
        public List<Integer> getSeason() {
            return season;
        }
    }

}
