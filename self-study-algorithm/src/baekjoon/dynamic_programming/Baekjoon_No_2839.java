package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_No_2839 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N+1][2];				// 각 kg당 필요한 봉투의 갯수. (0: 3kg 봉투의 수, 1: 5kg 봉투의 수)
        boolean[] isOk = new boolean[N+1];			// 3kg과 5kg 봉투를 이용해 운반할 수 있는 지 여부
        if(N >= 3) {
            isOk[3] = true;
            dp[3][0] = 1;
            dp[3][1] = 0;
        }

        if(N >= 5) {
            isOk[5] = true;
            dp[5][0] = 0;
            dp[5][1] = 1;
        }

        // (현재무게-3) +1 OR (현재무게-5) +1
        // 모두 가능한 경우 적은 무게를 선택.
        for(int i=6; i<N+1; i++) {

            if(isOk[i-3] && isOk[i-5]) {
                if(dp[i-3][0] + dp[i-3][1] < dp[i-5][0] + dp[i-5][1]) {
                    dp[i][0] = dp[i-3][0] + 1;
                    dp[i][1] = dp[i-3][1];
                }
                else {
                    dp[i][0] = dp[i-5][0];
                    dp[i][1] = dp[i-5][1] + 1;
                }
                isOk[i] = true;
            }
            else if(isOk[i-3] & !isOk[i-5]) {
                dp[i][0] = dp[i-3][0] + 1;
                dp[i][1] = dp[i-3][1];
                isOk[i] = true;
            }
            else if(!isOk[i-3] & isOk[i-5]) {
                dp[i][0] = dp[i-5][0];
                dp[i][1] = dp[i-5][1] + 1;
                isOk[i] = true;
            }

        }

        if(!isOk[N]) {
            System.out.println(-1);
        } else {
            System.out.println(dp[N][0] + dp[N][1]);
        }

    }

}