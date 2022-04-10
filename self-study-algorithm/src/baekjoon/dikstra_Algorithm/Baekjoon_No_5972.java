package baekjoon.dikstra_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_5972 {
	public static int N, M;
	
	public static ArrayList<ArrayList<Node>> map = new ArrayList<>();
	
	// 무한으로 표현될 변수
	public static final int INF = (int)1e9;
	
	// 시작점에서 각 지점까지의 최단 거리 기록 배열
	public static int[] d;
	
	// 우선순위 큐에서 사용될 노드 클래스 정의
	public static class Node implements Comparable<Node>{
		int idx;
		int cow;
		
		public Node(int idx, int cow) {
			this.idx = idx;
			this.cow = cow;
		}
		
		// 소의 갯수가 적을수록 높은 우선순위를 가지도록 설정
		@Override
		public int compareTo(Node other) {
			if(this.cow < other.cow) {
				return -1;
			}
			else {
				return 1;
			}
		}
	}
	
	// 다익스트라 알고리즘을 이용하여 최단거리 구하기
	public static void dikjstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		// 시작점은 1
		pq.offer(new Node(1, 0));
		d[1] = 0;
		
		// 우선순위 큐가 빌때까지 반복
		while(!pq.isEmpty()) {
			Node now = pq.poll();
		
			int idx = now.idx;
			int cow = now.cow;
			
			// 최단거리에 등록된 것보다 지금이 비용이 더 많이 드는 경우 무시
			if(d[idx] < cow) {
				continue;
			}
			
			// 지금으로부터 갈 수 있는 모든 노드 탐색
			for(int i=0; i<map.get(idx).size(); i++) {
				int tempIdx = map.get(idx).get(i).idx;
				int tempCow = map.get(idx).get(i).cow;
				
				// 지금 노드를 통해서 가는 것이 더 빠르면 갱신하고 우선순위 큐에 넣어주기
				int sum = tempCow + cow;
				if(d[tempIdx] > sum) {
					d[tempIdx] = sum;
					pq.offer(new Node(tempIdx, sum));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 헛간의 갯수. 시작점 : 1, 도착점 : N
		N = Integer.parseInt(st.nextToken());
		
		// 길의 갯수
		M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<=N; i++) {
			map.add(new ArrayList<Node>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int spot_1 = Integer.parseInt(st.nextToken());
			int spot_2 = Integer.parseInt(st.nextToken());
			int cow = Integer.parseInt(st.nextToken());
			
			// 양방향 이동 가능
			map.get(spot_1).add(new Node(spot_2, cow));
			map.get(spot_2).add(new Node(spot_1, cow));
		}
		
		d = new int[N+1];
		
		// 최단 거리 배열 무한으로 초기화
		Arrays.fill(d, INF);
		
		dikjstra();
		
		System.out.println(d[N]);
	}

}