package baekjoon.floyd_warshall_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_10159 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] sheet = new int[N+1][N+1];

        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            sheet[num1][num2] = 1;      // num1이 num2보다 무겁다
            sheet[num2][num1] = -1;     // num2가 num1보다 가볍다
        }

        for(int i=1; i<=N; i++) {
            for(int from=1; from<=N; from++) {
                for(int to=1; to<=N; to++) {
                    if(sheet[from][i] == 1 && sheet[i][to] == 1){
                        sheet[from][to] = 1;
                        sheet[to][from] = -1;
                    }
                    else if(sheet[from][i] == -1 && sheet[i][to] == -1){
                        sheet[from][to] = -1;
                        sheet[to][from] = 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            int cnt = 0;
            for(int j=1; j<=N; j++) {
                if(i==j)    continue;

                // 0 : 비교할 수 없는 물건
                if(sheet[i][j] == 0) cnt++;
            }

            sb.append(cnt+"\n");
        }
        System.out.println(sb.toString());
    }
}
