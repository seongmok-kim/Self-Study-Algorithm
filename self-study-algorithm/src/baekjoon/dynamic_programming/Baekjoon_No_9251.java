package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_No_9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        int str1Length = str1.length();
        String str2 = br.readLine();
        int str2Length = str2.length();

        int[][] dp = new int[str2Length+1][str1Length+1];

        for(int i = 1; i <= str2Length; i++) {
            char C = str2.charAt(i-1);
            for(int j = 1; j <= str1Length; j++) {
                // str1의 문자와 str2의 문자가 같으면 1 증가
                if(C == str1.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + 1;
                    // 다르다면, 이전크기와 그 전 줄의 최댓값을 넣어준다.
                else
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }

        System.out.println(dp[str2Length][str1Length]);

    }
}