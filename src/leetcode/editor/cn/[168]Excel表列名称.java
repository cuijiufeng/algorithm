//给定一个正整数，返回它在 Excel 表中相对应的列名称。 
//
// 例如， 
//
//     1 -> A
//    2 -> B
//    3 -> C
//    ...
//    26 -> Z
//    27 -> AA
//    28 -> AB 
//    ...
// 
//
// 示例 1: 
//
// 输入: 1
//输出: "A"
// 
//
// 示例 2: 
//
// 输入: 28
//输出: "AB"
// 
//
// 示例 3: 
//
// 输入: 701
//输出: "ZY"
// 
// Related Topics 数学
package leetcode.editor.cn;

class ConvertToTitle {
    public static void main(String[] args) {
        Solution solu = new ConvertToTitle().new Solution();
        System.out.println(solu.convertToTitle(65535));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public String convertToTitle(int n) {
            if (n == 0) {
                return "A";
            }
            StringBuilder sb = new StringBuilder();
            char[] chs = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            while (n != 0) {
                sb.append(chs[(n - 1) % 26]);
                n = (n - 1) / 26;
            }
            char[] chars = sb.toString().toCharArray();
            int b = 0, e = chars.length - 1;
            while (b < e) {
                char t = chars[b];
                chars[b++] = chars[e];
                chars[e--] = t;
            }
            return new String(chars);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}