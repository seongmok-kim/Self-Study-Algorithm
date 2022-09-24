package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// Link : https://www.acmicpc.net/problem/5397
public class Baekjoon_No_5397 {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            String str = br.readLine();

            Decode(str);		// 문자열 해석
        }

        System.out.println(sb.toString());
    }

    // 문자열 해석
    public static void Decode(String str) {
        char[] charArr = str.toCharArray();

        // Stack을 이용해서 풀이.
        Stack<Character> left = new Stack<Character>();		// 커서 기준 왼쪽 문자들
        Stack<Character> right = new Stack<Character>();		// 커서 기준 오른쪽 문자들

        for(char ch : charArr) {
            switch(ch) {
                // 왼쪽 문자 하나를 오른쪽 문자로 옮김.
                case '<':
                    if(!left.isEmpty())
                        right.push(left.pop());
                    break;
                // 오른쪽 문자 하나를 왼쪽 문자로 옮김
                case '>':
                    if(!right.isEmpty())
                        left.push(right.pop());
                    break;
                // 커서 왼쪽 문자 하나 삭제
                case '-':
                    if(!left.isEmpty())
                        left.pop();
                    break;
                // 커서 왼쪽 문자에 문자 추가
                default:
                    left.push(ch);
                    break;
            }
        }

        StringBuilder ss = new StringBuilder();

        // 커서 기준 오른쪽 문자를 왼쪽 문자에 넣어줌
        while(!right.isEmpty()) {
            left.push(right.pop());
        }

        // stack은 FILO이므로 pop이 아닌 elementAt을 이용해서 앞에서부터 차례대로 확인
        for(int i=0; i<left.size(); i++) {
            ss.append(left.elementAt(i));
        }

        sb.append(ss.toString()+"\n");
    }
}