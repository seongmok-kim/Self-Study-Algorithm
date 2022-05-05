package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14499
public class Baekjoon_No_14499 {

	// N : 지도의 세로, M : 지도의 가로
	public static int N, M;
	
	// x,y : 주사위 좌표
	public static int x, y;
	
	// K : 명령의 갯수
	public static int K;
	
	// 지도
	public static int[][] map;
	
	public static int[] ymove = {0, 0, -1, 1};
	public static int[] xmove = {-1, 1, 0, 0};
	
	// 주사위 (처음엔 모든 면이 0)
	public static int[] dice = new int[7];
	
	public static void checkDice(char command) {
		int moveY = 0;
		int moveX = 0;
		
		int[] tempDice = new int[7];
		
		for(int i=1; i<dice.length; i++) {
			tempDice[i] = dice[i];
		}
		
		// 동쪽(오른쪽)
		if(command == '1') {
			moveX = 1;
			
			tempDice[6] = dice[4];
			tempDice[3] = dice[2];
			tempDice[4] = dice[3];
			tempDice[2] = dice[6];
		}
		// 서쪽(왼쪽)
		else if(command == '2') {
			moveX = -1;
			
			tempDice[6] = dice[2];
			tempDice[2] = dice[3];
			tempDice[3] = dice[4];
			tempDice[4] = dice[6];
		}
		// 북쪽(위쪽)
		else if(command == '3') {
			moveY = -1;
			
			tempDice[6] = dice[1];
			tempDice[1] = dice[3];
			tempDice[3] = dice[5];
			tempDice[5] = dice[6];
		}
		// 남쪽(아래쪽)
		else if(command == '4') {
			moveY = 1;
			
			tempDice[6] = dice[5];
			tempDice[3] = dice[1];
			tempDice[1] = dice[6];
			tempDice[5] = dice[3];
		}
		
		int tempY = y+moveY;
		int tempX = x+moveX;
		
		// 범위 체크
		if(tempX >= 0 && tempX < M && tempY >= 0 && tempY < N) {
			int mapNum = map[tempY][tempX];
			
			// 지도가 0이면 주사위 바닥면의 수가 지도로 복사
			if(mapNum == 0) {
				map[tempY][tempX] = tempDice[6];
			}
			// 지도가 0이 아니면 지도의 수가 주사위 바닥면으로 복사하고 지도는 0으로 변경
			else {
				tempDice[6] = mapNum;
				map[tempY][tempX] = 0;
			}
			
			// tempDice의 내용을 dice로 옮기기
			for(int i=0; i<dice.length; i++) {
				dice[i] = tempDice[i];
			}
			
			x= tempX;
			y= tempY;
			System.out.println(dice[3]);
		}
		// 범위 벗어나면 무시
		else {
			return;
		}
		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		String commandStr = br.readLine();
		// 1: 동쪽, 2: 서쪽, 3: 북쪽, 4: 남쪽 
		char[] commands = commandStr.replaceAll(" ", "").toCharArray();
		
		for(int i=0; i<commands.length; i++) {
			char command = commands[i];
			
			checkDice(command);
		}
	}

}