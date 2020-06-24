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

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args){
        Solution solu = new Solution();
        solu.convert("LEETCODEISHIRING", 4);
    }
    public String convert(String s, int numRows) {
        int group = s.length() / (numRows + numRows - 2);
        int colCnt = s.length() % (numRows + numRows - 2)==0 ? group*(numRows-1) : group*(numRows-1)+1;
        char[][] chars = new char[numRows][colCnt];
        int row = 0;
        int col = 0;
        boolean flag = true;
        for (int i = 0; i < s.length(); i++) {
            chars[row][col] = s.charAt(i);
            if((i+1)%numRows==0){
                flag = true;
            }
            if((i+1)%(2*numRows-1)==0){
                flag = false;
            }
            if(flag){
                row++;
            } else {
                row--;
                col++;
            }
        }
        System.out.println(Arrays.toString(chars));
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
