package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_2573 {

	// N : 행의 개수
	// M : 열의 개수
	public static int N,M;
	
	// MAP
	public static int[][] map;
	
	// year : 연도
	// ice : 남아있는 빙산의 수
	// iceGroup : 빙산 그룹의 수(2 그룹 이상이 되어야함)
	public static int year=0, ice=0, iceGroup=0 ;
	
	// 상하좌우 확인 시 이용할 배열
	public static int[] nmove = {1,-1,0,0};
	public static int[] mmove = {0,0,-1,1};
	
	// 바닷물 위치를 모아둔 리스트
	public static ArrayList<Node> waters = new ArrayList<Node>();
	
	// DFS용 방문 체크 변수
	public static boolean[][] visited;
	
	// 빙하 녹이기(1년이 지나면 바닷물과 근접한 빙하가 녹음)
	public static void BFS() {
		// 1년 경과
		year++;
		
		Queue<Node> q = new LinkedList<>();

		int[][] visited = new int[N][M];
		
		// 바닷물의 위치 큐에 넣기
		for(int i=0; i<waters.size();i++) {
			Node node = waters.get(i);
			
			visited[node.n][node.m] += 1;
			q.offer(node);
			
		}
		
		// 큐가 빌 때까지 반복
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			int n = node.n;
			int m = node.m;
			
			// 바닷물의 상하좌우 확인
			for(int i=0; i<4; i++) {
				int tempN = n+nmove[i];
				int tempM = m+mmove[i];
				
				// 범위 내에
				if(tempN>=0 && tempN < N && tempM >= 0 && tempM < M) {
					// 빙하가 있다면
					if(map[tempN][tempM] != 0) {
						// 빙하의 높이를 1 줄이고
						map[tempN][tempM] -= 1;
						
						// 방금 줄인 빙하의 높이가 0. 즉, 다 녹은 경우 
						if(map[tempN][tempM] == 0) {
							// 빙하의 갯수를 1 빼주고
							ice--;
							
							// 바닷물 리스트에 추가
							waters.add(new Node(tempN, tempM));
						}
					}
				}
			}
		}
	}
	
	// 빙하가 하나로 이루어져있는 지 확인
	public static void DFS(int n, int m) {
		// 현재 빙하를 기준으로 상하좌우 확인
		for(int i=0; i<4; i++) {
			int tempN = n+nmove[i];
			int tempM = m+mmove[i];
			
			// 범위 이내라면
			if(tempN>=0 && tempN < N && tempM >= 0 && tempM < M) {
				
				// 빙하이고, 방문을 아직 안한 곳이라면 탐색 진행
				if(map[tempN][tempM] != 0 && visited[tempN][tempM] == false) {
					visited[tempN][tempM] = true;
					DFS(tempN, tempM);
				}
			}
		}
	}
	
	// 노드 클래스 선언
	public static class Node{
		int n;
		int m;
		
		public Node(int a, int b) {
			n=a;
			m=b;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		// 맵 설정
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				
				// 바닷물인 경우 바닷물 리스트에 추가
				if(num==0) {
					waters.add(new Node(i,j));
				}
				// 빙하인 경우 빙하의 갯수에 추가 
				else {
					ice++;
				}
			}
		}
		
		// 빙하가 다 녹을 때까지(빙하의 갯수가 0이 될 때까지)
		while(ice != 0) {
			
			// 빙하 녹이기
			BFS();
			
			
			// 빙하의 그룹 확인
			
			// 방문체크변수 및 빙하의 그룹 변수 초기화 
			visited = new boolean[N][M];
			iceGroup = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=0;j<M;j++) {
					
					// 빙하이고, 방문을 아직 안한 곳이면
					if(map[i][j] != 0 && visited[i][j] == false) {
						
						// 방문 체크 후 탐색
						visited[i][j] = true;
						DFS(i, j);
						
						// 빙하의 그룹 +1
						iceGroup++;
					}
				}
			}
			
			// 빙하의 그룹이 두 개 이상이면 탈출
			if(iceGroup >= 2) {
				break;
			}
		}
		
		// 빙하의 그룹이 2 이상이면 걸린 시간 출력
		if(iceGroup >= 2) {
			System.out.println(year);
		}
		
		// 빙하가 다 녹을 때까지 빙하의 그룹이 2 이상이 아닌 경우 0 출력
		else {
			System.out.println(0);
		}
		
	}

}
