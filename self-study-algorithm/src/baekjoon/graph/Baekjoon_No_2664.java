package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_No_2664 {

	// N : 사람의 수, M : 관계의 개수 
	// TARGET1, TARGET2 : 촌수를 계산해야하는 두 사람
	public static int N, M, TARGET1, TARGET2;
	
	// 방문 체크 변수
	public static boolean[] visited;
	
	// 그래프
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	
	// 결과
	public static int result = -1;
	
	// DFS 알고리즈 시작
	public static void DFS(int num, int current) {
		
		// 현재 노드 방문 체크
		visited[current] = true;
		
		// TARGET1로부터의 거리 1 증가
		num++;
		
		// 만약 현재 노드가 TARGET2라면 이어져있는 것이기 때문에 result 갱신
		if(current==TARGET2) {
			result = num;
			return;
		}
		
		// 현재 노드와 이어진 곳 확인
		for(int i=0; i<graph.get(current).size(); i++) {
			int next = graph.get(current).get(i);
			
			// 방문을 아직 안했다면 방문
			if(visited[next] == false) {
				DFS(num, next);
			}
		}
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		TARGET1 = Integer.parseInt(st.nextToken());
		TARGET2 = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 양방향을 위해 아래와 같이 그래프 설정
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		//DFS 탐색
		DFS(-1, TARGET1);
		
		// 결과 출력
		System.out.println(result);
	}

}