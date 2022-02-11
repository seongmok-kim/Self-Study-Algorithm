package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon_No_2665 {

	// 그래프 변수
	public static int[][] graph;
	
	// 무한 변수
	public static final int INF = (int)1e9;
	
	// 한 줄에 들어가는 방의 수
	public static int N;
	
	// 각 좌표으로 가는 데 경유해야 하는 검은방의 최소 수
	public static int[][] d ;
	
	// 상하좌우 이동에 필요한 배열
	public static int[] xmove = {1,-1,0,0};
	public static int[] ymove = {0,0,1,-1};
	
	// BFS 함수
	public static void BFS() {
		Queue<Node> q = new LinkedList<>();
		
		// 시작점은 0,0 고정이므로 큐에 넣고 최소 기록에도 넣는다.
		q.offer(new Node(0, 0, graph[0][0]));
		d[0][0] = graph[0][0];
		
		// 큐가 빌 때까지 반복
		while(!q.isEmpty()) {
			
			// 큐에서 하나 꺼내고
			Node node = q.poll();
			
			// 상하좌우 확인
			for(int i=0; i<4; i++) {
				int tempX = node.xPos + xmove[i];
				int tempY = node.yPos + ymove[i];
				
				// 범위를 벗어나면 무시
				if(tempX >= N || tempX < 0 || tempY >= N || tempY < 0) {
					continue;
				}
				// 범위 내라면
				else {
					// 위치로 가는 데의 경유하는 최소 검은 방 갯수  +  상하좌우로 이동한 위치의 검은방유무
					int tempTotal = d[node.xPos][node.yPos] + graph[tempX][tempY];
					
					// 최소기록보다 작을 경우, 갱신하고 큐에 넣기
					if(tempTotal < d[tempX][tempY]) {
						d[tempX][tempY] = tempTotal;
						q.offer(new Node(tempX, tempY, tempTotal));
					}
				}
			}
		}
	}
	
	// Node 클래스 선언
	public static class Node{
		// x좌표
		int xPos;
		
		// y좌표
		int yPos;
		
		// 현재 좌표까지의 검은방의 갯수
		int total;
		
		public Node(int x, int y, int s) {
			this.xPos = x;
			this.yPos = y;
			this.total = s;
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		d = new int[N][N];
		
		for (int i=0; i<N; i++) {
			String temp = br.readLine();
			String[] tempArr = temp.split("");
			
			// 최소 갯수를 무한으로 초기화
			Arrays.fill(d[i], INF);
			
			for(int j=0;j<N; j++) {
				
				// 검은 방을 1로, 흰 방을 0으로 바꿔서 넣기
				if(tempArr[j].equals("0")) {
					graph[i][j] = 1;
				}else {
					graph[i][j] = 0;
				}
			}
		}
		
		BFS();
		
		System.out.println(d[N-1][N-1]);
	}

}