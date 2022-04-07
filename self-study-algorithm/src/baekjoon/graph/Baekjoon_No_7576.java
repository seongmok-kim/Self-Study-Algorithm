package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_7576 {

	public static class Info{
		int y;
		int x;
		int days;
		
		public Info(int y, int x, int days) {
			this.y = y;
			this.x = x;
			this.days = days;
		}
	}
	
	public static Queue<Info> q = new LinkedList<>();
	
	public static int M, N;
	
	public static int[][] map;
	
	public static int[] ymove = {-1,0,1,0};
	public static int[] xmove = {0,1,0,-1};
	
	public static int days = 0;
	public static int BFS() {
		int cnt = 0;
		
		while(!q.isEmpty()) {
			Info now = q.poll();
			
			for(int i=0; i<4; i++) {
				int tempY = now.y + ymove[i];
				int tempX = now.x + xmove[i];
				
				if(tempY >= 0 && tempY < N && tempX >= 0 && tempX < M && map[tempY][tempX] == 0) {
					int tempDay = now.days+1;
					cnt++;
					
					q.add(new Info(tempY, tempX, tempDay));
					
					days = Math.max(tempDay, days);
					
					map[tempY][tempX] = 1;
				}
			}
		}
		
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
				
				if(type == 1) {
					q.add(new Info(i,j,0));
				}
				else if(type == 0) {
					beforeCnt++;
				}
				map[i][j] = type;
				
			}
		}
		
		if(beforeCnt == 0) {
			System.out.println(0);
		}
		else {
			int ripedTomatoCnt = BFS();
			
			if((beforeCnt - ripedTomatoCnt) > 0) {
				System.out.println(-1);
			}
			else {
				System.out.println(days);
			}
		}
	}

}