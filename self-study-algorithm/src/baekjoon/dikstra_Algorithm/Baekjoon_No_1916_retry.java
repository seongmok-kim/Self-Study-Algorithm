package baekjoon.dikstra_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_1916_retry {

	// N : 도시의 갯수
	// M : 버스의 갯수 
	// START : 시작 지점, END : 도착 지점
	public static int N, M, START, END;
	
	// map
	public static ArrayList<ArrayList<Node>> map = new ArrayList<>();
	
	// 최단 거리 기록할 배열
	public static int[] result;
	
	// 무한으로 표현될 변수
	public static final int INF = (int)1e9;
	
	public static class Node implements Comparable<Node>{
		int index;
		int cost;
		
		public Node(int a, int b) {
			index = a;
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
	
	public static void dikstra() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		// 배열 내 무한으로 초기화
		Arrays.fill(result, INF);
		
		// 시작지점 우선순위 큐에 넣기
		pq.offer(new Node(START,0));
		result[START] = 0;
		
		// 빌 때 까지 반복
		while(! pq.isEmpty() ) {
			Node node = pq.poll();
			
			int index = node.index;
			int cost = node.cost;
			
			// 현재 뽑은 노드의 정보가 최소 비용에 등록된 것보다 클 때 무시
			if(result[index] < cost) {
				continue;
			}
			
			// 현재 뽑은 노드에서 갈 수 있는 곳 조사
			for(int i=0; i<map.get(index).size(); i++) {
				int temp = cost + map.get(index).get(i).cost;
				
				if(result[map.get(index).get(i).index] > temp) {
					result[map.get(index).get(i).index] = temp;
					pq.offer(new Node(map.get(index).get(i).index, temp));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for(int i=0; i<=N; i++) {
			map.add(new ArrayList<Node>());
		}
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			map.get(from).add(new Node(to, cost));
		}
		
		// 시작지점과 끝지점 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		START = Integer.parseInt(st.nextToken());
		END = Integer.parseInt(st.nextToken());
		
		// 최소 비용 기록할 배열 크기 선언
		result = new int[N+1];
		
		// 다익스트라 알고리즘 진행
		dikstra();
		
		System.out.println(result[END]);
	}

}
