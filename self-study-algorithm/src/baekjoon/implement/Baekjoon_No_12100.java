package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Link : https://www.acmicpc.net/problem/12100
public class Baekjoon_No_12100 {

	public static int[][] map;
	
	public static int mapSize;
	
	public static final int COUNT = 5;
	
	public static int maximum = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException{
		
		// 맵 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		mapSize = Integer.parseInt(br.readLine());
		map = new int[mapSize][mapSize];
		
		for(int i=0; i<mapSize; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<mapSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] tempMap = new int[mapSize][mapSize];
		for(int i=0; i<mapSize; i++) {
			tempMap[i] = map[i].clone();
		}

		
		DFS(0, tempMap);
		
		System.out.println(maximum);
	}
	
	public static void DFS(int count, int[][] tempMap) {
		if(count > COUNT) {
			int max = 0;
			for(int i=0; i<mapSize; i++) {
				
				for(int j=0; j<mapSize; j++) {
					if(max < tempMap[i][j]) {
						max = tempMap[i][j];
					}
				}
			}
			
			if(max > maximum) {
				maximum = max;
			}
			
			return;
		}
		
		int[][][] changedMap = new int[4][mapSize][mapSize];
		
		// 왼쪽으로 이동
		for(int i=0; i<mapSize; i++) {
			boolean isSum = false;
			int position = 0;
			for(int j=0; j<mapSize; j++) {
				if(tempMap[i][j] != 0) {
					if(position == 0) {
						changedMap[0][i][position] = tempMap[i][j];
					}
					else if(position > 0) {
						if(changedMap[0][i][position-1] == tempMap[i][j] && isSum == false) {
							position--;
							changedMap[0][i][position] = (changedMap[0][i][position] * 2);
							
							isSum = true;
						}
						else {
							changedMap[0][i][position] = tempMap[i][j];
							isSum = false;
						}
					}
					position++;
				}
			}
		}
		DFS(count+1, changedMap[0]);
		
		// 오른쪽으로 이동
		for(int i=0; i<mapSize; i++) {
			boolean isSum = false;
			int position = mapSize-1;
			for(int j=mapSize - 1; j >= 0; j--) {
				if(tempMap[i][j] != 0) {
					if(position == mapSize-1) {
						changedMap[1][i][position] = tempMap[i][j];
					}
					else {
						if(changedMap[1][i][position+1] == tempMap[i][j] && isSum == false) {
							position++;
							changedMap[1][i][position] = (changedMap[1][i][position] * 2);
							
							isSum = true;
						}
						else {
							changedMap[1][i][position] = tempMap[i][j];
							isSum = false;
						}
					}
					position--;
				}
			}
		}
		DFS(count+1, changedMap[1]);
		
		// 위로 이동
		for(int i=0; i<mapSize; i++) {
			boolean isSum = false;
			int position = 0;
			for(int j=0; j < mapSize; j++) {
				if(tempMap[j][i] != 0) {
					if(position == 0) {
						changedMap[2][position][i] = tempMap[j][i];
					}
					else {
						if(changedMap[2][position-1][i] == tempMap[j][i] && isSum == false) {
							position--;
							changedMap[2][position][i] = (changedMap[2][position][i] * 2);
							
							isSum = true;
						}
						else {
							changedMap[2][position][i] = tempMap[j][i];
							isSum = false;
						}
					}
					position++;
				}
			}
		}
		DFS(count+1, changedMap[2]);
		
		// 아래로 이동
		for(int i=mapSize-1; i>=0; i--) {
			boolean isSum = false;
			int position = mapSize-1;
			for(int j=0; j < mapSize; j++) {
				if(tempMap[j][i] != 0) {
					if(position == (mapSize-1)) {
						changedMap[3][position][i] = tempMap[j][i];
					}
					else {
						if(changedMap[3][position+1][i] == tempMap[j][i] && isSum == false) {
							position++;
							changedMap[3][position][i] = (changedMap[3][position][i] * 2);
							
							isSum = true;
						}
						else {
							changedMap[3][position][i] = tempMap[j][i];
							isSum = false;
						}
					}
					position--;
				}
			}
		}
		DFS(count+1, changedMap[2]);
	}

}
