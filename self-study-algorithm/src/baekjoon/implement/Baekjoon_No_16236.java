package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_16236 {

	// 2<=N<=20
	public static int N, M;
	
	public static int[][] map;
	
	// 상하좌우 이동에 사용 
	public static int[] ymove = {-1, 0, 0, 1};
	public static int[] xmove = {0, -1, 1, 0};
		
	public static int sharkSize = 2;
	public static int eat = 0;
	
	// 위치를 기록하는 클래스
	public static class Pos{
		int y;
		int x;
		int move;
		
		public Pos(int y, int x, int move) {
			this.y = y;
			this.x = x;
			this.move = move;
		}
	}
	
	// 먹이 찾기
	public static Pos findFeed(Pos shark) {
		// 큐를 이용해 BFS탐색으로 진행
		Queue<Pos> q = new LinkedList<>();
		
		// 방문체크변수
		boolean[][] visited = new boolean[N][N];
		
		visited[shark.y][shark.x] = true;
		q.offer(shark);
		
		// 갈 수 있는 먹이 중 가장 짧은 거리
		int minDistance = Integer.MAX_VALUE;
		
		// 먹이 위치 
		Pos target = new Pos(-1,-1,shark.move);
		
		// 큐가 빌 때까지 반복
		while(!q.isEmpty()) {
			Pos now = q.poll();
			int move = now.move;
			
			// 가까운 거 부터 탐색
			// 상하좌우탐색
			for(int i=0; i<4; i++) {
				int tempY = now.y + ymove[i];
				int tempX = now.x + xmove[i];
				
				// minDistance보다 먼 곳은 무시
				if(minDistance<now.move+1) {
					continue;
				}
				
				// 맵 범위 내에 있으며, 방문하지 않은 곳이면 확인
				if(tempY >= 0 && tempY < N && tempX >= 0 && tempX < N && visited[tempY][tempX] == false) {
					
					// 0이 아니고(먹이여야하고) 아기상어의 몸집보다 작은 경우 먹이임
					if(map[tempY][tempX] != 0 && map[tempY][tempX] < sharkSize) {
						
						// minDistance랑 같은 거리면
						if(minDistance == move+1) {
							// 현재 먹이가 더 위쪽에 있으면
							if(target.y > tempY) {
								// 먹이 변경
								target = new Pos(tempY, tempX, move+1);
							}
							// 같은 위치에
							else if(target.y == tempY) {
								// 먹이가 더 왼쪽에 있으면
								if(target.x > tempX) {
									// 먹이 변경
									target = new Pos(tempY, tempX, move+1);
								}
							}
						}
						// minDistance보다 짧은 거리면
						else if(minDistance > move+1) {
							// 먹이 변경 후 minDistance 갱신
							target = new Pos(tempY, tempX, move+1);
							minDistance = move+1;
						}
					}
					
					// 먹이가 아니면 탐색
					if(map[tempY][tempX] <= sharkSize) {
						visited[tempY][tempX] = true;
						q.offer(new Pos(tempY, tempX, move+1));
					}
				}
			}
		}
		
		// 먹이가 있는 경우
		if(target.y != -1 && target.x != -1) {
			// 먹이 위치 0 으로 변경 후
			map[target.y][target.x] = 0;

			// 먹고
			eat++;
			
			// 크기 갱신
			if(eat == sharkSize) {
				sharkSize++;
				eat = 0;
			}
		}
		
		return target;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		
		// 아기 상어 객체
		// new Pos(y, x, move)
		Pos shark = new Pos(0,0,0);
		
		for(int i=0; i<N; i++)  {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				// 아기상어 위치 기록 후 0으로 변경하여 대입
				if(map[i][j] == 9) {
					shark.y = i;
					shark.x = j;
					shark.move = 0;
					map[i][j] = 0;
				}
			}
		}
		
		// 아기상어 객체의 y가 -1, x가 -1이 아니면 계속 먹이 찾기
		while(shark.y != -1 && shark.x != -1) {
			shark = findFeed(shark);
		}
		
		// 결과 출력
		System.out.println(shark.move);
	}

}