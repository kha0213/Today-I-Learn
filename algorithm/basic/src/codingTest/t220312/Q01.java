package codingTest.t220312;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Q01 {
    public static void main(String[] args){
      Q01 ex = new Q01();
        String[] products = {"sofa red long", "blanket blue long", "towel red", "mattress long", "curtain blue long cheap"};
        String[] products2 = {"towel red long thin", "blanket red thick short", "curtain red long wide", "mattress thick", "hat red thin", "pillow red long", "muffler blue thick long"};
        String[] purchased = {"towel", "mattress", "curtain"};
        String[] purchased2 = {"blanket", "curtain", "hat", "muffler"};
      System.out.println("blanket: "+ ex.solution(products,purchased));
      System.out.println("towel: "+ ex.solution(products2,purchased2));
    }

    public String solution(String[] products, String[] purchased) {
        Set<String> set = Arrays.stream(purchased).collect(toSet());
        Map<String, Integer> map = new HashMap<>();
        List<Product> list = new ArrayList<>();

        for (String product : products) {
            String[] s = product.split(" ");
            if (set.contains(s[0])) {
                for (int i = 1; i < s.length; i++) {
                    map.put(s[i], map.getOrDefault(s[i], 0) + 1);
                }
            } else {
                list.add(new Product(s));
            }
        }

        List<String> order = map.entrySet().stream().sorted((i, j) -> {
            if (i.getValue() != j.getValue()) {
                return j.getValue() - i.getValue();
            }
            return i.getKey().compareTo(j.getKey());
        }).map(Map.Entry::getKey).collect(toList());

        for (String key : order) {
            List<Product> result = new ArrayList<>();
            for (Product product : list) {
                if (product.characteristic.contains(key)) {
                    result.add(product);
                }
            }
            if (result.size() == 1) return result.get(0).name;
            if (result.size() > 1) list = result;

        }

        return null;
    }
    static class Product {
        String name;
        List<String> characteristic = new ArrayList<>();

        public Product(String[] arr) {
            this.name = arr[0];
            characteristic.addAll(Arrays.asList(arr).subList(1, arr.length));
        }
    }
}
