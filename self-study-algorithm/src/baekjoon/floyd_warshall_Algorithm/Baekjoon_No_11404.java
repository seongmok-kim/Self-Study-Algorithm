package baekjoon.floyd_warshall_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_No_11404 {
	
	// N : 도시의 수
	// M : 노선의 수
	public static int N, M;
	
	// 노선 정보
	public static int[][] map;
	
	// 높은 숫자로 설정
	// Integer.MAXVALUE로 설정하면 추후에 일어날 더하기 연산에서 제대로 작동안하므로 아래 처럼 설정하였음.
	public static final int INF = (int)1e9;
	
	// 플로이드 와샬 알고리즘
	public static void floydWarshall() {

		for(int k=1; k<=N; k++) {
			for(int from=1; from<=N; from++) {
				for(int to=1; to<=N; to++){
					map[from][to] = Math.min(map[from][to], map[from][k] + map[k][to]);
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];

		// 최단거리 테이블을 무한으로 초기화.
		for(int i=0; i<map.length; i++) {
			Arrays.fill(map[i], INF);
		}
		
		M = Integer.parseInt(br.readLine());
		
		// 최단거리 테이블을 무한으로 초기화.
		for(int i=1; i<=N; i++) {
			map[i][i] = 0;
		}
				
		// 노선의 수 만큼 반복
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			// from 도시에서 to 도시로 가는 노선이 여러개가 있을 수 있으니 최소 비용만 기록한다.
			int tempCost = Math.min(map[from][to], cost);
			map[from][to] = tempCost;
		}
		
		br.close();
		
		// 플로이드-와샬 알고리즘을 이용해 각 노드별 최단거리 추출
		floydWarshall();
		
		// 출력
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j] == INF) {
					System.out.print(0 + " ");
				}
				else {
					System.out.print(map[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
}