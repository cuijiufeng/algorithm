package cn.algo.demo;

/**
 * @ClassName: AlgoDay3_3
 * @Description:
 * @Date: 2020/6/11 15:58
 * @auth: Administrator
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class AlgoDay3_3 {
    public static void main(String[] args){
        AlgoDay3_3 algo = new AlgoDay3_3();
        System.out.println(algo.longestPalindrome("babad"));
//        System.out.println(algo.longestPalindrome("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc"));
    }

    public String longestPalindrome(String s) {
        if(s.isEmpty()) {
            return "";
        }
        int cnt = 0;
        String str = "";
        for(int i=0; i<s.length(); i++){
            for(int j=i+1; j<=s.length(); j++){
                boolean flag = true;
                if (j-1 == 1){
                    flag = true;
                }
                int b = 0, e = j-1;
                while(b < e){
                    if(s.charAt(b) != s.charAt(e)){
                        flag =  false;
                    }
                    b++;
                    e--;
                }
                flag = true;
                if(flag){
                    if(j-i > cnt){
                        cnt = j-i;
                        str = s.substring(i, j);
                    }
                }
            }
        }
        return str;
    }
}
