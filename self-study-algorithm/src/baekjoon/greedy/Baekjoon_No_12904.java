package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_No_12904 {

    static String s1 = "";
    static String s2 = "";

    static boolean flag = false;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s1 = br.readLine();
        s2 = br.readLine();


        // 문제 핵심 : s1에서 s2까지 찾는것이 아닌, s2에서 s1까지 확인.
        // s2의 마지막 알파벳이 A라면 A를 빼주고, 마지막 알파벳이 B라면 B를 빼주고 문자열을 거꾸로 뒤집으면서 s1을 찾으면 된다.
        while(s2.length() > s1.length()) {
            String lastStr = s2.substring(s2.length()-1, s2.length());
            if(lastStr.equals("A")) {
                s2 = Operation_1(s2);
            }
            else {
                s2 = Operation_2(s2);
            }
        }

        if(s2.equals(s1))
            System.out.println(1);
        else
            System.out.println(0);
    }

    // 연산 1 : 문자열의 뒤에 A를 제거
    static String Operation_1(String s) {
        String str = s.substring(0, s.length()-1);

        return str;
    }

    // 연산 2 : 문자열 뒤의 B를 제거 후 문자열을 뒤집기
    static String Operation_2(String s) {
        s = s.substring(0, s.length()-1);

        char[] charArr = s.toCharArray();
        StringBuilder sb = new StringBuilder();

        for(int i=charArr.length-1; i>=0; i--) {
            sb.append(charArr[i]);
        }

        return sb.toString();
    }

}
