package baekjoon.dikstra_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_1261_DFS_Fail {

	// 상하좌우 이동
	public static int[] amove = {-1, 0, 1, 0};
	public static int[] bmove = {0, 1, 0, -1};
	
	// 무한으로 표현될 변수
	public static final int INF = (int)1e9;
	
	// DFS에서 사용할 결과변수
	public static int result = INF;
	
	// N : 가로크기
	// M : 세로크기
	public static int N,M;
	
	// 맵 변수
	public static int[][] map;
	
	// 방문 체크 변수(DFS)
	public static boolean[][] visited;
	
	// DFS함수 시간초과 발생.
	public static void DFS(int a, int b, int count) {
		// 목적지에 도착 시, result와 비교 후 작으면 갱신
		if(a == M-1 && b == N-1) {
			result = Math.min(result, count);
			return;
		}
		
		// 상하좌우 체크
		for(int i=0; i<4; i++) {
			int tempa = a+amove[i];
			int tempb = b+bmove[i];
			
			// 범위 이상하면 벗어나기
			if(tempa < 0 || tempa>=M || tempb < 0 || tempb>=N) {
				continue;
			}
			
			// 방문 안한 곳이라면
			if(visited[tempa][tempb] == false) {
				// 방문 체크
				visited[tempa][tempb] = true;
				
				// 벽이라면
				if(map[tempa][tempb] == 1) {
					// 카운트 증가
					DFS(tempa, tempb, count+1);
				}
				// 아니면
				else {
					// 카운트 증가 X
					DFS(tempa, tempb, count);
				}
				
				// 백트래킹
				visited[tempa][tempb] = false;
			}
		}
	}  
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		visited = new boolean[M][N];
		
		for(int i=0; i<M; i++) {
			String temp = br.readLine();
			String[] tempArr = temp.split("");
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tempArr[j]);
			}
		}
		
		// 다익스트라 알고리즘을 이용하여 최소 비용 구하기
		DFS(0,0,0);
		
		System.out.println(result);
	}

}