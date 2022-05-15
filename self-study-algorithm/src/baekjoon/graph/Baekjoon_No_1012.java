package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_1012 {

	// 상하좌우 이동에 사용
	public static int[] ymove = {-1,1,0,0};
	public static int[] xmove = {0,0,1,-1};
	
	// 좌표 클래스
	public static class Pos{
		int y;
		int x;
		
		public Pos(int y, int x) {
			this.y=y;
			this.x=x;
		}
	}
	
	// 필요한 지렁이의 갯수 구하기
	public static int calcWorm(int[][] map, boolean[][] visited) {
		
		int count = 0;		// 필요한 지렁이의 갯수
		
		// 지도 전체 탐색 (BFS 방식 사용)
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				
				// 방문하지 않은 좌표인데, 그 좌표가 배추일 경우 
				if(visited[i][j] == false && map[i][j] == 1) {
					
					 // 큐를 만들고 좌표 정보를 이 큐에다가 넣음 
					 Queue<Pos> q = new LinkedList<>();
					 q.offer(new Pos(i,j));

					 // 방문 처리
					 visited[i][j] = true;
					 
					 // 필요한 지렁이의 갯수 1 증가
					 count++;
					 
					 // 현재 좌표에서 넘어갈 수 있는 다른 배추에 방문 및 방문 처리
					 while(!q.isEmpty()) {
						 Pos now = q.poll();
						 
						 for(int k=0; k<4; k++) {
							 int tempY = now.y + ymove[k];
							 int tempX = now.x + xmove[k];
							 
							 if(tempY >= 0 && tempY < map.length && tempX >= 0 && tempX < map[i].length) {
								 if(map[tempY][tempX] == 1 && visited[tempY][tempX] == false) {
									 q.offer(new Pos(tempY, tempX));
									 visited[tempY][tempX] = true;
								 }
							 }
						 }
					 }
				}
			}
		}
		
		// 필요한 지렁이의 갯수 반환
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		int[] result = new int[T];
		
		// 테스트 케이스의 수만큼 반복
		for(int i=0; i<T; i++) {
			int M, N, K;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken()); 	// 배추 밭의 가로길이
			N = Integer.parseInt(st.nextToken());	// 배추 밭의 세로길이
			K = Integer.parseInt(st.nextToken());	// 배추의 갯수
			
			int[][] map = new int[N][M];
			boolean[][] visited = new boolean[N][M];
			
			for(int j=0; j<K; j++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				map[y][x] = 1;
			}
			
			// 배열에 임시 저장
			result[i] = calcWorm(map, visited);
		}
		
		// 배열에 있는 내용 출력
		for(int i=0; i<T; i++) {
			System.out.println(result[i]);
		}
	}

}