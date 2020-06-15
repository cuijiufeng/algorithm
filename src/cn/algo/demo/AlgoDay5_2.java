package cn.algo.demo;

import java.util.Stack;

/**
 * @ClassName: AlgoDay5_2
 * @Description:
 * @Date: 2020/6/15 9:50
 * @auth: Administrator
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * 示例 1:
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 */
public class AlgoDay5_2 {
    public static void main(String[] args){
        AlgoDay5_2 algo = new AlgoDay5_2();
        String str = "}";
        System.out.println(algo.isValid(str));
    }
    public boolean isValid(String s) {
        if (s.isEmpty()){
            return true;
        }
        Stack<Character> stack = new Stack<Character>();
        for (int i=0; i<s.length(); i++){
            if(s.charAt(i)=='(' || s.charAt(i)=='{' || s.charAt(i)=='['){
                stack.push(s.charAt(i));
            } else {
                if(stack.empty()){
                    return false;
                }
                if(s.charAt(i)==')'){
                    if(stack.peek()=='('){
                        stack.pop();
                    } else {
                        return false;
                    }
                } else if (s.charAt(i)=='}'){
                    if(stack.peek()=='{'){
                        stack.pop();
                    } else {
                        return false;
                    }
                } else if (s.charAt(i)==']'){
                    if(stack.peek()=='['){
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        return stack.empty();
    }
}
