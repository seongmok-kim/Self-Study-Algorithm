package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Link : https://www.acmicpc.net/problem/2589
public class Baekjoon_No_2589 {

	// H: 높이, W: 넓이, MAX_LENGTH: 육지간 최대 거리
	public static int H, W, MAX_LENGTH;
	
	public static char[][] map;
	
	public static boolean[][] visited;
	
	// 상하좌우 확인에 사용
	public static int[] ymove = {-1,1,0,0};
	public static int[] xmove = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new char[H][W];
		
		Queue<Pos> q = new LinkedList<>();
		
		for(int i=0; i<H; i++) {
			String temp = br.readLine();
			map[i] = temp.toCharArray();
		}
		
		MAX_LENGTH = 0;		// 0으로 초기화

		// 맵 전체 탐색
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				
				// 육지일 때만 
				if(map[i][j] == 'L') {
					
					visited = new boolean[H][W];		// 방문체크 변수 초기화
					
					q.offer(new Pos(i,j,0));			// 현재 위치랑 0을 Pos에 넣고
					visited[i][j] = true;				// 방문 체크
					
					// q가 빌 때까지 반복
					while(!q.isEmpty()) {
						Pos p = q.poll();
						int d = p.distance;
						
						// 큐에서 꺼낸 거리가 MAX_LENGTH보다 길 때
						if(d > MAX_LENGTH) {
							MAX_LENGTH = d;			// MAX_LENGTH 갱신
						}
						
						// 상하좌우 확인
						for(int k=0; k<4; k++) {
							int tempY = p.y + ymove[k];
							int tempX = p.x + xmove[k];
							
							// 범위 내에 있고
							if(tempY >= 0 && tempY < H && tempX >= 0 && tempX < W) {
								
								// 방문한 적 없는 육지일 때
								if(visited[tempY][tempX] == false && map[tempY][tempX] == 'L') {
									visited[tempY][tempX] = true;				// 방문 체크 후
									q.offer(new Pos(tempY, tempX, d+1));		// 큐에 넣기 ( 현재거리에서 +1)  
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println(MAX_LENGTH);			//	MAX_LENGTH 출력 
	}
	
	public static class Pos{
		int y;
		int x;
		int distance;
		
		public Pos(int y, int x, int dis) {
			this.y = y;
			this.x = x;
			this.distance = dis;
		}
	}

}