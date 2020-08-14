package leetcode.editor.cn;
//给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
//
// 
//示例:
//输入: S = "a1b2"
//输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
//
//输入: S = "3z4"
//输出: ["3z4", "3Z4"]
//
//输入: S = "12345"
//输出: ["12345"]
// 
//
// 注意： 
//
// 
// S 的长度不超过12。 
// S 仅由数字和字母组成。 
// 
// Related Topics 位运算 回溯算法 
// 👍 195 👎 0
import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args){
        Solution solu = new Solution();
        System.out.println(solu.letterCasePermutation("a1b2"));
    }
    public List<String> letterCasePermutation(String S) {
        List<String> list = new ArrayList<>();
        f(S.toCharArray(), 0, list);
        return list;
    }
    public void f(char[] chs, int pos, List<String> list){
        if (pos == chs.length){
            list.add(String.valueOf(chs));
            return;
        }
        if ((chs[pos] & 0x40) != 0){
            for (int i = 0; i < 2; i++) {
                if ((chs[pos] & 0x20) == 0) {
                    chs[pos] |= 0x20;
                } else {
                    chs[pos] &= 0xdf;
                }
                f(chs, pos+1, list);
            }
        } else {
            f(chs, pos+1, list);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
