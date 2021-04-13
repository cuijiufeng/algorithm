package leetcode.editor.cn;
//将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
//
// 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下： 
//
// L   C   I   R
//E T O E S I I G
//E   D   H   N
// 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// string convert(string s, int numRows); 
//
// 示例 1: 
//
// 输入: s = "LEETCODEISHIRING", numRows = 3
//输出: "LCIRETOESIIGEDHN"
// 
//
// 示例 2: 
//
// 输入: s = "LEETCODEISHIRING", numRows = 4
//输出: "LDREOEIIECIHNTSG"
//解释:
//
//L     D     R
//E   O E   I I
//E C   I H   N
//T     S     G 
// Related Topics 字符串


import java.util.Arrays;

class ZConvert {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String convert(String s, int numRows) {
            if (s.length() == 1 || numRows == 1) {
                return s;
            }
            int group = s.length() % (numRows + numRows - 2) == 0 ? s.length() / (numRows + numRows - 2) : s.length() / (numRows + numRows - 2) + 1;
            int colCnt = group * (numRows - 1);
            char[][] chars = new char[numRows][colCnt];
            int row = 0;
            int col = 0;
            for (int i = 0; i < s.length(); i++) {
                chars[row][col] = s.charAt(i);
                if (i % (2 * numRows - 2) < numRows - 1) {
                    row++;
                } else {
                    row--;
                    col++;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (char[] aChar : chars) {
                for (char c : aChar) {
                    if (c != 0) {
                        sb.append(c);
                    }
                }
            }
            return sb.toString();
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        Solution solu = new ZConvert().new Solution();
        System.out.println(solu.convert("ABCDE", 4));
    }
}
