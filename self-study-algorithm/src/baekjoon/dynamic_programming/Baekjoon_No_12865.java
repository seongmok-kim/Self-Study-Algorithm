package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_12865 {
	
	// N : 물건의 갯수, K : 버틸 수 있는 무게
	public static int N, K;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// dp 배열
		int[][] d = new int[N+1][K+1];
		
		// 무게, 가치를 갖는 배열(따로 선언)
		int[] weight = new int[N+1];
		int[] value = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 따로저장
			weight[i]=Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=K; j++) {
				// i : 물건 index
				// j : 무게
				// weight : 물건의 무게
				d[i][j] = d[i-1][j];
				
				// 현재 들 수 있는 무게가 물건의 무게보다 많을 경우 
				// (현재 들 수 있는 무게 - 지금 물건의 무게)의 나머지 무게도 추가해서 비교한다. 
				if(j >= weight[i]) {
					
					d[i][j] = Math.max(d[i][j], d[i-1][j-weight[i]] + value[i]);
				}
			}
		}
		
		// N가지의 물건 중, 최대 K무게를 들었을 때 최대 가치 출력
		System.out.println(d[N][K]);
	}
}	
