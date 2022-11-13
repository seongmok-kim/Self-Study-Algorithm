package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_No_1003_ {

    static int T;

    static int[][] dp;

    static int MAX = 40;		// 최대 40까지라고 문제에 써져있음

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        int[] arr = new int[T];

        for(int i=0; i<T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // DP사용 Bottom-Up 방식
        // DP 참고 : https://ajdahrdl.tistory.com/112
        // dp[n][0] : n에 0이 나오는 횟수, dp[n][1] : n에 1이 나오는 횟수
        dp = new int[MAX+1][2];

        dp[0][0] = 1;
        dp[0][1] = 0;

        dp[1][0] = 0;
        dp[1][1] = 1;

        dp[2][0] = 1;
        dp[2][1] = 1;

        // 점화식은 아래와 같다.
        for(int i=3; i<=MAX; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-2][0];
            dp[i][1] = dp[i-1][1] + dp[i-2][1];
        }

        // 시간 단축을 위해 StringBuilder로 한 번에 출력
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<T; i++) {
            sb.append(dp[arr[i]][0]+" "+dp[arr[i]][1]+"\n");
        }

        System.out.println(sb.toString());
    }

}