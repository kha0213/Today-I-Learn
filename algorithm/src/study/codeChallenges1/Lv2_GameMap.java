package study.codeChallenges1;

public class Lv2_GameMap {
    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        System.out.println("maps = " + solution(maps));
    }

    public static int solution(int[][] maps) {
        int x=0,y=0;
        int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
        boolean[][] visited = new boolean[maps.length][maps[0].length];

        dfs(maps,x,y,Integer.MAX_VALUE ,0, visited);

        return answer;
    }

    private static void dfs(int[][] maps, int x, int y,int min, int cnt, boolean[][] visited) {
        if(x < 0 || x >= maps.length || y < 0 || y >= maps[0].length || maps[x][y] == 0) return;
        if(x == maps.length-1 && y == maps[0].length-1) return Math.min(min,cnt);

    }

}
