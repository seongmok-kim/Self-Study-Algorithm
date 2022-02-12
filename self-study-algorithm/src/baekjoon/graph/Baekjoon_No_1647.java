package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_1647 {

	// N : 집의 수
	// M : 길의 수
	public static int N, M;
	
	// 우선순위 큐
	public static PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
	
	// 부모 배열
	public static int[] parent;
	
	// 간선을 구현하기 위한 class 선언
	public static class Edge implements Comparable<Edge>{
		private int from;
		private int to;
		private int cost;
		
		public Edge(int a, int b, int c) {
			from = a;
			to = b;
			cost = c;
		}

		public int getFrom() {
			return from;
		}

		public void setFrom(int from) {
			this.from = from;
		}

		public int getTo() {
			return to;
		}

		public void setTo(int to) {
			this.to = to;
		}

		public int getCost() {
			return cost;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge other) {
			if(this.cost < other.cost)
				return -1;
			else
				return 1;
		}
	}
	
	// 부모 찾기 (집합 찾기)
	public static int findParent(int x) {
		// 루트노드가 자신일 경우 반환
		if(x == parent[x]) {
			return x;
		}
		
		// 루트노드를 끝까지 찾아서 parent에 넣고 반환
		return parent[x] = findParent(parent[x]);
	}
	
	// 부모 합치기 (집합 합치기)
	public static void unionParent(int a, int b) {
		int pa = findParent(a);
		int pb = findParent(b);
		
		if(pa<pb)
			parent[pb] = pa;
		else
			parent[pa] = pb;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		
		// parent 배열에 부모노드는 자기 자신으로 초기화
		for(int i=0;i<=N;i++) {
			parent[i]=i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			// 우선 순위 큐에 넣기
			pq.offer(new Edge(from, to, cost));
		}
		
		// 결과 변수
		int result = 0;
		
		// 최소 신장 트리 중 가장 큰 간선
		int last = 0;
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			int from = edge.from;
			int to = edge.to;
			int cost = edge.cost;
			
			// 두 노드의 루트노드가 다르면 사이클 존재 X이므로, 최소 신장 트리에 추가
			if(findParent(from) != findParent(to)) {
				// 같은 집합으로 설정
				unionParent(from, to);
				
				// 결과 변수
				result += cost;
				
				// 작은 것부터 추가되니까 맨 마지막에 추가된 간선이 가장 길이가 큰 간선임.
				last = cost;
			}
		}
		
		// 추출한 최소 신장트리에서 가장 큰 간선을 빼면 마을이 2개 됨.
		System.out.println(result-last);
	}

}