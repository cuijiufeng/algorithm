package leetcode.editor.cn;
//给你两个二进制字符串，返回它们的和（用二进制表示）。
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 数学 字符串

class AddBinary {
    public static void main(String[] args) {

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String addBinary(String a, String b) {
            StringBuilder sb = new StringBuilder();
            int pa = a.length() - 1, pb = b.length() - 1;
            int y = 0;
            while (pa >= 0 && pb >= 0) {
                int sum = Integer.parseInt(String.valueOf(a.charAt(pa))) + Integer.parseInt(String.valueOf(b.charAt(pb))) + y;
                y = sum / 2;
                sb.append(sum % 2);
                pa--;
                pb--;
            }
            while (pa >= 0) {
                int sum = Integer.parseInt(String.valueOf(a.charAt(pa))) + y;
                y = sum / 2;
                sb.append(sum % 2);
                pa--;
            }
            while (pb >= 0) {
                int sum = Integer.parseInt(String.valueOf(b.charAt(pb))) + y;
                y = sum / 2;
                sb.append(sum % 2);
                pb--;
            }
            if (y != 0) {
                sb.append(1);
            }
            return sb.reverse().toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}

