package baekjoon.dikstra_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 노드 클래스 선언
class Node2 implements Comparable<Node2>{
	
	// 지점
	private int index;
	
	// 거리
	private int distance;
	
	// 생성자를 이용해 멤버변수 설정
	public Node2(int index, int distance) {
		this.index = index;
		this.distance = distance;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public int getDistance() {
		return this.distance;
	}
	
	// 우선순위 큐에서 사용될 부분
	@Override
	public int compareTo(Node2 other) {
		// distance가 작은 것이 우선순위를 갖는다.
		if(other.distance > this.distance)
			return -1;
		else
			return 1;
	}
}

public class Baekjoon_No_1753 {

	// 무한으로 표현될 변수
	public static final int INF = (int)1e9;
	
	// 그래프 변수
	public static ArrayList<ArrayList<Node2>> graph = new ArrayList<ArrayList<Node2>>();
	
	// N : 정점의 갯수
	// M : 간선의 갯수
	// START : 시작점
	public static int N,M,START;
	
	// 시작점에서 각 노드별 최단 경로를 모아두는 배열
	public static int[] d;
	
	// 다익스트라 함수 (최단 경로 구하는 함수)
	public static void dijkstra() {
		
		// 우선순위 큐를 이용해 최단경로 구하기(시간 효율을 위함)
		PriorityQueue<Node2> pq = new PriorityQueue<Node2>();
		
		// 1. 무한으로 초기화
		Arrays.fill(d, INF);
		
		// 2. 시작점 우선순위 큐에 넣고 자기 자신은 0으로 갱신
		pq.offer(new Node2(START, 0));
		d[START] = 0;
		
		// 3. 우선순위 큐가 빌 때까지 반복
		while(!pq.isEmpty()) {
			
			// 우선순위 큐에서 노드를 하나 꺼내고
			Node2 node = pq.poll();
			
			int now = node.getIndex();
			int distance = node.getDistance();
			
			// 최단거리에 기록된 것 보다 거리가 멀 경우, continue를 이용해 무시
			if(d[now] < distance) {
				continue;
			}
			
			// 반복
			for(int i=0; i<graph.get(now).size(); i++) {
				int cost = distance + graph.get(now).get(i).getDistance();
				
				// 현재까지의 거리 + i번까지의 거리가 최단거리에 기록된 것 보다 적을 때 갱신 및 우선순위 큐에 넣어줌
				if(d[graph.get(now).get(i).getIndex()] > cost) {
					d[graph.get(now).get(i).getIndex()] = cost;
					pq.offer(new Node2(graph.get(now).get(i).getIndex(), cost));
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		
		// 시간 절약을 위해 BufferedReader 이용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 공백을 기준으로 문자열을 나눠주는 함수
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		START = Integer.parseInt(br.readLine());
		
		// 최단경로 기록용 변수 크기 선언
		d = new int[N+1];
		
		// 참고로 0. 첫번 째 리스트는 사용 X
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<Node2>());
		}
		
		// 간선의 갯수만큼 입력 반복
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Node2(to,distance));
		}
		
		// 다익스트라 알고리즘을 이용해 최단경로 구하기
		dijkstra();
		
		// 결과 출력
		for(int i=1; i<=N; i++) {
			if(d[i] == INF) {
				System.out.println("INF");
			}
			else {
				System.out.println(d[i]);
			}
		}
	}
}