package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_1697 {

	// N : 수빈이의 위치
	// K : 동생의 위치
	public static int N, K;
	
  // 동생에게 가는 최소 시간
	public static int result = (int)1e9;
	
  // 방문 체크 변수 (움직일 수 있는 범위 : 0~100000)
	public static boolean[] visited = new boolean[100001];
	
  // BFS 탐색
	public static void BFS() {
    // 큐를 이용
		Queue<Node> q = new LinkedList<>();
		
    // 수빈의 위치 큐에 넣기
		q.offer(new Node(N, 0));
		
    // 큐가 빌 때까지 반복
		while(!q.isEmpty()) {
      // 큐에서 하나 꺼내고
			Node node = q.poll();
			
			int pos = node.pos;
			int time = node.time;
			
      // 방문한 적 있는 위치면 무시
			if(visited[pos] == true) {
				continue;
			}
			
      // 방문 체크
			visited[pos] = true;
					
      // 현재 위치가 동생의 위치일 경우, 지금까지의 시간과 result를 비교해서 적을 경우 갱신
			if(pos == K) {
				result = Math.min(time, result);
				continue;
			}
			
      // 순간이동 했을 때의 위치가 방문 안한 곳이라면 큐에 추가(범위 이내)
			if(pos*2 >= 0 && pos*2 <=100000 && visited[pos*2] == false ) {
				q.offer(new Node(pos*2, time+1));
			}
      // 한 칸 앞으로 갔을 때의 위치가 방문 안한 곳이라면 큐에 추가(범위 이내)
			if(pos+1 >= 0 && pos+1 <=100000 && visited[pos+1] == false) {
				q.offer(new Node(pos+1, time+1));
			}
      // 한 칸 뒤로 갔을 때의 위치가 방문 안한 곳이라면 큐에 추가(범위 이내)
			if(pos-1 >= 0 && pos-1 <=100000 && visited[pos-1] == false) {
				q.offer(new Node(pos-1, time+1));
			}
			
			
		}
	}
	
  // 노드 클래스 선언
	public static class Node{
		int pos;
		int time;
		
		public Node(int a, int b) {
			pos = a;
			time = b;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		br.close();
		
    //BFS 탐색
		BFS();
		
    // 결과 출력
		System.out.println(result);
		
	}

}