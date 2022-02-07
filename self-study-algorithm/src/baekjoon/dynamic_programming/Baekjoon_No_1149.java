package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_1149 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// N개의 집과 해당 집의 도배에 필요한 각각의 비용을 넣는 변수
		int[][] arr = new int[N][3];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());		
			}
		}
		
		// 비용을 저장해두는 변수
		int[][] cost = new int[N][3];
		cost[0][0] = arr[0][0];
		cost[0][1] = arr[0][1];
		cost[0][2] = arr[0][2];

		// 비용 계산
		// 점화식 (0:R색, 1:G색, 2:B색)
		// N번째 집을 R색으로 칠한 경우 cost[i][0] = min(cost[i][1], cost[i][2]) + arr[i][0]
		// N번째 집을 G색으로 칠한 경우 cost[i][1] = min(cost[i][0], cost[i][2]) + arr[i][1]
		// N번째 집을 B색으로 칠한 경우 cost[i][2] = min(cost[i][1], cost[i][0]) + arr[i][2]
		for(int i=1;i<N;i++) {
			cost[i][0] = Math.min(cost[i-1][1], cost[i-1][2]) + arr[i][0];
			cost[i][1] = Math.min(cost[i-1][0], cost[i-1][2]) + arr[i][1];
			cost[i][2] = Math.min(cost[i-1][0], cost[i-1][1]) + arr[i][2];
		}
		
		// 마지막에 N번 째 집을 RGB 로 도배한 비용 중에 가장 작은 값을 반환한다.
		int result = Math.min(cost[N-1][0], Math.min(cost[N-1][1], cost[N-1][2]));
		
		System.out.println(result);
	}

}
