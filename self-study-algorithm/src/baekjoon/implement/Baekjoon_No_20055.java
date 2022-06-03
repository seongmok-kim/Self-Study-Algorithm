package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Link: https://www.acmicpc.net/problem/20055
/*
 1.벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
 2.가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
   * 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
 3.올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
 
 로봇을 올리는 위치에 올리거나 로봇이 어떤 칸으로 이동하면 그 칸의 내구도는 즉시 1만큼 감소한다.
 */
public class Baekjoon_No_20055 {
	// N: 벨트의 길이, K: 내구도가 0인 칸의 갯수
	public static int N, K;
	
	// 각 칸의 내구도 배열
	public static int[] mainArr;
	
	// 각 칸의 로봇 여부
	public static boolean[] robotArr;
	
	public static Queue<Robot> q = new LinkedList<>();
	
	public static class Pos{
		int idx;
		int du;
		
		public Pos(int idx, int du) {
			this.idx = idx;
			this.du = du;
		}
	}
	
	public static class Robot{
		int idx;
		
		public Robot(int idx) {
			this.idx = idx;
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int length = (2*N);
		mainArr = new int[length];
		robotArr = new boolean[N];	// 로봇을 놓을 수 있는 위치는 컨베이어벨트의 위쪽만 가능하므로 N길이만큼만 선언
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<length; i++) {
			mainArr[i] = Integer.parseInt(st.nextToken());
		}
		
		int page = 0;
		while(true) {
			page++;
			
			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			
			// 벨트를 한 칸씩 앞으로 이동
			int tempMain = mainArr[mainArr.length-1];
			for(int i=mainArr.length-1; i>0; i--) {
				mainArr[i] = mainArr[i-1];
			}
			mainArr[0] = tempMain;
			
			// 로봇도 한 칸씩 앞으로 이동
			for(int i=robotArr.length-1; i>0; i--) {
				robotArr[i] = robotArr[i-1];
			}
			robotArr[0] = false;
			
			// 로봇이 내리는 위치에 있으면 로봇 빼기
			if(robotArr[N-1] == true) {
				robotArr[N-1] = false;
			}
			
			
			// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다. 
			// (로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.)
			for(int i=N-2; i>=0; i--) {
				boolean isRobot = robotArr[i];
				if(isRobot) {
					if(robotArr[i+1] == false && mainArr[i+1] > 0) {
						robotArr[i] = false;
						robotArr[i+1] = true;
						mainArr[i+1]-=1;
						
						// 로봇이 내리는 위치에 있으면 로봇 빼기
						if(robotArr[N-1] == true) {
							robotArr[N-1] = false;
						}
					}
				}
			}
			
			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if(mainArr[0] > 0 && robotArr[0] == false) {
				robotArr[0] = true;
				mainArr[0] -= 1;
			}
			
			// 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
			int cnt = 0;
			for(int i=0; i<mainArr.length; i++) {
				if(mainArr[i] == 0) {
					cnt++;
				}
			}
			
			if(cnt >= K) {
				break;
			}
		}
		
		System.out.println(page);
		
	}

}