package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Link: https://www.acmicpc.net/problem/15662
public class Baekjoon_No_15662 {
	// T: 톱니바퀴의 갯수, K: 회전 횟수
	public static int T, K;
	
	// 톱니바퀴의 상태
	public static int[][] gearArr;
	
	// 방문 체크에 사용될 배열
	public static boolean[] visited;
	
	// 회전해야할 톱니바퀴
	public static int[] isRotate;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		gearArr = new int[T][8];
		
		for(int i=0; i<T; i++) {
			String temp = br.readLine();
			String[] tempArr = temp.split("");
			
			for(int j=0; j<8; j++) {
				gearArr[i][j] = Integer.parseInt(tempArr[j]);
			}
		}
		
		K = Integer.parseInt(br.readLine());
		
		// 회전시키기
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int target = (Integer.parseInt(st.nextToken()) - 1);		// 회전시킬 톱니바퀴
			int direction = Integer.parseInt(st.nextToken());			// 회전시킬 방향
			
			// 방문체크 및 회전 예정 배열 초기화
			visited = new boolean[T];
			isRotate = new int[T];
			
			// 방문 체크 후 Check 함수 호출
			visited[target] = true;
			Check(target, direction);
			
			// 실제로 회전시키기
			Rotate();
		}

		
		// 결과 출력
		int result = 0;
		for(int i=0; i<T; i++) {
			if(gearArr[i][0] == 1) {
				result++;
			}
		}
		System.out.println(result);
	}
	
	// 회전해야할 톱니바퀴 찾기
	public static void Check(int now, int direction) {
		// 회전 예정 배열에 회전 방향 넣고
		isRotate[now] = direction;
		
		// 현재 톱니바퀴 좌우 확인
		int left = now - 1;
		int right = now + 1;

		// 좌측 톱니바퀴가 범위 안에 있고
		if(left >= 0 && left < T) {
			// 서로 맞물린 부분이 서로 다르고 && 방문한 적이 없는 톱니바퀴라면
			if(gearArr[now][6] != gearArr[left][2] && visited[left] == false) {
				
				// 현재 회전 방향과 반대로 회전 
				int newDirection = direction * -1;
				// 방문체크
				visited[left] = true;
				
				Check(left, newDirection);
			}
		}
		
		// 우측 톱니바퀴가 범위 안에 있고
		if(right >=0 && right < T) {
			// 서로 맞물린 부분이 서로 다르고 && 방문한 적이 없는 톱니바퀴라면
			if(gearArr[now][2] != gearArr[right][6] && visited[right] == false) {
				
				// 현재 회전 방향과 반대로 회전 
				int newDirection = direction * -1;
				// 방문체크
				visited[right] = true;
				
				Check(right, newDirection);
			}
		}
	}
	
	// 회전 예정 배열을 토대로 회전 시키기
	public static void Rotate() {
		for(int i=0; i<T; i++) {
			// 정방향 회전
			if(isRotate[i] == 1) {
				int temp = gearArr[i][7];
				for(int j=7; j>0; j--) {
					gearArr[i][j] = gearArr[i][j-1];
				}
				gearArr[i][0] = temp;
			}
			// 역방향 회전
			else if(isRotate[i] == -1){
				int temp = gearArr[i][0];
				for(int j=0; j<7; j++) {
					gearArr[i][j] = gearArr[i][j+1];
				}
				gearArr[i][7] = temp;
			}
		}
	}

}