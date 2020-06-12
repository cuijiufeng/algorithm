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
        long date = System.currentTimeMillis();
//        System.out.println(algo.longestPalindrome("cbbd"));
        System.out.println(algo.longestPalindrome("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc"));
        System.out.println(System.currentTimeMillis()-date);
    }

    public String longestPalindrome(String s) {
        if(s.isEmpty()) {
            return "";
        }
        if(s.length() == 1){
            return s;
        }
        int a, b;
        String str = "";
        for(int i=0; i<s.length(); i++){
            for(int j=i; j<s.length(); j++){
                a = i;
                b = j;
                boolean flag = true;
                while(a<b){
                    if(s.charAt(a) != s.charAt(b)){
                        flag = false;
                        break;
                    }
                    a++;
                    b--;
                }
                if(flag && j-i+1>str.length()){
                    str = s.substring(i, j+1);
                }
            }
        }
        return str;
    }
}
