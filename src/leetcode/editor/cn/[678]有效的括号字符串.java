package leetcode.editor.cn;//给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
//
// 
// 任何左括号 ( 必须有相应的右括号 )。 
// 任何右括号 ) 必须有相应的左括号 ( 。 
// 左括号 ( 必须在对应的右括号之前 )。 
// * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。 
// 一个空字符串也被视为有效字符串。 
// 
//
// 示例 1: 
//
// 
//输入: "()"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "(*)"
//输出: True
// 
//
// 示例 3: 
//
// 
//输入: "(*))"
//输出: True
// 
//
// 注意: 
//
// 
// 字符串大小将在 [1，100] 范围内。 
// 
// Related Topics 字符串 
// 👍 144 👎 0


class CheckValidString{
    public static void main(String[] args){
        Solution solu = new CheckValidString().new Solution();
        System.out.println(solu.checkValidString("(*))"));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean checkValidString(String s) {
            return f(s.toCharArray(), 0, 0);
        }

        public boolean f(char[] arr, int pos, int balance){
            if (balance<0){
                return false;
            }
            if (pos == arr.length){
                return balance == 0;
            }
            if (arr[pos] == '('){
                return f(arr, pos+1, balance+1);
            } else if (arr[pos] == ')'){
                return f(arr, pos+1, balance-1);
            } else if (arr[pos] == '*') {
                return f(arr, pos+1, balance+1) || f(arr, pos+1, balance-1) || f(arr, pos+1, balance);
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}