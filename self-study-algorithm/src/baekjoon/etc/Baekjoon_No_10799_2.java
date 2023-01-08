package baekjoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_No_10799_2 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s= br.readLine();

        // 레이저 표현 변경 () -> X
        s = s.replace("()", "X");

        int num = 0;			// 현재 봉의 갯수
        int result = 0;			// 조각 갯수

        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);

            // 봉 갯수 추가
            if(c == '(') {
                num++;
            }
            // 봉 갯수 감소 및 조각 갯수에 추가
            else if(c == ')'){
                result ++;
                num--;
            }
            // 레이저를 만나면 현재 봉의 갯수만큼 조각 갯수에 추가
            else if(c == 'X') {
                result += num;
            }
        }

        System.out.println(result);
    }
}
