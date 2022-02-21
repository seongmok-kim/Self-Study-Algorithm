package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_No_9205 {
	
	// 결과
	public static boolean[] result;
	
	// 방문변수체크
	public static boolean[] visited;
	
	// 노드 클래스 선언
	public static class Node{
		int x;
		int y;
		
		public Node(int a, int b) {
			x=a;
			y=b;
		}
	}
	
	// DFS탐색
	public static void DFS(Node now, ArrayList<Node> storeArr, Node festival, int current) {
		// 페스티벌이 1000미터 내에 있는 지 확인
		// 맥주 한 병당 50미터 이동할 수 있고 20병 있으므로. 
		if(Math.abs(now.x-festival.x) + Math.abs(now.y-festival.y) <= 1000) {
			result[current] = true;
			return;
		}
		
		// 편의점 위치 확인
		for(int i=0; i<storeArr.size(); i++) {
			Node store = storeArr.get(i);
			
			// 방문 안 한 편의점인 경우
			if(visited[i] == false) {
				// 1000미터 이내에 있다면 DFS 탐색 실행
				if(Math.abs(now.x-store.x) + Math.abs(now.y-store.y) <= 1000) {
					visited[i] = true;
					DFS(store, storeArr, festival, current);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// t : 테스트 케이스의 갯수
		int t = Integer.parseInt(br.readLine());
		result = new boolean[t];
		
		StringTokenizer st;
		
		for(int i=0; i<t; i++) {
			// n : 맥주를 파는 편의점의 갯수
			int n = Integer.parseInt(br.readLine());
			
			// home : 상근이 집 위치
			st = new StringTokenizer(br.readLine());
			Node home = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			// store : 편의점 위치
			ArrayList<Node> store = new ArrayList<>();
			for(int j=0; j<n; j++) {
				st = new StringTokenizer(br.readLine());
				
				store.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			// festival : 축제 위치
			st = new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			
			Node festival = new Node(x, y);
			
			visited = new boolean[n];
			
			// DFS탐색 시작
			DFS(home, store, festival, i);
		}
		
		// 결과 출력
		for(int i=0;i<t;i++) {
			if(result[i])
				System.out.println("happy");
			else
				System.out.println("sad");
		}
	}

}
