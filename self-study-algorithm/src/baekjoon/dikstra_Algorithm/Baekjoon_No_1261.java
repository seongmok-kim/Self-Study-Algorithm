package baekjoon.dikstra_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_1261 {

	// 상하좌우 이동
	public static int[] amove = {-1, 0, 1, 0};
	public static int[] bmove = {0, 1, 0, -1};
	
	// 무한으로 표현될 변수
	public static final int INF = (int)1e9;
	
	// N : 가로크기
	// M : 세로크기
	public static int N,M;
	
	// 맵 변수
	public static int[][] map;
	
	// 다익스트라 알고리즘 확인. 0,0에서 각 노드까지의 만난 벽의 수
	public static int[][] d;
	
	// 다익스트라 알고리즘
	public static void dijkstra() {
		// 우선순위 큐 이용
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		// 최소 비용 배열 갱신
		d[0][0] = 0;
		// 우선순위 큐에 추가
		pq.offer(new Node(0,0,0));
		
		// 큐가 빌때까지
		while(!pq.isEmpty()) {
			// 큐에서 하나 꺼내고
			Node node = pq.poll();
			
			// 상하좌우 확인
			for(int i=0; i<4; i++) {
				int tempa = node.a + amove[i];
				int tempb = node.b + bmove[i];
				int tempBroken = node.broken;
				
				// 범위 벗어나면 무시
				if(tempa < 0 || tempa>=M || tempb < 0 || tempb>=N) {
					continue;
				}
				// 벽이라면 카운트
				if(map[tempa][tempb] == 1) {
					tempBroken++;
				}
				// 최소비용 배열보다 현재 경로의 비용이 더 적을 경우 
				if(d[tempa][tempb] > tempBroken) {
					// 갱신
					d[tempa][tempb] = tempBroken;
					
					// 우선순위 큐에 추가
					pq.offer(new Node(tempa, tempb, tempBroken));
				}
				
				
			}
		}
	}
	
	// 우선순위 큐에서 사용할 노드 클래스 정의
	public static class Node implements Comparable<Node>{
		int a;
		int b;
		int broken;
		
		public Node(int a, int b, int broken) {
			this.a = a;
			this.b = b;
			this.broken = broken;
		}
		
		@Override
		public int compareTo(Node other) {
			if(this.a < other.a) {
				return -1;
			}
			else {
				return 1;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		d = new int[M][N];
		for(int i=0; i<M; i++) {
			Arrays.fill(d[i], INF);
		}
		
		for(int i=0; i<M; i++) {
			String temp = br.readLine();
			String[] tempArr = temp.split("");
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tempArr[j]);
			}
		}
		
		// 다익스트라 알고리즘을 이용하여 최소 비용 구하기
		dijkstra();
		
		System.out.println(d[M-1][N-1]);
	}

}