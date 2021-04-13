package leetcode.editor.cn;
//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 字符串 回溯算法


import java.util.ArrayList;
import java.util.List;

class LetterCombinations {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private final String[] strs = new String[]{"", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        public List<String> letterCombinations(String digits) {
            if(digits.isEmpty()){
                return new ArrayList<>();
            } else if(digits.length() == 1){
                List<String> rs = new ArrayList<>();
                String str = strs[digits.charAt(0) - '0' - 1];
                for (char c : str.toCharArray()) {
                    rs.add(String.valueOf(c));
                }
                return rs;
            }

            List<String> rs = new ArrayList<>();
            List<String> stringList = letterCombinations(digits.substring(1));
            String str = strs[digits.charAt(0) - '0' - 1];
            for (int i = 0; i < str.length(); i++) {
                for (String s : stringList) {
                    rs.add(str.charAt(i)+s);
                }
            }
            return rs;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args){
        Solution solu = new LetterCombinations().new Solution();
        System.out.println(solu.letterCombinations(""));
    }
}

