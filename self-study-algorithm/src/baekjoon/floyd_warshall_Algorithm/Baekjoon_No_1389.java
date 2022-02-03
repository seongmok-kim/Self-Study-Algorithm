package baekjoon.floyd_warshall_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_No_1389 {

	// 그래프
	public static int[][] graph;
	
	// N : 친구(노드)의 수
	// M : 친구관계(간선)의 수
	public static int N, M;

	// 무한을 의미 (약 10억)
	public static final int INF = (int)1e9;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[N+1][N+1];
		
		// 무한으로 초기화
		for(int i=0; i<=N; i++) {
			Arrays.fill(graph[i], INF);
		}
		
		// 자기 자신은 0
		for(int i=0; i<=N; i++) {
			graph[i][i] = 0;
		}
		
		// 친구관계(간선) 입력
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			// 서로 연결되어있으니 n1과 n2 서로 연결
			graph[n1][n2] = 1;
			graph[n2][n1] = 1;
		}
		
		// 플로이드 워셜 알고리즘 
		for(int temp=1; temp<=N; temp++) {
			for(int from=1; from<=N; from++) {
				for(int to=1; to<=N; to++) {
					graph[from][to] = Math.min(graph[from][to], graph[from][temp] + graph[temp][to]);
				}
			}
		}
		
		// 결과 출력
		int result = -1;
		int min = INF;
		
		for(int i=1; i<=N; i++) {
			int sum = 0;
			for(int j=1; j<=N; j++) {
				sum += graph[i][j];
			}
			
			if(sum < min) {
				result = i;
				min = sum;
			}
		}
		
		System.out.println(result);
		
	}

}
