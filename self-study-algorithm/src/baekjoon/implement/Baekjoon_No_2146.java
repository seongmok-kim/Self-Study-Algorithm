package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Link : https://www.acmicpc.net/problem/2146
public class Baekjoon_No_2146 {

	public static int N;
	public static int MIN = Integer.MAX_VALUE;
	
	public static int[][] map;
	public static int[][] sector;
	public static boolean[][] visited;
	
	public static int[] ymove = {-1,1,0,0};
	public static int[] xmove = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		sector = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 육지 별로 구역 나누기
		visited = new boolean[N][N];
		int now = 1;

		// 전체탐색을 돌면서 구역 별 숫자를 넣는다. (DFS이용)
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visited[i][j] == false && map[i][j] == 1) {
					visited[i][j] = true;
					DFS(i,j, now);
					now++;
				}
			}
		}
		
		// 구역 간 최소 거리 구하기
		// 전체 탐색 BFS이용
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				visited = new boolean[N][N];		// 방문체크변수 초기화
				int start = sector[i][j];			// 시작 구역 번호 저장
				
				// 시작 구역이 0(바다)가 아닐 때 확인
				if(start != 0) {
					Queue<Pos> q = new LinkedList<>();		// 큐
					q.offer(new Pos(i,j,0));				// 큐에 현재 위치 넣고
					visited[i][j] = true;					// 방문 체크
					
					// 큐가 빌 때까지 반복
					while(!q.isEmpty()){
						Pos p = q.poll();					// 큐에서 꺼내고
						
						int d = p.d;
						int y = p.y;
						int x = p.x;

						// 큐에서 꺼낸 곳의 구역이 바다가 아니고
						if(sector[y][x] != 0) {
							// 시작 구역의 번호랑 다르다면 다른 구역으로 도착한 것임
							if(sector[y][x] != start) {
								
								// d-1을 해주는 이유 = 다른 육지로 도착했을 때는 다리로 치지 않기 때문
								// 작은 값으로 갱신
								if(d-1 < MIN) {
									MIN = d-1;
									break;
								}
							}
						}
						
						// 상하좌우 확인
						for(int k=0; k<4; k++) {
							int tempY = y + ymove[k];
							int tempX = x + xmove[k];
							
							// 범위 내이고
							if(tempY >= 0 && tempY < N && tempX >= 0 && tempX < N) {
								
								// 같은 구역이 아니면서 바다가 아닌 곳에 방문하지 않은 곳
								if(visited[tempY][tempX] == false && sector[tempY][tempX] != start)  {
									q.offer(new Pos(tempY, tempX, d+1));
									visited[tempY][tempX] = true;
								}
							}
						}
					}
				}
			}
		}
		
		// 결과 출력
		System.out.println(MIN);
	}
	
	// 구역 별 숫자 넣기
	public static void DFS(int y, int x, int num) {
		sector[y][x] = num;
		
		for(int i=0; i<4; i++) {
			int tempY = y + ymove[i];
			int tempX = x + xmove[i];
			
			if(tempY >= 0 && tempY < N && tempX >= 0 && tempX < N) {
				if(visited[tempY][tempX] == false && map[tempY][tempX] == 1) {
					visited[tempY][tempX] = true;
					DFS(tempY, tempX, num);
				}
			}
		}
	}
	
	public static class Pos{
		int y;
		int x;
		int d;
		
		public Pos(int y, int x, int d){
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
	
}