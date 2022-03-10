package baekjoon.dikstra_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_1504 {
	
	// N : 정점의 갯수
	// E : 간선의 갯수
	// V1, V2 : 반드시 거쳐야 하는 정점 1, 2
	public static int N, E, V1, V2;
	
	// MAP 변수
	public static ArrayList<ArrayList<Node>> map = new ArrayList<ArrayList<Node>>();
	
	// 1번 인덱스, V1번 인덱스, V2번 인덱스 기준으로 각 노드까지의 최소 경로를 모아두는 배열
	public static int[][] d;
	
	// 무한으로 표현될 변수
	public static final int INF = (int)1e8;

	// 우선순위 큐에서 우선순위를 갖도록 한다.
	public static class Node implements Comparable<Node>{
		int idx;
		int cost;
		
		public Node(int a, int b) {
			idx = a;
			cost = b;
		}
		
		@Override
		public int compareTo(Node other) {
			if(other.cost > this.cost)
				return -1;
			else
				return 1;
		}
	}
	
	public static void dijkstra(){
		// d[0] : 1번 인덱스에서 각 노드로의 최소 비용
		// d[1] : V1번 인덱스에서 각 노드로의 최소 비용
		// d[2] : V2번 인덱스에서 각 노드로의 최소 비용
		int[] numList = {1, V1, V2};
		
		for(int i=0; i<numList.length; i++) {
			int now = numList[i];
			
			// 우선순위 큐
			PriorityQueue<Node> pq = new PriorityQueue<Node>();
			
			pq.offer(new Node(now, 0));
			d[i][now] = 0;
			
			while(! pq.isEmpty()) {
				Node node = pq.poll();
				
				int idx = node.idx;
				int cost = node.cost;
				
				if(d[i][idx] < cost) {
					continue;
				}
				
				for(int j=0; j<map.get(idx).size(); j++) {
					Node newNode = map.get(idx).get(j);
					
					int tempCost = cost + newNode.cost;
					int tempIdx = newNode.idx;
					
					if(d[i][tempIdx] > tempCost) {
						d[i][tempIdx] = tempCost;
						pq.offer(new Node(tempIdx, tempCost));
					}
					
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N+1; i++) {
			map.add(new ArrayList<Node>());
		}
				
		// 간선의 갯수가 0일 수도 있음
		if(E != 0) {
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				// 양방향
				map.get(A).add(new Node(B, cost));
				map.get(B).add(new Node(A, cost));
			}
		}
		
		
		st = new StringTokenizer(br.readLine());
		V1 = Integer.parseInt(st.nextToken());
		V2 = Integer.parseInt(st.nextToken());
		
		// d[0] : 1번 인덱스에서 각 노드로의 최소 비용
		// d[1] : V1번 인덱스에서 각 노드로의 최소 비용
		// d[2] : V2번 인덱스에서 각 노드로의 최소 비용
		d = new int[3][N+1];
		
		// 무한으로 초기화
		for(int i=0; i<d.length; i++) {
			Arrays.fill(d[i], INF);
		}
		
		// 다익스트라 알고리즘을 이용하여 최소 비용 구하기
		dijkstra();
		
		// 1번 인덱스 ~ V1번 인덱스, V2번 인덱스 ~ N번 인덱스의 최소 비용을 구하는 문제
		// 경로의 경우의 수는 아래 2가지이며, 작은 값을 출력하면 된다.
		int result = Math.min(d[0][V1] + d[1][V2] + d[2][N], d[0][V2] + d[2][V1] + d[1][N]);
		
		// 갈 수 없는 경우 -1 출력
		if(result >= INF) {
			System.out.println(-1);
		}else {
			System.out.println(result);
		}
	}

}
