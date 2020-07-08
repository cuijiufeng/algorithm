package leetcode.editor.cn;
//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
//
// 示例 1: 
//
// 输入: num1 = "2", num2 = "3"
//输出: "6" 
//
// 示例 2: 
//
// 输入: num1 = "123", num2 = "456"
//输出: "56088" 
//
// 说明： 
//
// 
// num1 和 num2 的长度小于110。 
// num1 和 num2 只包含数字 0-9。 
// num1 和 num2 均不以零开头，除非是数字 0 本身。 
// 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。 
// 
// Related Topics 数学 字符串

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args){
        Solution solu = new Solution();
        System.out.println(solu.multiply("33", "0"));
    }
    
    public String multiply(String num1, String num2) {
        char[] str1 = reverseString(num1.toCharArray());
        char[] str2 = reverseString(num2.toCharArray());
        char[] sb = new char[str1.length+str2.length];
        Arrays.fill(sb, '0');
        for (int i = 0; i < str2.length; i++) {
            int ni = str2[i] - '0';
            for (int j = 0; j < str1.length; j++) {
                int nj = str1[j] - '0';
                int t = ni * nj + (sb[i + j] - '0');
                sb[i+j] = (char)(t%10+'0');
                sb[i+j+1] += t/10;
            }
        }
        int cnt = 0;
        for (int i = sb.length-1; i >=0 && sb[i] == '0'; i--) {
            cnt++;
        }
        if (cnt == sb.length) {
            return new String("0");
        }
        return new String(reverseString(sb), cnt, sb.length-cnt);
    }

    private char[] reverseString(char[] chars){
        char t;
        int b = 0, e = chars.length-1;
        while (b < e){
            t = chars[b];
            chars[b++] = chars[e];
            chars[e--] = t;
        }
        return chars;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
