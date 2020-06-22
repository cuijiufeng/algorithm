package leetcode.editor.cn;
//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
//
// 说明：本题中，我们将空字符串定义为有效的回文串。 
//
// 示例 1: 
//
// 输入: "A man, a plan, a canal: Panama"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "race a car"
//输出: false
// 
// Related Topics 双指针 字符串


import java.util.regex.Matcher;
import java.util.regex.Pattern;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args){
        Solution solu = new Solution();
        System.out.println(solu.isPalindrome(".,"));
    }
    public boolean isPalindrome(String s) {
        if (s==null || s.isEmpty()){
            return true;
        }
        if(s.length()==1 || s.matches("[ ]*")){
            return true;
        }
        if(s.matches("[^a-zA-Z0-9]+")){
            return true;
        }
        int i=0, j = s.length()-1;
        while(i<j){
            char ch1 = s.charAt(i++);
            char ch2 = s.charAt(j--);
            while (!(ch1>='a' && ch1<='z' || ch1>='A' && ch1<='Z' || ch1>='0' && ch1<='9')){
                ch1 = s.charAt(i++);
            }
            while (!(ch2>='a' && ch2<='z' || ch2>='A' && ch2<='Z' || ch2>='0' && ch2<='9')){
                ch2 = s.charAt(j--);
            }
            ch1 = (char) (ch1 & 0xDF);
            ch2 = (char) (ch2 & 0xDF);
            if(ch1 != ch2){
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
