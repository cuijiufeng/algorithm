package leetcode.editor.cn;
//给定一个用字符串表示的整数的嵌套列表，实现一个解析它的语法分析器。
//
// 列表中的每个元素只可能是整数或整数嵌套列表 
//
// 提示：你可以假定这些字符串都是格式良好的： 
//
// 
// 字符串非空 
// 字符串不包含空格 
// 字符串只包含数字0-9、[、-、,、] 
// 
//
// 
//
// 示例 1： 
//
// 给定 s = "324",
//
//你应该返回一个 NestedInteger 对象，其中只包含整数值 324。
// 
//
// 示例 2： 
//
// 给定 s = "[123,[456,[789]]]",
//
//返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：
//
//1. 一个 integer 包含值 123
//2. 一个包含两个元素的嵌套列表：
//    i.  一个 integer 包含值 456
//    ii. 一个包含一个元素的嵌套列表
//         a. 一个 integer 包含值 789
// 
// Related Topics 栈 字符串

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation

class Deserialize{
    public static void main(String[] args){
        Solution solu = new Deserialize1().new Solution();
        solu.deserialize("[123,456,[788,799,833],[[]],10,[]]");
    }
    class NestedInteger {
        private Integer num = null;
        private List<NestedInteger> list = new ArrayList<>();
        // Constructor initializes an empty nested list.
        public NestedInteger(){}

        // Constructor initializes a single integer.
        public NestedInteger(int value){num = value;}

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger(){return num != null;}

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger(){return num;}

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value){this.num = value;}

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni){list.add(ni);}

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList(){return list;}
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public NestedInteger deserialize(String s) {
            if (s == null || s.isEmpty()){
                return new NestedInteger();
            }
            if(s.matches("[-0-9]*")){
                return new NestedInteger(Integer.parseInt(s));
            }
            int numCount = 0;
            boolean flag = false;
            boolean isNega = false;
            Stack<NestedInteger> stack = new Stack<NestedInteger>();
            stack.push(new NestedInteger());
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '[') {
                    flag = false;
                    stack.push(new NestedInteger());
                    numCount = 0;
                } else if (s.charAt(i) == ']') {
                    if (flag){
                        int num = isNega ? -1*Integer.parseInt(s.substring(i - numCount, i)): Integer.parseInt(s.substring(i - numCount, i));
                        stack.peek().add(new NestedInteger(num));
                    }
                    numCount = 0;
                    NestedInteger t = stack.pop();
                    stack.peek().add(t);
                    flag = false;
                    isNega = false;
                } else if (s.charAt(i) == '-') {
                    flag = false;
                    isNega = true;
                    numCount = 0;
                } else if (s.charAt(i) == ',') {
                    if(flag){
                        int num = isNega ? -1*Integer.parseInt(s.substring(i - numCount, i)): Integer.parseInt(s.substring(i - numCount, i));
                        stack.peek().add(new NestedInteger(num));
                        isNega = false;
                    }
                    numCount = 0;
                    flag = false;
                } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    flag = true;
                    numCount++;
                }
            }
            return stack.pop().getList().get(0);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
