package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_7576 {

	// 토마토에 대한 정보
	public static class Info{
		
		int y;		// 토마토 y위치
		int x;		// 토마토 x위치
		int days;	// 경과한 날짜
		
		public Info(int y, int x, int days) {
			this.y = y;
			this.x = x;
			this.days = days;
		}
	}
	
	// 큐 이용
	public static Queue<Info> q = new LinkedList<>();
	
	public static int M, N;
	
	public static int[][] map;

	// 상하좌우에 사용할 배열
	public static int[] ymove = {-1,0,1,0};
	public static int[] xmove = {0,1,0,-1};
	
	// 최소 날짜 
	public static int days = 0;
	
	// BFS탐색을 이용
	public static int BFS() {
		int cnt = 0;	// 안익은 토마토에서 익은 토마토로 변한 토마토의 갯수
		
		// 큐가 빌 떄까지 반복
		while(!q.isEmpty()) {
			// 큐에서 익은 토마토를 꺼낸다.
			Info now = q.poll();
			
			// 꺼낸 토마토의 상하좌우 확인
			for(int i=0; i<4; i++) {
				int tempY = now.y + ymove[i];
				int tempX = now.x + xmove[i];
				
				// 범위를 벗어나지 말아야하고
				// 안익은 토마토일 경우에만 그 토마토로 탐색 (0 : 안익은 토마토)
				if(tempY >= 0 && tempY < N && tempX >= 0 && tempX < M && map[tempY][tempX] == 0) {
					// 익게할 거니깐 날짜 +1일
					int tempDay = now.days+1;
					
					// 익은 토마토로 카운트
					cnt++;
					
					// 큐에 넣어줌
					q.add(new Info(tempY, tempX, tempDay));
					
					// 날짜 갱신
					days = Math.max(tempDay, days);
					
					// 익은 토마토로 바꿔주기
					map[tempY][tempX] = 1;
				}
			}
		}
		
		// 카운트 반환
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		// 안익은 토마토 갯수
		int beforeCnt = 0;
		
		// 1 : 익은 토마토
		// 0 : 안익은 토마토
		// -1 : 토마토없음
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<M; j++) {
				int type = Integer.parseInt(st.nextToken());

				// 익은 토마토는 큐에 넣어주기
				if(type == 1) {
					q.add(new Info(i,j,0));
				}
				
				// 안익은 토마토는 따로 갯수를 세기
				else if(type == 0) {
					beforeCnt++;
				}
				map[i][j] = type;
				
			}
		}
		
		// 안익은 토마토가 처음부터 아예 없다면 0 출력 (문제 조건)
		if(beforeCnt == 0) {
			System.out.println(0);
		}
		else {
			int ripedTomatoCnt = BFS();
			
			// 토마토가 다 익지 않았으면 -1 출력(문제 조건)
			if((beforeCnt - ripedTomatoCnt) > 0) {
				System.out.println(-1);
			}
			// 토마토가 다 익었으면 걸린 날짜 출력
			else {
				System.out.println(days);
			}
		}
	}

}