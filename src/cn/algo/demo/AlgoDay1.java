package cn.algo.demo;

import java.util.Scanner;

/**
 * @ClassName: AlgoDay1
 * @Description: day1
 * @Date: 2020/6/9 11:54
 * @auth: Administrator
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * 示例 1:
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是'bccfi', 'bwfi', 'bczi', 'mcfi'和'mzi'
 * 提示：
 * 0 <= num < 231
 */
class AlgoDay1 {
    public static void main(String[] args) {
        AlgoDay1 algoDay1 = new AlgoDay1();
        System.out.println(algoDay1.translateNum(12258));
    }
    private final char str[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private int cnt = 0;
    public int translateNum(int num) {
        f(String.valueOf(num), "");
        return cnt;
    }

    public void f(String numStr, String rsStr){
        if(numStr.isEmpty()){
            cnt++;
            return;
        }
        for(int i=1; i<=2; i++){
            if(i==2 && (numStr.length()<2 || Integer.parseInt(numStr.substring(0, 2))>25)){
                return;
            }
            Character t = null;
            if(i == 1){
                t =  str[numStr.charAt(0)-'0'];
            } else if(i == 2){
                if(numStr.charAt(0)=='0'){
                    return;
                }
                t = str[Integer.parseInt((numStr.charAt(0)+""+numStr.charAt(1)))];
            }
            f(numStr.substring(i), rsStr+t);
        }
    }
}
