package leetcode.editor.cn;//编写一个算法来判断一个数 n 是不是快乐数。
//
// 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
//如果 可以变为 1，那么这个数就是快乐数。 
//
// 如果 n 是快乐数就返回 True ；不是，则返回 False 。 
//
// 
//
// 示例： 
//
// 输入：19
//输出：true
//解释：
//12 + 92 = 82
//82 + 22 = 68
//62 + 82 = 100
//12 + 02 + 02 = 1
// 
// Related Topics 哈希表 数学 
// 👍 459 👎 0


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class IsHappy{
    public static void main(String[] args){
        Solution solu = new IsHappy().new Solution();
        System.out.println(solu.isHappy(19));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isHappy(int n) {
            Map<Integer, Integer> map = new HashMap<>();

            while (true) {
                map.put(n, n);
                int cnt = 0, t = n, num = n;
                while(t != 0){
                    cnt++;
                    t /= 10;
                }
                char[] chs = new char[cnt];
                cnt = 0;
                while (num != 0){
                    chs[cnt++] = (char)(num % 10);
                    num /= 10;
                }
                n = 0;
                for (int i = 0; i < chs.length; i++) {
                    n += chs[i]*chs[i];
                }
                if (n == 1){
                    return true;
                }
                Iterator<Integer> iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    if (iterator.next() == n) {
                        return false;
                    }
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}

