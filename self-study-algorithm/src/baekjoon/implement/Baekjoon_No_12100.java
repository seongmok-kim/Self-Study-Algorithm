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

	// 5번의 이동으로 나올 수 있는 가장 큰 값
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
		
		// map의 내용물을 tempMap으로 복사
		int[][] tempMap = new int[mapSize][mapSize];
		for(int i=0; i<mapSize; i++) {
			tempMap[i] = map[i].clone();
		}

		// DFS 이용
		DFS(0, tempMap);
		
		// 결과 출력
		System.out.println(maximum);
	}
	
	// DFS를 이용한 풀이
	// count: 현재 재귀함수를 호출한 횟수, tempMap : 수정된 map
	public static void DFS(int count, int[][] tempMap) {
		
		// DFS를 5번 호출한 경우, 최댓값 확인 및 갱신
		if(count >= COUNT) {
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

		// 계산 체크 변수 (기존에 합쳐진 칸은 다시 합쳐질 수 없다는 규칙에 사용 예정)
		boolean[][] visited = new boolean[mapSize][mapSize];
		
		// tempMap을 기준으로 왼쪽,오른쪽,위,아래 이동한 결과 값을 담게될 배열
		// 0: 왼쪽, 1: 오른쪽, 2: 위, 3: 아래
		int[][][] changedMap = new int[4][mapSize][mapSize];
		
		
		// 1. LEFT
		visited = new boolean[mapSize][mapSize];		// 계산체크 변수 초기화

		for(int i=0; i<mapSize; i++) {
			int position = 0;		// 새로운 맵에 넣을 위치
			for(int j=0; j<mapSize; j++) {
				
				// 0이 아니라면,
				if(tempMap[i][j] != 0) {
					// position이 0. 즉, 첫 번째의 경우에는 확인할 필요 없이 바로 넣어줌. (어처피 처음이기에 합칠 대상도 없음)
					if(position == 0) {
						changedMap[0][i][position] = tempMap[i][j];
					}
					// position이 1 이상인 경우
					else if(position > 0) {
						// 이전 위치의 값과 지금 확인하고 있는 값이 같고, 이전위치에서 계산을 한 적이 없는 경우
						if(changedMap[0][i][position-1] == tempMap[i][j] && visited[i][position-1] == false) {
							position--;		// 현재 위치를 이전 위치로 옮기고
							
							changedMap[0][i][position] = (changedMap[0][i][position] * 2);		// 이전 위치의 값을 2배로 바꿈
							
							visited[i][position] = true;	// 계산 체크
						}
						else {
							// 다른 경우 값을 넣어줌
							changedMap[0][i][position] = tempMap[i][j];
						}
					}
					
					// position 1 추가
					position++;
				}
			}
		}
		// 변경된 map 과 함께 DFS 호출 
		DFS(count+1, changedMap[0]);
		
		// 오른쪽, 위, 아래도 위와 같은 방식을 사용함. 단지, 탐색하는 방법이 다르다.
		
		// 2. RIGHT
		visited = new boolean[mapSize][mapSize];
		for(int i=0; i<mapSize; i++) {
			int position = mapSize-1;
			for(int j=mapSize - 1; j >= 0; j--) {
				if(tempMap[i][j] != 0) {
					if(position == mapSize-1) {
						changedMap[1][i][position] = tempMap[i][j];
					}
					else {
						if(changedMap[1][i][position+1] == tempMap[i][j] && visited[i][position+1] == false) {
							position++;
							changedMap[1][i][position] = (changedMap[1][i][position] * 2);
							
							visited[i][position] = true;
						}
						else {
							changedMap[1][i][position] = tempMap[i][j];
						}
					}
					position--;
				}
			}
		}
		DFS(count+1, changedMap[1]);
		
		
		// 3. UP
		visited = new boolean[mapSize][mapSize];
		for(int i=0; i<mapSize; i++) {
			int position = 0;
			for(int j=0; j < mapSize; j++) {
				if(tempMap[j][i] != 0) {
					if(position == 0) {
						changedMap[2][position][i] = tempMap[j][i];
					}
					else {
						if(changedMap[2][position-1][i] == tempMap[j][i] && visited[position-1][i] == false) {
							position--;
							changedMap[2][position][i] = (changedMap[2][position][i] * 2);
							
							visited[position][i] = true;
						}
						else {
							changedMap[2][position][i] = tempMap[j][i];
						}
					}
					position++;
				}
			}
		}
		DFS(count+1, changedMap[2]);
		
		
		// 4. DOWN
		visited = new boolean[mapSize][mapSize];
		for(int i=0; i<mapSize; i++) {
			int position = mapSize-1;
			for(int j=mapSize-1; j >=0 ; j--) {
				if(tempMap[j][i] != 0) {
					if(position == (mapSize-1)) {
						changedMap[3][position][i] = tempMap[j][i];
					}
					else {
						if(changedMap[3][position+1][i] == tempMap[j][i] && visited[position+1][i] == false) {
							position++;
							changedMap[3][position][i] = (changedMap[3][position][i] * 2);
							
							visited[position][i] = true;
						}
						else {
							changedMap[3][position][i] = tempMap[j][i];
						}
					}
					position--;
				}
			}
		}
		DFS(count+1, changedMap[3]);
	}
	
}