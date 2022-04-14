package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_17144 {

	public static int[][] originMap;
	
	// R : 세로
	// C : 가로
	// T : 초
	public static int R, C, T;
	
	// 공기청정기 위치 
	public static int UP = -1, DOWN = -1;
	
	// 좌표의 정보를 가지는 Pos 클래스 
	public static class Pos{
		int y;
		int x;
		
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	// 상하좌우 확인에 사용될 배열
	public static int[] ymove = {1,0,-1,0};
	public static int[] xmove = {0,-1,0,1};
	
	// 미세먼지 확산
	public static void spread() {
		Queue<Pos> q = new LinkedList<>();
		
		int[][] tempMap = new int[R][C];
		
		// 미세먼지 위치 기록
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				int amount = originMap[i][j];
				
				if(amount != 0 && amount != -1) {
					q.offer(new Pos(i,j));
				}
			}
		}
		
		// 큐가 빌 때까지 반복
		while(!q.isEmpty()) {
			Pos now = q.poll();
			
			int amount = originMap[now.y][now.x];
			int divided = amount / 5;
			// 확산
			if(divided > 0) {
				int cnt = 0;
				
				// 상하좌우 확산
				for(int i=0; i<4; i++) {
					int tempY = now.y + ymove[i];
					int tempX = now.x + xmove[i];
					
					// 범위 이내라면
					if(tempY >= 0 && tempY < R && tempX >= 0 && tempX < C && originMap[tempY][tempX] != -1 ) {
						tempMap[tempY][tempX] += divided;
						cnt++;
					}
				}
				
				// 남은 미세먼지 양 계산
				tempMap[now.y][now.x] += amount - (divided * cnt);
			}
			else {
				tempMap[now.y][now.x] += amount;
			}
		}
		
		// 공기청정기 위치 넣기
		tempMap[UP][0] = -1;
		tempMap[DOWN][0] = -1;
		
		// tempMap -> originMap 으로 복사
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				originMap[i][j] = tempMap[i][j];
			}
		}
	}
	
	// 공기청정기 작동
	public static void clean() {
		int[][] tempMap = new int[R][C];
		
		// 위쪽 공기청정기
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(originMap[i][j] == -1) {
					tempMap[i][j] = -1;
					continue;
				}
				
				if(i <= UP) {
					if(i == UP && j > 0 && j < C-1) {
						tempMap[i][j+1] = originMap[i][j];
					}
					else if(j == C-1 && i>0) {
						tempMap[i-1][j] = originMap[i][j];
					}
					else if(i == 0 && j > 0 && j < C) {
						tempMap[i][j-1] = originMap[i][j];
					}
					else if(j == 0) {
						if(originMap[i+1][j] != -1) {
							tempMap[i+1][j] = originMap[i][j];
						}
					}
					else {
						tempMap[i][j] = originMap[i][j];
					}
					
				}
				else {
					if(i == DOWN && j > 0 && j < C-1) {
						tempMap[i][j+1] = originMap[i][j];
					}
					else if(j == C-1 && i<R-1) {
						tempMap[i+1][j] = originMap[i][j];
					}
					else if(i == R-1 && j > 0 && j < C) {
						tempMap[i][j-1] = originMap[i][j];
					}
					else if(j == 0) {
						if(originMap[i-1][j] != -1) {
							tempMap[i-1][j] = originMap[i][j];
						}
					}
					else {
						tempMap[i][j] = originMap[i][j];
					}
					
				}
			}
		}
		
		// tempMap -> originMap 으로 복사
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				originMap[i][j] = tempMap[i][j];
			}
		}
	}
	
	// 미세먼지의 합 계산
	public static int calc() {
		int sum = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(originMap[i][j] != -1) {
					sum += originMap[i][j];
				}
			}
		}
		
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		originMap = new int[R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<C; j++) {
				int amount = Integer.parseInt(st.nextToken());
				
				originMap[i][j] = amount;
				
				// 공기청정기 위치 기록
				if(amount == -1) {
					if(UP == -1) {
						UP = i;
					}
					else {
						DOWN = i;
					}
				}
			}
		}
		
		br.close();
		
		// 주어진 초만큼 반복
		for(int i=0; i<T; i++) {
			// 1. 미세먼지 확산
			spread();
			
			// 2. 공기청정기 작동
			clean();
		}
		
		// 미세먼지의 총 합 구하기
		int result = calc();
		
		// 결과 출력
		System.out.println(result);
	}

}