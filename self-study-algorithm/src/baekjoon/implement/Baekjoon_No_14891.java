package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 맞닿은 톱니바퀴의 톱니가 서로 다르면 거꾸로 회전, 같으면 동작X
 * 회전하기 전에 체크해야함.
 * 
 */

// Link : https://www.acmicpc.net/problem/14891
public class Baekjoon_No_14891 {

	public static int[][] gears = new int[5][8];
	
	// 회전
	public static void spin(int num, boolean type) {
		int[] temp = new int[8];
		
		for(int i=0; i<8; i++) {
			temp[i] = gears[num][i];
		}
		
		// 정방향 회전
		if(type == true) {
			for(int i=1; i<8; i++) {
				gears[num][i] = temp[i-1];
			}
			gears[num][0] = temp[7];
		}
		// 역방향 회전
		else {
			for(int i=0; i<7; i++) {
				gears[num][i] = temp[i+1];
			}
			gears[num][7] = temp[0];
		}
	}
	
	public static class SpinInfo{
		int index;
		int direction;
		
		public SpinInfo(int index, int d) {
			this.index = index;
			this.direction = d;
		}
	}
	
	public static void spinGear(int target, int direction) {
		// 회전 예정 배열
		int[][] spinArr = new int[5][1];
		
		// 큐 이용
		Queue<SpinInfo> q = new LinkedList<>();
		
		// 큐에 회전할 톱니바퀴 인덱스와 회전 방향 넣기
		q.offer(new SpinInfo(target, direction));
		
		// 방문 체크 변수
		boolean[] visited = new boolean[5];
		
		// 큐가 빌 때까지 반복
		while(!q.isEmpty()) {
			// 큐에서 꺼내고
			SpinInfo spinInfo = q.poll();
			int now = spinInfo.index;
			int nowD = spinInfo.direction;
			
			// 회전 예정 배열에 현재 인덱스 등록
			spinArr[now][0] = nowD;
			visited[now] = true;
			
			// 현재 톱니바퀴 기준 우측 톱니바퀴 확인
			if(now + 1 <= 4 && now + 1 >= 1) {
				if(gears[now][2] != gears[now+1][6] && visited[now+1] == false) {
					if(nowD > 0) {
						q.offer(new SpinInfo(now+1, -1));
					}else {
						q.offer(new SpinInfo(now+1, 1));
					}
				}
			}
			// 현재 톱니바퀴 기준 좌측 톱니바퀴 확인
			if(now - 1 >= 1 && now - 1 <= 4) {
				if(gears[now-1][2] != gears[now][6] && visited[now-1] == false) {
					if(nowD > 0) {
						q.offer(new SpinInfo(now-1, -1));
					}else {
						q.offer(new SpinInfo(now-1, 1));
					}
				}
			}
		}
		
		// 회전 예정 배열에서 등록된 것만 회전시키기
		for(int i=1; i<5; i++) {
			if(spinArr[i][0] == 1) {
				spin(i, true);
			}
			else if(spinArr[i][0] == -1) {
				spin(i, false);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 4개의 톱니바퀴 입력
		for(int i=1; i<5; i++) {
			String temp = br.readLine();
			
			char[] tempArr = temp.toCharArray();
			for(int j=0; j<tempArr.length; j++) {
				gears[i][j] = (tempArr[j] - '0');
			}
		}

		// 회전 횟수 입력
		int rotationCount = Integer.parseInt(br.readLine());
		
		// 회전 대상 및 방향 입력
		int[][] rotationTargetArr = new int[rotationCount][2];
		for(int i=0; i<rotationCount; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int target = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			
			rotationTargetArr[i][0] = target;
			rotationTargetArr[i][1] = direction;
		}
		
		// 회전 횟수만큼 회전 시키기
		for(int i=0; i<rotationCount; i++) {
			spinGear(rotationTargetArr[i][0], rotationTargetArr[i][1]);
		}
		
		// 결과 계산
		/**
		 1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
		 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
		 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
		 4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점
		**/
		int result = 0;
		for(int i=1; i<5; i++) {
			if(gears[i][0] == 1) {
				int temp = (int)Math.pow(2, i-1);
				result+=temp;
			}
		}
		
		System.out.println(result);
	}

}