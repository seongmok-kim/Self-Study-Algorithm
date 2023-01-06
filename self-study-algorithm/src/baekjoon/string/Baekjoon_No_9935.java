package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baekjoon_No_9935 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        String bomb = br.readLine();
        int bombSize = bomb.length();

        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            stack.push(s.charAt(i));

            if(stack.size() >= bombSize) {
                boolean flag = true;
                for(int j=0; j<bombSize; j++) {
                    // 스택에 있는 문자열 중 가장 마지막 문자열(폭탄 사이즈만큼)을 확인
                    if(stack.get(stack.size() - bombSize + j) != bomb.charAt(j)) {
                        flag = false;
                        break;
                    }
                }

                if(flag) {
                    // 스택 마지막에 들어온 문자열과 폭탄을 비교해서 flag값이 true일 때 마지막에 들어온 문자열(폭탄) 을 pop
                    for(int j=0; j<bombSize; j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Character c : stack) {
            sb.append(c);
        }

        if(sb.length() == 0) {
            System.out.println("FRULA");
        }
        else {
            System.out.println(sb.toString());
        }
    }

}