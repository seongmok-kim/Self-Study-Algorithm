package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_5014 {

	// F : 건물의 전체 층 수
	// G : 스타트링크의 층 수
	// S : 강호가 현재 있는 층 수
	// U : 위로 올라가는 층 수
	// D : 아래로 내려가는 층 수
	public static int F, G, S, U, D;
	
	// 버튼 눌러야하는 최소 횟수
	public static int result = (int)1e9;
	
	// 방문 체크 변수
	public static boolean[] visited;
	
	// BFS탐색
	public static void BFS() {
		
		// 큐를 이용하여 해결
		Queue<Node> q = new LinkedList<>();
		
		// 현재 위치(강호의 현재 층 수) 방문 체크 후 큐에 넣기
		visited[S] = true;
		q.offer(new Node(S,0));
		
		// 큐가 빌 때까지 반복
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			int num = node.floor;
			int cnt = node.cnt;
			
			// 큐에서 꺼낸 노드가 G층이라면 result 갱신
			if(num == G) {
				result = Math.min(cnt, result);
				continue;
			}
			
			// 위로 가는 버튼 누른 경우
			// 1층~F층 이내일 경우 방문하지 않았다면 큐에 넣기
			int uNum = num+U;
			if(uNum <= F && uNum >= 1) {
				if(visited[uNum] == false) {
					visited[uNum] = true;
					q.offer(new Node(uNum, cnt+1));
				}
			}
			
			// 아래로 가는 버튼 누른 경우
			// 1층~F층 이내일 경우 방문하지 않았다면 큐에 넣기
			int dNum = num-D;
			if(dNum <= F && dNum >= 1) {
				if(visited[dNum] == false) {
					visited[dNum] = true;
					q.offer(new Node(dNum, cnt+1));
				}
			}
		}
	}
	
	// 노드 클래스 선언
	public static class Node{
		// 현재 층 수
		int floor;
		
		// 버튼 누른 횟수
		int cnt;
		
		public Node(int a, int b) {
			floor = a;
			cnt = b;
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		visited = new boolean[F+1];
		BFS();
		
		if(result == (int)1e9) {
			System.out.println("use the stairs"); 
		}
		else {
			System.out.println(result);
		}
	}

}
