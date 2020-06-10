package cn.algo.demo;

/**
 * @author cui
 * @TIME 2020/6/10 21:51
 * @Desc
 *
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
示例 1:
输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:
输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:
输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class AlgoDay2_4 {
    public static void main(String[] args) {
        AlgoDay2_4 algoDay2_4 = new AlgoDay2_4();
        System.out.println(algoDay2_4.lengthOfLongestSubstring("abcabcbb"));
    }

    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 1){
            return 1;
        }
        int rs = 0;
        for(int i=1; i<s.length(); i++){
            String str = s.substring(i);
            if(str.contains(s.charAt(0)+"")){
                rs = lengthOfLongestSubstring(str);
            } else {
                rs = lengthOfLongestSubstring(str)+1;
            }
        }
        return rs;
    }
}
