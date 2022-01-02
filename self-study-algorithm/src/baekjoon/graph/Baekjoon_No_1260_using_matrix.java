package baekjoon.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Baekjoon_No_1260_using_matrix {

	// 그래프 변수(인접행렬 이용)
	public static int[][] graph;
	public static int N,M,V;
	
	//  방문 체크 변수
	public static boolean[] visited;
	
	// DFS 함수
	public static void DFS(int num) {
		
		// 현재 노드 번호 방문 처리
		visited[num] = true;
		System.out.print(num + " ");
		
		for(int i=0; i<graph[num].length;i++) {
			int temp = graph[num][i];
			
			// temp 노드가 이어져있고 방문처리가 안 된 경우 재귀함수 DFS 호출
			if(visited[i] == false && temp == 1) {
				DFS(i);
			}
		}
	}
	
	// BFS 함수
	public static void BFS(int num) {
		
		// queue.offer() : 삽입하기
		// queue.poll() : 꺼내서 확인하기
		Queue<Integer> queue = new LinkedList<>();
		
		// 현재 노드 queue에 삽입 후 방문 처리
		queue.offer(num);
		visited[num] = true;
		
		System.out.print(num + " ");
		
		// queue가 빌 때까지 반복
		while(!queue.isEmpty()) {
			// 큐에서 하나를 꺼낸 후
			int number = queue.poll();
			
			// 꺼낸 노드의 번호와 인접한 노드들 체크
			for(int i=0;i<graph[number].length;i++) {
				int temp_num = graph[number][i];
				
				// 해당 노드가 이어져있고, 방문이 안 된 경우
				if(visited[i] == false && temp_num == 1) {
					// 해당 노드를 방문 체크 후
					visited[i] = true;
					// 큐에 삽입
					queue.offer(i);
					System.out.print(i +" ");
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();
		sc.nextLine();
		
		// 변수 크기 선언
		visited = new boolean[N+1];
		
		// 맵 변수 크기 선언
		graph = new int[N+1][N+1];
		for(int i=0;i<M;i++) {
			int tempN1 = sc.nextInt();
			int tempN2 = sc.nextInt();
			sc.nextLine();
			
			// 일방적인 방향이 있는 것이 아니고 간선이기 때문에 두 노드에 둘 다 추가하며, 두 노드 간 이어져있는 경우 1로 표시
			graph[tempN1][tempN2] = 1;
			graph[tempN2][tempN1] = 1;
		}
		
		DFS(V);
		visited = new boolean[N+1];
		System.out.println();
		BFS(V);
		
	}

}
