package baekjoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baekjoon_No_10799_1 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s= br.readLine();
        Stack<Character> stack = new Stack<>();

        // 레이저 표현 변경 () -> X
        s = s.replace("()", "X");

        int result = 0;			// 조각 갯수

        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);

            // 봉 갯수 추가
            if(c == '(') {
                stack.add(c);
            }
            // 봉 갯수 감소 및 조각 갯수에 추가
            else if(c == ')'){
                stack.pop();
                result ++;
            }
            // 레이저를 만나면 현재 봉의 갯수만큼 조각 갯수에 추가
            else if(c == 'X') {
                result += stack.size();
            }
        }

        System.out.println(result);
    }
}
