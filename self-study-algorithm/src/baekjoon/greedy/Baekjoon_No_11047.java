package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int answer = 0;

        int[] moneyType = new int[N];
        for (int i = 0; i < N; i++) {
            moneyType[i] = Integer.parseInt(br.readLine());
        }

        for (int i=N-1; i>=0; i--) {
            int money = moneyType[i];
            int count = K / money;
            if (count > 0) {
                K -= (money * count);
                answer += count;
            }

            if (K == 0) {
                break;
            }
        }

        System.out.println(answer);
    }
}
