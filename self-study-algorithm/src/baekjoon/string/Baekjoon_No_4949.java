package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baekjoon_No_4949 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        // FILO 구조인 STACK을 이용
        while(true) {
            String s = br.readLine();
            if(".".equals(s)) {
                break;
            }

            Stack<Character> stack = new Stack<>();

            char[] charArr = s.toCharArray();
            String result = "yes";

            for(char c : charArr) {
                if(c == '(' || c=='[') {
                    stack.push(c);
                }

                else if(c==')') {
                    if(stack.isEmpty()) {
                        result="no";
                        break;
                    }

                    char check = stack.pop();

                    if(check!='(') {
                        result="no";
                        break;
                    }
                }
                else if(c==']') {
                    if(stack.isEmpty()) {
                        result="no";
                        break;
                    }

                    char check = stack.pop();

                    if(check!='[') {
                        result="no";
                        break;
                    }
                }

            }

            if(!stack.isEmpty()) {
                result = "no";
            }

            sb.append(result+"\n");
        }

        System.out.println(sb.toString());
    }

}