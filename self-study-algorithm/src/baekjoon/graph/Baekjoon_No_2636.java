package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Link : https://www.acmicpc.net/problem/2636
public class Baekjoon_No_2636 {

	// H: 높이, W: 넓이, CHEESE_COUNT: 남은 치즈의 수
	public static int H, W, CHEESE_COUNT;

	public static int[][] map;
	public static boolean[][] visited;
	
	public static int[] xmove = {-1,1,0,0};
	public static int[] ymove = {0,0,-1,1};
	
	public static Queue<Pos> q = new LinkedList<Pos>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		
		CHEESE_COUNT = 0;
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<W; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				
				// 치즈를 발견할 떄마다 치즈 갯수 세기
				if(num == 1) {
					CHEESE_COUNT++;
				}
			}
		}

		// 시간 경과체크용
		int hours = 0;
		while(true) {
			hours++;
			
			visited = new boolean[H][W];
			
			int deleteCheeseCnt = KillCheese();		// 녹은 치즈의 갯수 반환
			
			// 만약 녹은 치즈의 갯수가 남아있던 치즈의 갯수와 같다면 탈출
			if(CHEESE_COUNT == deleteCheeseCnt) {
				break;
			}
			// 아니라면
			else {
				// 남은 치즈의 갯수만큼 뺴주고 반복
				CHEESE_COUNT -= deleteCheeseCnt;
			}
			
		}
		
		System.out.println(hours+"\n"+CHEESE_COUNT);
	}

	// BFS를 이용해서 치즈 녹이기
	public static int KillCheese() {
		
		q.offer(new Pos(0,0));		// 0,0에서 시작 (0,0은 무조건 공기가 있기 때문)
		visited[0][0] = true;		// 방문 체크
		
		int deleted = 0;			// 녹은 치즈의 갯수
		
		// 큐가 빌 때까지 반복
		while(!q.isEmpty()) {
			Pos pos = q.poll();
			int y = pos.y;
			int x = pos.x;
			
			// 상하좌우 확인
			for(int i=0; i<4; i++) {
				int tempY = y + ymove[i];
				int tempX = x + xmove[i];
				
				// 범위 이내만 확인
				if(tempY >= 0 && tempY < H && tempX >=0 && tempX < W) {
					// 방문 안한 칸만 확인
					if(visited[tempY][tempX] == false) {
						
						// 치즈일 경우 (1)
						if(map[tempY][tempX] == 1) {
							deleted++;					// 녹은 치즈의 갯수 증가시키고
							map[tempY][tempX] = 0;		// 공기로 바꿔줌
						}
						// 공기일 경우 (0)
						else {
							q.offer(new Pos(tempY, tempX));		// 큐에 추가
						}
						visited[tempY][tempX] = true;		// 방문 체크
					}
				}
			}
		}
		
		return deleted;
		
	}
	
	public static class Pos{
		int y;
		int x;
		
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
}