package leetcode.editor.cn;//实现一个基本的计算器来计算一个简单的字符串表达式的值。
//
// 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格 。 
//
// 示例 1: 
//
// 输入: "1 + 1"
//输出: 2
// 
//
// 示例 2: 
//
// 输入: " 2-1 + 2 "
//输出: 3 
//
// 示例 3: 
//
// 输入: "(1+(4+5+2)-3)+(6+8)"
//输出: 23 
//
// 说明： 
//
// 
// 你可以假设所给定的表达式都是有效的。 
// 请不要使用内置的库函数 eval。 
// 
// Related Topics 栈 数学
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args){
        Solution solu = new Solution();
        solu.calculate("22-22");
    }

    public int calculate(String s) {
        boolean isNum = false;
        Stack<String> stack = new Stack<>();
        List<String> out = new ArrayList<>();
        //先将中缀表达式转换成后缀表达式
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' '){
                isNum = false;
            } else if ('(' == s.charAt(i)) {
                isNum = false;
                stack.push("(");
            } else if (')' == s.charAt(i)) {
                isNum = false;
                String t;
                while (!(t = stack.pop()).equals("(")){
                    out.add(t);
                }
            } else if ('+' == s.charAt(i)) {
                isNum = false;
                String t;
                while(!stack.isEmpty() && ((t=stack.peek()).equals("-") || t.equals("+"))){
                    out.add(stack.pop());
                }
                stack.push("+");
            } else if ('-' == s.charAt(i)) {
                isNum = false;
                String t;
                while(!stack.isEmpty() && ((t=stack.peek()).equals("-") || t.equals("+"))){
                    out.add(stack.pop());
                }
                stack.push("-");
            } else if (String.valueOf(s.charAt(i)).matches("[0-9]")) {
                if (isNum){
                    String t = out.remove(out.size() - 1);
                    out.add(t+s.charAt(i));
                } else {
                    out.add(String.valueOf(s.charAt(i)));
                }
                isNum = true;
            }
        }
        while (!stack.isEmpty()){
            out.add(stack.pop());
        }

        //计算后缀表达式的值
        Stack<Integer> stackNum = new Stack<>();
        for (String s1 : out) {
            if (s1.matches("[0-9]+")) {
                stackNum.push(Integer.parseInt(s1));
            } else if ("+".equals(s1)){
                Integer right = stackNum.pop();
                Integer left = stackNum.pop();
                stackNum.push(left+right);
            } else if ("-".equals(s1)){
                Integer right = stackNum.pop();
                Integer left = stackNum.pop();
                stackNum.push(left-right);
            }
        }
        return stackNum.pop();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
