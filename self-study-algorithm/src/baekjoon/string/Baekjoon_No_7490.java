package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_7490 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());

            DFS(num, "1", 1);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void DFS(int num, String str, int now) {
        if(now == num) {
            // 공백으로 표시한 건 단순히 공백을 제거해서 숫자끼리 합침
            String changedStr = str.replaceAll(" ", "");

            // StringTokenizer를 사용하는데, + or - 를 기준으로 구분하고, st에 구분자도 포함한다.
            StringTokenizer st = new StringTokenizer(changedStr, "+|-", true);

            int result = 0;
            while(st.hasMoreTokens()){
                String s = st.nextToken();
                if(s.equals("+"))       result += Integer.parseInt(st.nextToken());
                else if(s.equals("-"))  result -= Integer.parseInt(st.nextToken());
                else                    result = Integer.parseInt(s);
            }

            if(result == 0)     sb.append(str).append("\n");
        }
        else if(now < num){
            int next = now + 1;
            // 아스키코드 순서 상 공백, +, - 순서이다.
            DFS(num, str + " " + next, next);
            DFS(num, str + "+" + next, next);
            DFS(num, str + "-" + next, next);

        }
    }
}
