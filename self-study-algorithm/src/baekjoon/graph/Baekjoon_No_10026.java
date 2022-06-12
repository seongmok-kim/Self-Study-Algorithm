package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Link: https://www.acmicpc.net/problem/10026
public class Baekjoon_No_10026 {
	
	// 상하좌우에 사용될 배열
	public static int[] xmove = {-1,1,0,0};
	public static int[] ymove = {0,0,-1,1};
	
	
	public static int N;			// 맵 크기
	public static char[][] map;		// 맵 변수
	
	public static boolean[][] visited;	// 방문 체크 변수
	
	public static int[][] count = new int[2][3];	// 구역 저장 변수 
	// count[0][0]: 정상인 시점 R구역 갯수, count[0][1]: 정상인 시점 G구역 갯수, count[0][2]: 정상인 시점 B구역 갯수
	// count[1][0]: 정상인 시점 R구역, G구역 갯수, count[1][1]: 정상인 시점 B구역 갯수
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		
		for(int i=0; i<N; i++) {
			String temp = br.readLine();
			char[] tempArr = temp.toCharArray();
			
			map[i] = tempArr;
		}
		
		// 정상인 시점 구역 갯수 구하기
		visited = new boolean[N][N];		// 방문체크변수 초기화
		
		// 완전탐색 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				// 방문 안한 곳이라면
				if(visited[i][j] == false) {
					visited[i][j] = true;		// 방문 체크 후
					
					// 색에 맞게 구역 갯수 추가 후
					char color = map[i][j];
					if(color == 'R') {
						count[0][0]++;
					}
					else if(color == 'G') {
						count[0][1]++;
					}
					else {
						count[0][2]++;
					}
					
					// 같은 구역 방문처리하러 함수 호출
					DFS(i,j,color,true);
				}
			}
		}
		
		// 적록색약인 시점 구역 갯수 구하기
		visited = new boolean[N][N];		// 방문체크변수 초기화
		
		// 완전탐색 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				// 방문 안한 곳이라면
				if(visited[i][j] == false) {
					visited[i][j] = true;		// 방문 체크 후
					
					// 색에 맞게 구역 갯수 추가 후
					char color = map[i][j];
					if(color == 'R' || color == 'G') {
						count[1][0]++;
					}
					else {
						count[1][1]++;
					}
					
					// 같은 구역 방문처리하러 함수 호출
					DFS(i,j,color,false);
				}
			}
		}
		
		// 결과 출력
		int result1 = count[0][0] + count[0][1] + count[0][2];
		int result2 = count[1][0] + count[1][1];
		
		System.out.println(result1 + " " + result2);
	}
	
	// 영역 구하기
	public static void DFS(int y, int x, char color, boolean isNormal) {
		// 상하좌우 밀접한 칸 확인
		for(int i=0; i<4; i++) {
			int tempX = x+xmove[i];
			int tempY = y+ymove[i];
			
			// 범위 내에 있고
			if(!(tempX < 0 || tempX >= N || tempY < 0 || tempY >= N)) {
				
				// 방문한 곳이 없는 곳일 경우
				if(visited[tempY][tempX] == false) {
					// 정상인일 때
					if(isNormal == true) {
						
						// 이전칸과 같은 구역일 때만
						if(map[tempY][tempX] == color) {
							// 방문 체크 후
							visited[tempY][tempX] = true;
							
							// DFS 재호출
							DFS(tempY, tempX, color, isNormal);
						}
					}
					// 적록색약인일 경우에
					else {
						// 적록색약인의 경우 R과 G를 구분 못하므로 아래 처럼 처리
						if(color == 'R' || color == 'G') {
							if(map[tempY][tempX] == 'R' || map[tempY][tempX] == 'G') {
								// 방문 체크 후
								visited[tempY][tempX] = true;
								
								// DFS 재호출
								DFS(tempY, tempX, map[tempY][tempX], isNormal);
							}
						}
						// 파란색은 구분 할 수 있으므로 아래 처럼 처리
						else if(color == 'B'){
							if(map[tempY][tempX] == color) {
								// 방문 체크 후
								visited[tempY][tempX] = true;
								
								// DFS 재호출
								DFS(tempY, tempX, color, isNormal);
							}
						}
					}
				}
			}
		}
	}
}