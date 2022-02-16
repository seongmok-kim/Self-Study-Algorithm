package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_No_2606 {

	// N : 컴퓨터의 수
	// M : 네트워크 수
	public static int N,M;
	
	// 그래프 변수
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	
	// 방문 변수 체크
	public static boolean[] visited;
	
	// 바이러스에 감염된 컴퓨터의 수
	public static int result = 0;
	
	// DFS 이용 
	public static void DFS(int num) {
		
		// 현재 컴퓨터 방문 처리 
		visited[num] = true;
		
		// 감염된 컴퓨터에 1 추가  
		result++;
		
		// 현재 컴퓨터와 연결된 컴퓨터 탐색 
		for(int i=0; i<graph.get(num).size(); i++) {
			int next = graph.get(num).get(i);
			
			// 방문 안했던 컴퓨터일 경우에 DFS 재귀 호출 
			if(visited[next] == false) {
				DFS(next);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		// 그래프 세팅 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		visited = new boolean[N+1];
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 네트워크선은 양방향 통신이므로 아래와 같이 그래프 설정 
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		// DFS
		DFS(1);
		
		// 결과 출력(1번 컴퓨터는 빼고 계산) 
		System.out.println(result-1);
	}

}