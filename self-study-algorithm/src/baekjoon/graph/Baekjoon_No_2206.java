package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Link : https://www.acmicpc.net/problem/2206
public class Baekjoon_No_2206 {

	public static int N, M;
	
	public static int[][] map;
	public static boolean[][][] visited;
	
	public static int[] ymove = {-1,1,0,0};
	public static int[] xmove = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String ss = br.readLine();
			String[] sarr = ss.split("");
			
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(sarr[j]);
			}
		}
		
		// [0][?][?] : 벽을 안부쉈을 때의 방문 체크 변수
		// [1][?][?] : 벽을 부쉈을 때의 방문 체크 변수
		visited = new boolean[2][N][M];
		
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(0,0,1,false));
		visited[0][0][0] = true;		// 현재 벽을 부수지 않았으니 맨 앞의 배열 인자를 0으로 설정하고 방문 체크
		
		while(!q.isEmpty()){
			Pos pos = q.poll();
			int dis = pos.distance;
			boolean flag = pos.flag;
			
			// BFS이므로 목표 지점에 가장 먼저 도착하는 것이 최단 거리
			if(pos.y == N-1 && pos.x == M-1) {
				System.out.println(dis);
				return;
			}
			
			// 상하좌우 확인
			for(int i=0; i<4; i++) {
				int tempY = pos.y + ymove[i];
				int tempX = pos.x + xmove[i];
				
				// 범위 내
				if(tempY >= 0 && tempY < N && tempX >= 0 && tempX < M) {
					// 벽이라면
					if(map[tempY][tempX] == 1) {
						// 벽을 부순 적 없을 때만 이동 가능
						if(flag == false) {
							// 벽을 부순 이후 해당 지점에 방문했던 적이 없을 때
							if(visited[1][tempY][tempX] == false) {
								// 벽을 부순 이후의 방문 체크 변수를 방문 체크 후 큐에 추가
								visited[1][tempY][tempX] = true;
								q.offer(new Pos(tempY,tempX,dis+1,true));
							}
							
						}
					}
					// 벽이 아니라면
					else {
						// 이전에 벽을 부순적 이 있을 경우
						if(flag == true) {
							// 벽을 부순 이후 해당 지점에 방문했던 적이 없을 때
							if(visited[1][tempY][tempX] == false) {
								// 벽을 부순 이후의 방문 체크 변수를 방문 체크 후 큐에 추가
								visited[1][tempY][tempX] = true;
								q.offer(new Pos(tempY,tempX,dis+1,flag));
							}
						}
						// 이전에 벽을 부순적이 없을 경우
						else {
							// 벽을 부수지 않고 해당 지점에 방문한 적이 없을 때
							if(visited[0][tempY][tempX] == false) {
								// 벽을 부수지 않은 방문 체크 변수를 방문 체크 후 큐에 추가
								visited[0][tempY][tempX] = true;
								q.offer(new Pos(tempY,tempX,dis+1,flag));
							}
						}
					}
				}
			}
		}
		
		// 목적지까지 도착하지 않으면 여기까지 오므로 -1 출력
		System.out.println("-1");  // 결과 출력
	}
	
	
	public static class Pos{
		int y;
		int x;
		int distance;
		boolean flag;
		
		public Pos(int y, int x, int distance, boolean flag) {
			this.y = y;
			this.x = x;
			this.distance = distance;
			this.flag = flag;
		}
	}

}