package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_13549 {

	public static class Node implements Comparable<Node>{
		// 현재 위치
		int pos;
		
		// 걸린 시간
		int time;
		
		public Node(int pos, int time) {
			this.pos= pos;
			this.time=time;
		}
		
		// 우선순위 큐에서 이용
		@Override
		public int compareTo(Node other) {
			// time 작은 것이 높은 우선순위를 갖는다.
			if(other.time > this.time)
				return -1;
			else
				return 1;
		}
	}
	
	// START : 수빈의 위치, END : 동생의 위치
	public static int START,END;
	
	// 방문 체크용 변수
	public static boolean[] visited;
	
	// 최소 횟수
	public static int min = Integer.MAX_VALUE;
	
	// 맵은 0 ~ 100000 로 고정(문제에 제시)
	public static int max = 100000;
	
	
	public static void bfs() {
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		
		// 수빈의 위치를 큐에 넣기
		q.offer(new Node(START, 0));
		
		// 큐가 빌 때 까지 반복
		while(!q.isEmpty()) { 
			Node node = q.poll();
			
			// 방문 했는 지 여부를 큐에 넣기 전에 하기(우선순위 큐를 이용한 경우에는 큐에서 꺼낸 후에 해도 괜찮음)
			// 속도는 우선순위큐+ 방문체크여부를 큐에 넣기 전에 하는 것이 가장 빠름
			
			// 방문 체크
			visited[node.pos] = true;
			
			// node(수빈)이 동생과 위치가 같으면,
			// node에 기록된 횟수와 최소 횟수로 기록된 변수와 비교 후, 더 작으면 갱신
			if(node.pos == END) {
				min = Math.min(min, node.time);
			}
			
			// 현재 위치에서 2배를 해도 max 범위 이내일 경우,
			// 2배 해서 queue에 넣기 (순간이동 시, 0초소요)
			if(node.pos*2 <= max && visited[node.pos*2] == false) {
				q.offer(new Node(node.pos*2, node.time));
			}
			
			// 현재 위치에서 +1을 해도 max 범위 이내일 경우,
			// +1 해서 queue에 넣기
			if(node.pos+1<=max && visited[node.pos+1] == false) {
				q.offer(new Node(node.pos+1, node.time+1));
			}
			
			// 현재 위치에서 -1을 해도 max 범위 이내일 경우,
			// -1 해서 queue에 넣기
			if(node.pos-1>=0 && visited[node.pos-1] == false) {
				q.offer(new Node(node.pos-1, node.time+1));
			}
		}
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		START = Integer.parseInt(st.nextToken());
		END = Integer.parseInt(st.nextToken());

		// 방문체크용 변수 크기 할당
		visited = new boolean[max+1];
		
		// bfs 함수를 이용해서 최소 횟수 구하기
		bfs();
		
		// 결과 출력
		System.out.println(min);
	}
}