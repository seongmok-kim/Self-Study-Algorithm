package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_14503 {

	// N x M 크기의 맵
	// r,c : 현재 청소기의 위치. d : 청소기의 방향
	public static int N, M, r, c, d;
	
	// 맵
	public static int[][] map;
	
	// 순서대로 북, 동, 남, 서
	public static int[] amove = {-1,0,1,0};
	public static int[] bmove = {0,1,0,-1};
	
	// 결과
	public static int result = 0;
	
	// DFS탐색
	public static void DFS(int r, int c, int d, int cnt) {
		// 0 : 빈 방
		// 1 : 벽
		// 2 : 청소완료한 방 
		
		// 청소체크
		map[r][c] = 2;
		
		// 결과값 갱신
		result = Math.max(result, cnt);
		
		int temp = d;
		
		// 네 방향의 방이 모두 청소가 되어있는 지 체크
		boolean check = false;
		
		// 상하좌우체크
		for(int i=0; i<4; i++) {
			// 좌측부터 체크(현재 바라보고 있는 방향 기준 왼쪽부터 탐색)
			temp = (temp+3)%4;
			
			int tempA = r+amove[temp];
			int tempB = c+bmove[temp];
			
			// 범위 이내이고, 빈 방이라면 DFS 탐색진행
			if(tempA > 0 && tempA < N && tempB > 0 && tempB < M) {
				if(map[tempA][tempB] == 0) {
					DFS(tempA, tempB, temp, cnt+1);
					check = true;
					break;
				}
			}
		}
		
		// 위에서 빈 방을 찾지 못한 경우
		if(check == false) {
			// 바라보고 있는 방향의 뒤로 이동
			int reverse = (d+2)%4;
			
			int reverseA = r+amove[reverse];
			int reverseB = c+bmove[reverse];
			
			// 범위 이내이고 벽이 아니라면 뒤로 이동(Cnt 증가 X)
			if(reverseA > 0 && reverseA < N && reverseB > 0 && reverseB < M) {
				if(map[reverseA][reverseB] != 1) {
					DFS(reverseA, reverseB, d, cnt);
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		// DFS탐색
		DFS(r,c,d,1);
		
		// 청소한 방의 수 출력
		System.out.println(result);
	}

}
