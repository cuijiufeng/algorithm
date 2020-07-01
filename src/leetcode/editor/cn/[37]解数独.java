package leetcode.editor.cn;
//编写一个程序，通过已填充的空格来解决数独问题。
//
// 一个数独的解法需遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 
// 
//
// 空白格用 '.' 表示。 
//
// 
//
// 一个数独。 
//
// 
//
// 答案被标成红色。 
//
// Note: 
//
// 
// 给定的数独序列只包含数字 1-9 和字符 '.' 。 
// 你可以假设给定的数独只有唯一解。 
// 给定数独永远是 9x9 形式的。 
// 
// Related Topics 哈希表 回溯算法


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args){
        char[][] board = new char[][]{
                {'.','.','9','7','4','8','.','.','.'},
                {'7','.','.','.','.','.','.','.','.'},
                {'.','2','.','1','.','9','.','.','.'},
                {'.','.','7','.','.','.','2','4','.'},
                {'.','6','4','.','1','.','5','9','.'},
                {'.','9','8','.','.','.','3','.','.'},
                {'.','.','.','8','.','3','.','2','.'},
                {'.','.','.','.','.','.','.','.','6'},
                {'.','.','.','2','7','5','9','.','.'}
        };
        Solution solution = new Solution();
        solution.solveSudoku(board);
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }

    public void solveSudoku(char[][] board) {
        char[][] rs = new char[9][9];
        solveSudokuF(board, rs, 81-1);
        //把问题的答案拷贝到原先的数组里
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(rs[i], 0, board[i], 0, board[i].length);
        }
    }

    /**
     * @Desc: 寻找题解
     * @Date: 2020/7/1 15:16
     * @param board 问题
     * @param rs 保存答案
     * @param pos
     * @return void
     */
    public void solveSudokuF(char[][] board, char[][] rs, int pos){
        //在找到答案的时候先保存下来，不然会被覆盖
        if(pos == -1){
            for (int i = 0; i < board.length; i++) {
                System.arraycopy(board[i], 0, rs[i], 0, board[i].length);
            }
            return;
        }
        int i = pos/9, j = pos%9;
        //如果此格子是没有值的
        if(board[i][j] == '.'){
            for (int k = 1; k <= 9; k++) {
                //试探此格子可以放什么值
                if(fillEnable(board, i, j, k)){
                    //试探
                    board[i][j] = (char)(k+'0');
                    //下一格子
                    solveSudokuF(board, rs,pos-1);
                    //回溯
                    board[i][j] = '.';
                }
            }
        } else {
            //如果此格子已经有值，则下一格子
            solveSudokuF(board, rs,pos-1);
        }
    }

    /**
     * @Desc: 判断在board[x][y]位置放k值是否合法
     * @Date: 2020/7/1 15:15
     * @param board
     * @param x
     * @param y
     * @param k
     * @return boolean
     */
    private boolean fillEnable(char[][] board, int x, int y, int k) {
        for(int i=0; i<9; i++){
            if(i != y && board[x][i] == (char)(k+'0')){
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if(i != x && board[i][y] == (char)(k+'0')){
                return false;
            }
        }
        for (int i=x/3*3; i<x/3*3+3; i++){
            for (int j = y/3*3; j<y/3*3+3 ; j++) {
                if(i != x && j != y && board[i][j] == (char)(k+'0')){
                    return false;
                }
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
