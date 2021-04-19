package goormTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 2021-03-20
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
public class Test03 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] grade = br.readLine().replaceAll("\\.","").split(" ");
        int[] grades = new int[grade.length];
        for(int i=0;i<grade.length;i++) {
            grades[i] = Integer.parseInt(grade[i]);
        }

        String[] rowColumn = br.readLine().split(" ");
        int row = Integer.parseInt(rowColumn[0]);
        int column = Integer.parseInt(rowColumn[1]);


        List<WhatCha> user = new ArrayList<>();
        for (int i=0;i<row;i++) {
            String temp = br.readLine();
            for (int j=0;j<column;j++) {
                char tempOpen = temp.charAt(j);
                int open = tempOpen == 'Y'?2:tempOpen == 'O'?1:0;
                user.add(new WhatCha(open, i * 10 + j));
            }
        }
        for (int i=0;i<row;i++) {
            String temp = br.readLine();
            for (int j=0;j<column;j++) {
                char genre = temp.charAt(j);
                WhatCha whatCha = user.get(i * column + j);
                whatCha.setGenre(genre, grades[(int)(genre-65)]);
            }
        }

        user.stream()
            .filter(o->o.getOpen()>0)
            .sorted(new Comparator<WhatCha>() {
            @Override
            public int compare(WhatCha o1, WhatCha o2) {
                if(o1.getOpen() != o2.getOpen()) {
                    return o2.getOpen() - o1.getOpen();
                }
                if(o1.getPreference() != o2.getPreference()) {
                    return o2.getPreference() - o1.getPreference();
                }
                return o1.getLocation() - o2.getLocation();
            }
        }).forEachOrdered(System.out::println);

    }
    /*4.0 3.0 2.1 4.3 5.0
            2 3
            WYO
            YYO
            ABC
            DCE*/
    static class WhatCha {
        char genre; //장르
        int preference; //선호도
        int open; //열람정보
        int location; // 행렬 위치

        public WhatCha(int open, int location) {
            this.open = open;
            this.location = location;
        }
        public void setGenre(char genre, int preference) {
            this.genre = genre;
            this.preference = preference;
        }

        public char getGenre() {
            return genre;
        }

        public int getPreference() {
            return preference;
        }

        public int getLocation() {
            return location;
        }

        public int getOpen() {
            return open;
        }

        @Override
        public String toString() {
            return genre+" "+(double)preference/10+" "+location/10+" "+location%10;
        }
    }
}
