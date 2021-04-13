package leetcode.editor.cn;//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
//
// 
//
// 示例 1： 
//
// 输入：s = "We are happy."
//输出："We%20are%20happy." 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 10000 
// 👍 42 👎 0

class ReplaceSpace {
    public static void main(String[] args) {
        Solution solution = new ReplaceSpace().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String replaceSpace(String s) {
            int cnt = 0;
            int idx = 0;
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == ' ') {
                    cnt++;
                }
            }
            char[] newChars = new char[chars.length + cnt * 2];
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == ' ') {
                    newChars[idx++] = '%';
                    newChars[idx++] = '2';
                    newChars[idx++] = '0';
                    continue;
                }
                newChars[idx++] = chars[i];
            }
            return new String(newChars);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
