package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_No_16234 {

	public static int N, L, R;
	
	public static int[][] map;
	public static boolean[][] visited;
	
	public static int[][] sector;
	public static int sectorCount = 0;
	public static boolean flag = false;
	
	public static int[] xmove = {1,-1,0,0};
	public static int[] ymove = {0,0,1,-1};
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		sector = new int[N][N];
				
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		
		// 무한 반복
		while(true) {
			sectorCount = 0;
			visited = new boolean[N][N];
			sector = new int[N][N];
			flag = false;
			
			// 1. 구역 찾기
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					
					if(visited[i][j] == false) {
						sectorCount++;
						DFS(i,j);
					}
				}
			}
			
			// 국경선이 열린 곳이 없으면 반복 종료
			if(flag == false) {
				break;
			}
			cnt++;
			
			// 2. 구역별 인구 수 배분 준비
			// 배열 형식 : tempSum[구역][0:구역인구수합계, 1:구역을 이루는나라의 수, 2:평균값]
			int[][] tempSum = new int[sectorCount+1][3];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					
					int nowSector = sector[i][j];
					if(nowSector != 0) {
						tempSum[nowSector][0] += map[i][j];
						tempSum[nowSector][1]++;
					}
				}
			}
			
			// 3. 각 구역별 인구 평균 값 구하기
			for(int i=0; i<tempSum.length; i++) {
				if(tempSum[i][0] != 0) {
					tempSum[i][2] = tempSum[i][0] / tempSum[i][1];
				}
			}
			
			// 4. 해당 구역에 평균 값 넣어주기
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					
					int nowSector = sector[i][j];
					if(nowSector != 0) {
						map[i][j] = tempSum[nowSector][2];
					}
				}
			}
		}
		
		System.out.println(cnt);	// 결과 출력
	}
	
	// DFS를 이용해서 구역 찾기
	public static void DFS(int y, int x) {
		visited[y][x] = true;		// 해당 칸에 들어와야만 방문 체크함.
		
		for(int k=0; k<4; k++) {
			int tempY = y+ymove[k];
			int tempX = x+xmove[k];
			
			
			// 맵의 범위를 벗어났다면
			if(tempY < 0 || tempY >= N || tempX < 0 || tempX >= N) {
				continue;
			}
			else {
				// 두 나라의 차이를 절댓값으로 구함
				int difference = Math.abs(map[y][x] - map[tempY][tempX]);
				
				// 제시된 범위 내의 차이 && 방문하지 않은 곳이라면
				if(difference >= L && difference <= R && visited[tempY][tempX] == false) {
					
					// 구역을 정해주고
					sector[y][x] = sectorCount;
					sector[tempY][tempX] = sectorCount;
					
					// flag값 true로 설정(국경선이 열린 나라가 있다)
					flag = true;
					
					// 재귀호출
					DFS(tempY, tempX);
				}
			}
		}
	}
}