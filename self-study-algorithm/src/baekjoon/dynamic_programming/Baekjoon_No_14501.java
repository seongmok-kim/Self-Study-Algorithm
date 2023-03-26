package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        // 21 : N의 최댓값 15 + Ti의 최댓값 5 + 하루
        int[] T = new int[20];
        int[] P = new int[20];
        int[] dp = new int[21];     // 20일째 받는 금액도 확인해야하므로 하루 추가
        int max = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int nowT = Integer.parseInt(st.nextToken());
            int nowP = Integer.parseInt(st.nextToken());

            T[i] = nowT;
            P[i] = nowP;
        }

        for(int i=0; i<=N; i++){

            // 현재 날짜까지의 최대 보수 비교 후 현재 날짜의 dp에 넣고, max도 갱신
            dp[i] = Math.max(dp[i], max);
            max = dp[i];

            // 퇴사 이후의 날짜는 무시
            if(i + T[i] > N) continue;

            // 비용을 많이 받을 수 있는 최댓값 확인
            // P[i] + dp[i] : 현재까지 얻은 최댓값 금액 + 이번에 진행하는 상담 금액
            // dp[i + T[i]] : i + T[i] 날짜에 정산받을 금액
            dp[i + T[i]]  = Math.max(P[i] + dp[i], dp[i + T[i]]);
        }

        System.out.println(max);
    }

}
