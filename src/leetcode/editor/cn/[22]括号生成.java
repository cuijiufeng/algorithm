package leetcode.editor.cn;
//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
// 示例：
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// Related Topics 字符串 回溯算法


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args){
        Solution solu = new Solution();
        List<String> list = solu.generateParenthesis(3);
        for (String s : list) {
            System.out.print("\""+s+"\",");
        }
    }
    
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        dfs(list, n, n, "");
        return list;
    }

    /**
     * @Desc: 深度优先遍历解决问题
     * @Date: 2020/6/30 15:14
     * @param list 存放结果集
     * @param left 左括号的个数
     * @param right 右括号的个数
     * @param str list中的一次，括号拼接的串
     * @return void
     */
    public void dfs(List<String> list, int left, int right, String str){
        //递归出口。如果左右括号都没有了，则这是一种方案，添加进list并且返回
        if (left == 0 && right == 0) {
            list.add(str);
            return;
        }

        //如果还有左括号，则拼接一次左括号
       if(left > 0){
           dfs(list, left-1, right, str+"(");
       }
       //这是必须是右括号比左括号多的时候才可以拼接左括号，以保证括号的匹配
       if(right > left){
           dfs(list, left, right - 1, str+")");
       }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
