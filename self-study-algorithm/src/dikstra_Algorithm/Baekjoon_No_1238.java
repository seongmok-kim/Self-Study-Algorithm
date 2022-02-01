package dikstra_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//거리와 노드를 저장할 수 있는 Node Class 선언
class Node implements Comparable<Node> {
	// 시간
	private int cost;

	// 노드
	private int index;

	public Node(int index, int distance) {
		this.index = index;
		this.cost = distance;
	}

	public int getCost() {
		return this.cost;
	}

	public int getIndex() {
		return this.index;
	}

	// 거리가 낮을수록 우선순위가 높게 설정
	@Override
	public int compareTo(Node other) {
		if (this.cost < other.cost) {
			return -1;
		} else {
			return 1;
		}
	}
}

public class Baekjoon_No_1238 {

	// 무한을 의미
	public static final int INF = (int) 1e9;

	// N : 마을의 갯수
	// M : 간선의 갯수
	// X : 파티하는 마을
	public static int N, M, X;

	// graph 1 : 마을에서 X로 가는 그래프(일반그래프)
	public static ArrayList<ArrayList<Node>> graph1 = new ArrayList<ArrayList<Node>>();

	// graph2 : X에서 마을로 가는 최단 경로 구하는 로직에 사용될 그래프
	public static ArrayList<ArrayList<Node>> graph2 = new ArrayList<ArrayList<Node>>();
	
	// d1 : 마을에서 X로 가는 최단 경로
	// d2 : X에서 마을로 돌아가는 최단 경로
	public static int[] d1, d2;

	public static void dijkstra() {
		/**** X마을 에서 각자 마을로 가는 최단 경로 구하기 ****/
		// 우선순위 큐1
		PriorityQueue<Node> pq1 = new PriorityQueue<>();

		Arrays.fill(d1, INF);

		pq1.offer(new Node(X, 0));
		d1[X] = 0;

		while (!pq1.isEmpty()) {
			Node node = pq1.poll();

			int index = node.getIndex();
			int cost = node.getCost();

			if (d1[index] < cost) {
				continue;
			}

			for (int i = 0; i < graph1.get(index).size(); i++) {
				int temp = cost + graph1.get(index).get(i).getCost();

				if (temp < d1[graph1.get(index).get(i).getIndex()]) {
					d1[graph1.get(index).get(i).getIndex()] = temp;
					pq1.offer(new Node(graph1.get(index).get(i).getIndex(), temp));
				}
			}
		}

		/**** 각자 마을에서 X마을로 가는 최단 경로 구하기 ****/
		// 우선순위 큐2
		PriorityQueue<Node> pq2 = new PriorityQueue<>();

		Arrays.fill(d2, INF);

		pq2.offer(new Node(X, 0));
		d2[X] = 0;

		while (!pq2.isEmpty()) {
			Node node = pq2.poll();

			int index = node.getIndex();
			int cost = node.getCost();

			if (d2[index] < cost) {
				continue;
			}

			for (int i = 0; i < graph2.get(index).size(); i++) {
				int temp = cost + graph2.get(index).get(i).getCost();

				if (temp < d2[graph2.get(index).get(i).getIndex()]) {
					d2[graph2.get(index).get(i).getIndex()] = temp;
					pq2.offer(new Node(graph2.get(index).get(i).getIndex(), temp));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		d1 = new int[N + 1];
		d2 = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			graph1.add(new ArrayList<Node>());
			graph2.add(new ArrayList<Node>());
		}

		// 간선의 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			// 일반적인 그래프 설정
			graph1.get(from).add(new Node(to, cost));
			
			// 각자 마을에서 X로 가는 것을 구하기 위함. 
			// 방향만 바꾸면 다익스트라 알고리즘으로 최단 경로를 구하기 쉬움
			graph2.get(to).add(new Node(from, cost));
		}

		// 다익스트라 알고리즘 진행
		dijkstra();

		// 결과 출력
		int min = 0;
		
		for(int i=1; i<=N; i++) {
			int sum = d1[i] + d2[i];
		
			if(min < sum) {
				min = sum;
			}
		}
		System.out.println(min);
		
	}

}
