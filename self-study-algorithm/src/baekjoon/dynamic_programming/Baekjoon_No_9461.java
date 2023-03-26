package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_No_9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N  = Integer.parseInt(br.readLine());
        int[] testArr = new int[N];
        int max = 0;
        for(int i=0; i<N; i++) {
            testArr[i] = Integer.parseInt(br.readLine());
            max = Math.max(testArr[i], max);
        }

        // Int의 범위를 벗어나는 수가 있기 때문에 long으로 선언
        long[] dp = new long[max];
        dp[0] = 1;
        if(max > 1){
            dp[1] = 1;
        }
        if(max > 2){
            dp[2] = 1;
        }

        if(max > 3) {
            for(int i=3; i<max; i++) {
                // 점화식 : dp[i] = dp[i-3] + dp[i-2];
                dp[i] = dp[i-3] + dp[i-2];
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            sb.append(dp[testArr[i] - 1] + "\n");
        }

        System.out.println(sb.toString());
    }
}