package codingTest.t221224;

public class Ex03 {
    public static void main(String[] args) {
        int[][] board =
                {{1,-7,-2,1,-1},
                 {2,3,0,-1,-2},
                 {1,-1,6,-1,-2},
                 {-1,1,-2,0,4},
                 {-10,5,-3,-1,1}};
        System.out.println(new Ex03().solution(board));
        int[][] board2 =
                {{-10,20,30},
                {-10,0,10},
                {-20,40,1}};
        System.out.println(new Ex03().solution(board2));
    }
    int max = 0;
    public int solution(int[][] board) {
        dfs(board, 0,0, 0);
        return max;
    }

    private void dfs(int[][] board, int sum, int x, int y) {
        if (x == board.length || y == board[0].length) {
            return;
        }
        sum += board[x][y];
        if (x == board.length - 1 && y == board[0].length - 1) {
            max = Math.max(max, sum);
            return;
        }
        if (board[x][y] == 0) {
            dfs(board, sum * -1, x + 1, y);
            dfs(board, sum * -1, x, y + 1);
        }
        dfs(board, sum, x + 1, y);
        dfs(board, sum, x, y + 1);
    }
}
