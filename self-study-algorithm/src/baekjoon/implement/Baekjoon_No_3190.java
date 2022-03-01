package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_3190 {

	// 뱀
		public static class Snake{
			int x;
			int y;
			
			public Snake(int a, int b) {
				x=a;
				y=b;
			}
		}
		
		public static void main(String[] args) throws IOException {
			// TODO Auto-generated method stub
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			// N : 맵의 크기(N x N), K : 사과의 갯수
			int N, K;
			
			N = Integer.parseInt(br.readLine());
			K = Integer.parseInt(br.readLine());
			
			int[][] map = new int[N][N];
			
			for(int i=0; i<K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				// 7 : 사과, 1 : 뱀
				map[x-1][y-1] = 7;
			}
			
			// L : 뱀의 방향 변환 횟수
			int L = Integer.parseInt(br.readLine());
			Map<Integer,String> spinList = new HashMap<Integer,String>();
			
			for(int i=0; i<L; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int time = Integer.parseInt(st.nextToken());
				String d = st.nextToken();
				
				spinList.put(time, d);
			}
			
			// 상하좌우 이동
			int[] xmove = {-1,0,1,0};
			int[] ymove = {0,1,0,-1};
			
			Queue<Snake> q = new LinkedList<>();
			
			// 게임 진행 시간
			int totalTime = 0;
					
			// 뱀 위치 및 방향
			// 큐에 넣고
			q.offer(new Snake(0,0));
			// 맵에 표시(1:뱀, 7:사과)
			map[0][0] = 1;
			
			int direction = 1;
			int tempX = 0;
			int tempY = 0;
			
			while(true) {
				// 바라보는 방향의 한 칸 앞 확인
				tempX += xmove[direction];
				tempY += ymove[direction];
				
				// 시간 증가
				totalTime += 1;
							
				// 벽이랑 부딪힌 경우
				if(tempX >= N || tempX < 0 || tempY >= N || tempY < 0) {
					break;
				}
				
				// 몸이랑 부딪힌 경우
				if(map[tempX][tempY] == 1) {
					break;
				}
				
				// 사과를 만난 경우
				if(map[tempX][tempY] == 7) {
					// 머리 증가
					q.offer(new Snake(tempX, tempY));
					map[tempX][tempY] = 1;
				}
				// 사과가 아닌 경우
				else {
					// 머리 증가하고 꼬리 삭제
					q.offer(new Snake(tempX, tempY));
					map[tempX][tempY] = 1;
					
					Snake s = q.poll();
					map[s.x][s.y] = 0;
				}
				
				// 회전해야할 시간일 때
				if(spinList.containsKey(totalTime)) {
					String d = spinList.get(totalTime);
					
					// 왼쪽 방향인 경우
					if(d.equals("L")) {
						direction-=1;
						if(direction == -1) {
							direction=3;
						}
					}
					
					// 오른쪽 방향인 경우
					else {
						direction+=1;
						if(direction == 4) {
							direction=0;
						}
					}
				}
			}
			
			// 결과출력
			System.out.println(totalTime);
			
		}

}
