package leetcode.editor.cn;
//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
// 上图为 8 皇后问题的一种解法。
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
// 示例:
// 输入: 4
//输出: [
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 提示：
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步
//，可进可退。（引用自 百度百科 - 皇后 ） 
// 
// Related Topics 回溯算法 
// 👍 482 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SolveNQueens {
    public static void main(String[] args) {
        Solution solu = new SolveNQueens().new Solution();
        System.out.println(solu.solveNQueens(4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<String>> solveNQueens(int n) {
            List<List<String>> rs = new ArrayList<>();
            char[][] chars = new char[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(chars[i], '.');
            }
            f(rs, n, chars, 0);
            return rs;
        }

        public void f(List<List<String>> rs, int n, char[][] flag, int pos) {
            if (pos == n) {
                List<String> t = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    t.add(new String(flag[i]));
                }
                rs.add(t);
                return;
            }
            for (int j = 0; j < n; j++) {
                if (verify(flag, pos, j)) {
                    flag[pos][j] = 'Q';
                    f(rs, n, flag, pos + 1);
                    flag[pos][j] = '.';
                }
            }
        }

        public boolean verify(char[][] flag, int x, int y) {
            for (int i = 0; i < flag.length; i++) {
                if (flag[x][i] == 'Q') {
                    return false;
                }
                if (flag[i][y] == 'Q') {
                    return false;
                }
            }
            int i = x, j = y;
            while (i >= 0 && j >= 0) {
                if (flag[i--][j--] == 'Q') {
                    return false;
                }
            }
            i = x;
            j = y;
            while (i < flag.length && j < flag.length) {
                if (flag[i++][j++] == 'Q') {
                    return false;
                }
            }
            i = x;
            j = y;
            while (i >= 0 && j < flag.length) {
                if (flag[i--][j++] == 'Q') {
                    return false;
                }
            }
            i = x;
            j = y;
            while (i < flag.length && j > 0) {
                if (flag[i++][j--] == 'Q') {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}

