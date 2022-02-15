package baekjoon.floyd_warshall_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_1197 {
	
	// V : 정점의 개수
	// M : 간선의 개수
	public static int V, M;
	
	// 각 정점의 루트 노드
	public static int[] parent;
	
	// 노드 클래스 선언
	public static class Node implements Comparable<Node>{
		int posA;
		int posB;
		int cost;
		
		public Node(int a, int b, int c) {
			posA=a;
			posB=b;
			cost=c;
		}
		
		// 우선순위 큐에서 사용 (비용이 적은 것이 우선순위를 갖도록)
		@Override
		public int compareTo(Node other) {
			if(this.cost < other.cost) {
				return -1;
			}else {
				return 1;
			}
		}
	}
	
	// 특정 노드의 루트 노드 찾기
	public static int findParent(int x) {
		if(x == parent[x]) {
			return x;
		}
		
		return parent[x] = findParent(parent[x]);
	}
	
	// 두 개 노드의 루트 노드 합치기
	public static void unionParent(int a, int b){
		int pa = findParent(a);
		int pb = findParent(b);
		
		// 큰 수가 작은 수를 바라보도록 설정(일반적으로 이렇게 설정함)
		if(pa < pb)
			parent[pb] = pa;
		else {
			parent[pa] = pb;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 그래프(우선순위 큐)
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 정점과 간선 입력받기
		V = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			q.add(new Node(a,b,cost));
		}
		
		// 부모노드 크기 할당
		parent = new int[V+1];
		
		// 부모노드 자기 자신으로 초기화
		for(int i=1; i<=V; i++) {
			parent[i] = i;
		}
		
		int result = 0;
		
		// 큐가 빌 때까지 반복
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			int a = node.posA;
			int b = node.posB;
			int cost = node.cost;
			
			// 부모 노드가 같지 않으면 진행 (부모 노드가 같으면 사이클이 발생한 것임)
			if(findParent(a) != findParent(b)) {
				unionParent(a,b);
				result += cost;
			}
		}
		
		System.out.println(result);
		
	}
}